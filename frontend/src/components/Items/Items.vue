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
            <v-hover v-slot="{ isHovering, props }">
              <v-sheet
                class="item d-flex rounded-lg ma-2 pa-4"
                v-bind="props"
                :elevation="isHovering ? 24 : 6"
              >
                <img :src="item.img" alt="" />
                <div class="item__title">{{ item.title }}</div>
                <div class="item__description">{{ item.description }}...</div>
                <div class="price-btn">
                  <div class="item__price">Цена: {{ item.price }}</div>
                  <v-btn variant="outlined"> Купить </v-btn>
                </div>
              </v-sheet>
            </v-hover>
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
                  :length="Math.ceil(items.length/24)"
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
export default {
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
      }
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
</script>

<style>
.items {
  margin-top: 80px;
  padding: 60px;
  display: grid;
  justify-content: center;
}

.item {
  flex-direction: column;
  align-items: center;
  max-width: 260px;
}

img {
  max-width: 170px;
  height: 204px;
}

.item__title {
  font-size: 25px;
  color: rgba(0, 0, 0, 0.8);
}

.price-btn {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}

@media (max-width: 500px) {
  .price-btn {
    flex-direction: column;
  }
  .items {
    padding: 0;
  }
  .item {
    margin: 8px auto !important;
  }
}
</style>
