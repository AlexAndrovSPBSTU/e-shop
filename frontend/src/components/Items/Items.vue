<template>
  <div class="items">
    <div class="container">
      <div class="filters">
        <v-select 
          v-model="filter" 
          :items=filters
          variant="underlined" 
          label="Сортировать по"
          class="my-select"
        >
        </v-select>
      </div>
      <div class="list" v-for="(item, index) in paginatedTickers" :key="index">
        <item-of-catalog :item="item" />
      </div>

      <div class="text-center">
        <v-container>
          <v-row justify="center">
            <v-col cols="8">
              <v-container class="max-width">
                <v-pagination
                  v-model="page"
                  class="my-4"
                  :length="Math.ceil(items.length / 6)"
                  :total-visible="4"
                ></v-pagination>
              </v-container>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
  </div>
</template>

<script>
import ItemOfCatalog from "@/components/ItemOfCatalog/ItemOfCatalog.vue";
import MyPagination from "../Pagination/MyPagination.vue";

export default {
  components: {
    ItemOfCatalog,
    MyPagination,
  },

  data: () => ({
    page: 1,
    filters: ['Сначала подешевле', 'Сначала подороже'],
    filter: 'Сначала подешевле',
    items: [
      {
        title: "Realme C55",
        description:
          "64MP Kamera Juara Я пишу ответы с помощью YaGPT 2 — новой нейросети Яндекса, подражая текстам в интернете. Поэтому результат может быть выдумкой: это не моё мнение и не мнение Яндекса. Я стараюсь быть этичной, так что на некоторые запросы не отвечаю. Не судите строго за ошибки — я только учусь.",
        img: "src/assets/productimage.png",
        amount: 999,
        price: 32999,
      },
      {
        title: "iPhone 15 Pro",
        description:
          "64MP Kamera Juara Я пишу ответы с помощью YaGPT 2 — новой нейросети Яндекса, подражая текстам в интернете. Поэтому результат может быть выдумкой: это не моё мнение и не мнение Яндекса. Я стараюсь быть этичной, так что на некоторые запросы не отвечаю. Не судите строго за ошибки — я только учусь.",
        img: "src/assets/15pro.png",
        amount: 999,
        price: 259000,
      },
      {
        title: "iPhone 15 Pro Max",
        description:
          "64MP Kamera Juara Я пишу ответы с помощью YaGPT 2 — новой нейросети Яндекса, подражая текстам в интернете. Поэтому результат может быть выдумкой: это не моё мнение и не мнение Яндекса. Я стараюсь быть этичной, так что на некоторые запросы не отвечаю. Не судите строго за ошибки — я только учусь.",
        img: "src/assets/15promax.png",
        amount: 999,
        price: 350000,
      },
      {
        title: "iPhone 14",
        description:
          "64MP Kamera Juara Я пишу ответы с помощью YaGPT 2 — новой нейросети Яндекса, подражая текстам в интернете. Поэтому результат может быть выдумкой: это не моё мнение и не мнение Яндекса. Я стараюсь быть этичной, так что на некоторые запросы не отвечаю. Не судите строго за ошибки — я только учусь.",
        img: "src/assets/14.png",
        amount: 999,
        price: 157999,
      },
      {
        title: "iPhone 15",
        description:
          "64MP Kamera Juara Я пишу ответы с помощью YaGPT 2 — новой нейросети Яндекса, подражая текстам в интернете. Поэтому результат может быть выдумкой: это не моё мнение и не мнение Яндекса. Я стараюсь быть этичной, так что на некоторые запросы не отвечаю. Не судите строго за ошибки — я только учусь.",
        img: "src/assets/15.png",
        amount: 999,
        price: 129999,
      },
      {
        title: "iPhone 14 Pro",
        description:
          "64MP Kamera Juara Я пишу ответы с помощью YaGPT 2 — новой нейросети Яндекса, подражая текстам в интернете. Поэтому результат может быть выдумкой: это не моё мнение и не мнение Яндекса. Я стараюсь быть этичной, так что на некоторые запросы не отвечаю. Не судите строго за ошибки — я только учусь.",
        img: "src/assets/14pro.png",
        amount: 999,
        price: 100000,
      },
      {
        title: "Realme C55",
        description: "64MP Kamera Juara",
        img: "src/assets/airpods.png",
        amount: 999,
        price: 999,
      },
      {
        title: "Realme C55",
        description: "64MP Kamera Juara",
        img: "src/assets/productimage.png",
        amount: 999,
        price: 999,
      },
      {
        title: "Realme C55",
        description: "64MP Kamera Juara",
        img: "src/assets/productimage1.png",
        amount: 999,
        price: 999,
      },
      {
        title: "Realme C55",
        description: "64MP Kamera Juara",
        img: "src/assets/productimage1.png",
        amount: 999,
        price: 999,
      },
    ],
  }),

  computed: {
    startIndex() {
      return (this.page - 1) * 6;
    },

    endIndex() {
      return this.page * 6;
    },

    paginatedTickers() {
      return this.items.slice(this.startIndex, this.endIndex);
    },

    sortItems(){
      this.page = 1;
      return this.filter === "Сначала подешевле" 
        ? this.items.sort((a, b) => a.price - b.price) 
        : this.items.sort((a, b) => a.price - b.price).reverse()
      
    },
  },

  watch: {
    page() {
      this.paginatedTickers;
    },

    filter(){
      this.sortItems;
    }
  },
};

/**
 * 
 * created() {
  fetch("https://jsonplaceholder.typicode.com/posts", requestOptions)
    .then(response => response.json())
}
 */
</script>

<style>
.items {
  margin-top: 120px;
}

.filters{
  display: flex;
  justify-content: flex-end;
}

.my-select{
  max-width: 190px;
}
</style>
