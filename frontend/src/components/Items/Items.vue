<template>
  <div class="items">
    <div class="container">
      <v-container>
        <v-row no-gutters>
          <v-col
            v-for="(item, index) in paginatedTickers"
            :key="index"
            cols="12"
            sx="12"
            sm="auto"
            md="auto"
            lg="auto"
            xl="auto"
            xxl="auto"
          >
            <item-of-catalog :item="item"/>
          </v-col>
        </v-row>
      </v-container>
      <div class="text-center">
        <v-container>
          <v-row justify="center">
            <v-col cols="8">
              <v-container class="max-width">
                <v-pagination
                  v-model="page"
                  class="my-4"
                  :length="Math.ceil(items.length / 24)"
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

export default {
  components: {
    ItemOfCatalog
  },

  data: () => ({
    page: 1,
    items: [
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
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
        img: "src/assets/productimage.png",
        amount: 999,
        price: 999,
      },
    ],
  }),

  computed: {
    startIndex() {
      return (this.page - 1) * 24;
    },

    endIndex() {
      return this.page * 24;
    },

    paginatedTickers() {
      return this.items.slice(this.startIndex, this.endIndex);
    },
  },

  watch: {
    page() {
      this.paginatedTickers;
    },
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
  margin-top: 80px;
  padding: 60px;
  display: grid;
  justify-content: center;
}

</style>


