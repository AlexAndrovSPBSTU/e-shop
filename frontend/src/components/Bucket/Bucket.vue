<template>
  <div class="bucket">
    <my-header />

    <bucket-local v-if="!this.$store.state.initialState.status.loggedIn" />
    <div v-else class="bucket__content">
      <v-alert
        v-if="errorProducts.length > 0"
        text="Пока вы собирали корзину, некоторые товары закончились!"
        type="error"
      ></v-alert>
      <div class="container">
        <router-link
          to="/categories/1/products?page=1&order=4"
          class="back-to-shop"
        >
          Вернуться к покупкам
        </router-link>
        <div class="bucket__title">Корзина</div>

        <div class="bucket__order">
          <div class="bucket__list" v-if="dataServer.products == undefined">
            Ваша корзина пуста
          </div>
          <div class="bucket__list" v-else>
            <div
              class="bucket__item"
              v-for="(item, index) in sortProduct"
              :key="index"
              :class="{ 'sold-out': item.soldOut }"
            >
              <div class="check__item">
                <v-checkbox
                  v-model="selectedProduct"
                  :value="item"
                  hide-details="true"
                ></v-checkbox>
              </div>
              <img :src="item.img" alt="" class="bucket__img" />
              <div class="bucket__title">{{ item.name }}</div>
              <div class="bucket__btns">
                <v-btn
                  density="compact"
                  icon="mdi-minus"
                  variant="plain"
                  @click.stop="delItem(item.productId)"
                />
                <div class="bucket__count">
                  {{ item.totalCount }}
                </div>
                <v-btn
                  density="compact"
                  icon="mdi-plus"
                  variant="plain"
                  @click.stop="addItem(item.productId)"
                />
                <div class="bucket__price">
                  {{
                    (
                      item.totalCount *
                      (item.price * ((100 - item.discount) / 100))
                    ).toFixed(0)
                  }}
                </div>
                <v-btn
                  density="compact"
                  icon="mdi-trash-can"
                  variant="plain"
                  @click.stop="delStackItems(item.productId)"
                />
              </div>
            </div>
          </div>
          <div class="place-an-order">
            <div class="place-an-order--fixed">
              <div class="place-an-order__title">Оформить заказ</div>
              <div class="place-an-order__sum">
                Сумма заказа: {{ getSumProducts }}
              </div>
              <div class="place-an-order__totalAmount">
                Количество товаров: {{ getCountProducts }}
              </div>
              <v-btn class="place-an-order__btn" @click.stop="buy()"
                >Оформить</v-btn
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <my-footer />
  </div>
</template>

<script>
import MyFooter from "../Footer/MyFooter.vue";
import MyHeader from "../Header/MyHeader.vue";
import {
  getCart,
  deleteItemsOnCard,
  reduceItemOnCard,
  addToCard,
  buyCart,
} from "@/API/index.js";
import BucketLocal from "./BucketLocal.vue";

