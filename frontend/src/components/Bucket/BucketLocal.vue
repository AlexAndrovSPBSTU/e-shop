<template>
  <div class="bucket__content">
    <div class="container">
      <router-link to="/categories/1/products?page=1&order=4" class="back-to-shop">
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
              <div class="bucket__price">
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
            <v-btn disabled class="place-an-order__btn">Оформить</v-btn>
            <p class="info__buy">Купить могут только авторизированные пользователи</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
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
.info__buy{
    font-size: 12px;
    text-align: center;
    color: red;
}
</style>
