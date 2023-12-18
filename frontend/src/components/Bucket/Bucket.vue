<template>
  <div class="bucket">
    <my-header />
    <div class="bucket__content">
      <div class="container">
        <router-link to="/categories/1/products" class="back-to-shop">
          Вернуться к покупкам
        </router-link>
        <div class="bucket__title">Корзина</div>

        <div class="bucket__order">
          <div class="bucket__list" v-if="this.$store.state.bucket.length == 0">
            Ваша корзина пуста
          </div>
          <div class="bucket__list" v-else>
            <div
              class="bucket__item"
              v-for="(item, index) in this.$store.state.bucket"
              :key="index"
            >
              <img :src="item.photos[0].url" alt="" class="bucket__img" />
              <div class="bucket__title">{{ item.name }}</div>

              <div class="bucket__btns">
                <v-btn
                  density="compact"
                  icon="mdi-minus"
                  variant="plain"
                  @click.stop="delItem(item)"
                />
                <div class="bucket__count">
                  {{ item.totalCount }}
                </div>
                <v-btn
                  density="compact"
                  icon="mdi-plus"
                  variant="plain"
                  @click.stop="addItem(item)"
                />
                
                <div
                  v-if="
                    this.$store.state.initialState.status.loggedIn &&
                    item.discount !== 0
                  "
                >
                  <div class="bucket__price">
                    {{ (item.totalCount * (item.price * ((100 - item.discount) / 100))).toFixed(0) }}
                  </div>
                </div>
                <div v-else class="bucket__price">
                  {{ item.totalCount * item.price }}
                </div>
                <v-btn
                  density="compact"
                  icon="mdi-trash-can"
                  variant="plain"
                  @click.stop="delAllItems(item)"
                />
              </div>
            </div>
          </div>
          <div class="place-an-order">
            <div class="place-an-order--fixed">
              <div class="place-an-order__title">Оформить заказ</div>
              <div class="place-an-order__sum">
                Сумма заказа: {{ $store.getters["getSumPrice"] }}
              </div>
              <div class="place-an-order__totalAmount">
                Количество товаров: {{ $store.getters["getAmountItem"] }}
              </div>
              <v-btn class="place-an-order__btn">Оформить</v-btn>
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
export default {
  components: { MyHeader, MyFooter },
  data() {
    return {
      data: this.$store.state.bucket,
    };
  },

  methods: {
    addItem(item) {
      this.$store.commit("increment");

      this.$store.commit("setItem", item);
    },

    delItem(item) {
      this.$store.commit("decrement");

      this.$store.commit("delItem", item);
    },

    delAllItems(item) {
      this.$store.commit("delAllItems", item);
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
  gap: 30px;
}

.bucket__img {
  width: 150px;
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
</style>