export default {
  components: { MyHeader, MyFooter, BucketLocal },
  data() {
    return {
      dataLocal: this.$store.state.bucket,
      dataServer: [],
      selectedProduct: [],
      errorProducts: [],
    };
  },

  beforeCreate() {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user) {
      getCart()
        .then((res) => {
          console.log(res);
          const updatedProducts = res.products.map((product) => ({
            ...product,
            soldOut: false,
          }));

          this.dataServer = { ...res, products: updatedProducts };
          this.selectedProduct = updatedProducts
            .slice()
            .sort((a, b) => a.productId - b.productId);
          this.$store.commit("setCount", res.totalCount);
          this.errorProducts = []; // Очищаем массив ошибок
        })
        .catch((error) => {
          console.error(error);
        });
    }
  },

  computed: {
    getCountProducts() {
      let count = 0;

      this.selectedProduct.forEach((prod) => {
        count += prod.totalCount;
      });

      return count;
    },

    getSumProducts() {
      let sum = 0;
      this.selectedProduct.forEach((prod) => {
        sum += prod.totalCount * (prod.price * ((100 - prod.discount) / 100));
      });

      return sum.toFixed(0);
    },

    sortProduct() {
      return this.dataServer.products.sort((a, b) => a.productId - b.productId);
    },
  },

  methods: {
    setData() {
      getCart()
        .then((res) => {
          if (res.products != undefined) {
            const updatedProducts = res.products.map((product) => ({
              ...product,
              soldOut: false,
            }));

            this.dataServer = { ...res, products: updatedProducts };
            this.selectedProduct = updatedProducts
              .slice()
              .sort((a, b) => a.productId - b.productId);
            this.$store.commit("setCount", res.totalCount);
            this.errorProducts = []; // Очищаем массив ошибок
          } else {
            this.dataServer = {};
            this.selectedProduct = [];
            this.$store.commit("setCount", 0);
          }
          console.log(res);
        })
        .catch((error) => {
          console.error(error);
        });
    },

    delItem(id) {
      reduceItemOnCard(id).then(() => {
        this.selectedProduct.forEach((prod) => {
          if (prod.productId === id) {
            prod.totalCount--;
          }
        });
        this.setData();
      });
    },

    delStackItems(id) {
      deleteItemsOnCard(id).then(() => {
        this.selectedProduct = this.selectedProduct.filter(
          (prod) => prod.productId !== id
        );
        this.setData();
      });
    },

    addItem(id) {
      addToCard(id).then(() => {
        this.selectedProduct.forEach((prod) => {
          if (prod.productId === id) {
            prod.totalCount++;
          }
        });
        this.setData();
      });
    },

    async buy() {
      let queryIds = "";

      this.selectedProduct.forEach((prod) => {
        queryIds += `id=${prod.productId}&`;
      });
      queryIds = queryIds.substring(0, queryIds.length - 1);

      console.log(queryIds);

      if (queryIds !== "") {
        const res = await buyCart(queryIds);

        console.log(res);

        if (res && res.soldOutProducts && res.soldOutProducts.length > 0) {
          this.errorProducts = res.soldOutProducts;
          console.log("Нет товаров");

          this.selectedProduct.forEach((prod) => {
            if (res.soldOutProducts.includes(prod.productId)) {
              prod.soldOut = true;
            } else {
              prod.soldOut = false;
            }
          });
        } else {
          console.log("Есть");
          const purchasedProducts = [...this.selectedProduct];

          this.errorProducts = [];
          this.selectedProduct.forEach((prod) => {
            prod.soldOut = false;
          });

          this.dataServer.products = this.dataServer.products.filter(
            (product) => {
              return !purchasedProducts.some((purchasedProduct) => {
                return purchasedProduct.productId === product.productId;
              });
            }
          );

          this.selectedProduct = [];
          alert("Поздравляем! Вы успешно купили товары!");

          console.log("Купленные товары:", purchasedProducts);
        }
      }
    },
  },
};
</script>

<style>
.bucket {
  height: inherit;
  display: flex;
  flex-direction: column;
}

.bucket__content {
  margin-top: 120px;
  flex: 1;
}

.back-to-shop {
  margin-bottom: 20px;
  font-size: 14px;
  text-decoration: none;
  color: black;
}

.bucket__title {
  font-size: 25px;
  flex: 1;
}

.bucket__order {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.bucket__list {
  width: 80%;
}

.bucket__item {
  display: flex;
  flex-direction: row;
  background-color: white;
  border-radius: 20px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.3);
  gap: 20px;
}

.bucket__img {
  width: 200px;
  height: 200px;
}

.bucket__btns {
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  width: 200px;
}

.bucket__price {
  font-size: 20px;
  margin-left: 8%;
  margin-right: 8%;
}

.bucket__count {
  font-size: 18px;
  margin-left: 2%;
  margin-right: 2%;
}

.place-an-order {
  width: 15%;
  padding-bottom: 20px;
}

.place-an-order--fixed {
  background-color: white;
  border-radius: 20px;
  box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.3);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 280px;
  padding: 20px;
  position: sticky;
  top: 100px;
  right: 100px;
}

.place-an-order__title {
  font-size: 23px;
}

.check__item {
  max-width: 30px !important;
  display: flex;
  align-items: center;
}

.sold-out {
  border: 2px solid red; /* Измените стиль на ваш выбор */
}
</style>
