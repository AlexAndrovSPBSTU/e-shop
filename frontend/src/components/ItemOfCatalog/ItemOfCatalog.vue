<template>
  <v-hover v-slot="{ isHovering, props }">
    <div class="go-to-login__link" @click="handleClick">
      <v-sheet
        class="item d-flex rounded-lg ma-2 pa-4"
        v-bind="props"
        :elevation="isHovering ? 24 : 6"
      >
        <img
          :src="item.photos[0].url"
          alt=""
          class="item__img"
          v-if="item.photos[0]"
        />
        <div class="item__text">
          <div class="item__text-content">
            <div class="item__title">{{ item.name }}</div>
            <div class="item__description">{{ item.description }}...</div>
          </div>

          <div class="price-btn">
            <div class="" v-if="item.amount < 1">
              <v-btn disabled variant="outlined" @click.stop="addItems"
                >Нет в наличии
              </v-btn>
            </div>
            <div class="" v-else>
              <v-btn variant="outlined" @click.stop="addItems">Купить</v-btn>
            </div>

            <div class="item__price">
              Цена:
              {{
                this.$store.state.initialState.status.loggedIn &&
                item.discount !== 0
                  ? (item.price * ((100 - item.discount) / 100)).toFixed(0)
                  : item.price
              }}₽
            </div>
            <div
              v-if="
                this.$store.state.initialState.status.loggedIn &&
                item.discount !== 0
              "
              class="item__price--lineThrough"
            >
              {{ item.price }}₽
            </div>
          </div>
        </div>
      </v-sheet>
    </div>
  </v-hover>
</template>

<script>
import { addToCard, getCart, getProduct } from "@/API/index.js";

export default {
  props: {
    item: {
      type: Object,
      required: true,
      default: () => {},
    },
  },

  methods: {
    addItems() {
      const user = JSON.parse(localStorage.getItem("user"));
      if (user) {
        //http://localhost:8080/products/:productId
        getProduct(`/products/${this.item.id}`).then((res) => {
          if (res.amount < 1) {
            alert("Товар закончился");
            this.item.amount = 0;
          } else {
            addToCard(this.item.id).then(() => {
              getCart().then((res) => {
                console.log(res);
                this.$store.commit("setCount", res.totalCount);
              });
            });
          }
        });
      } else {
        this.$store.commit("setItem", this.item);
      }
      this.$store.commit("increment");
    },

    handleClick() {
      this.$router.push("/products/" + this.item.id);
    },
  },
};
</script>

<style scoped>
.item {
  flex-direction: row;

  gap: 40px;
}

.go-to-login__link {
  cursor: pointer;
}

.item__img {
  height: 250px;
  max-width: 200px;
}

.item__text {
  height: inherit;
  display: flex;
  justify-content: space-between;
  flex: 1;
}

.item__title {
  font-size: 26px;
  color: rgba(0, 0, 0, 0.8);
  margin-bottom: 10px;
}

.item__description {
  font-weight: 300;
}

.price-btn {
  display: flex;
  flex-direction: column-reverse;
  align-items: flex-end;
}

.item__price {
  white-space: nowrap;
}

.item__price--lineThrough {
  color: gray;
  text-decoration: line-through;
  text-decoration-style: solid;
  text-decoration-thickness: 1px;
}

@media (max-width: 750px) {
  .item {
    flex-direction: column;
  }
}

@media (max-width: 500px) {
  .item__text {
    flex-direction: column;
  }
  .price-btn {
    margin-top: 20px;
    flex-direction: row-reverse;
    justify-content: space-between;
  }
}
</style>
