<template>
  <div class="bucket">
    <my-header />
    <div class="container">
      <div class="bucket__content">
        <router-link to="/catalog" class="back-to-shop">
          Вернуться к покупкам
        </router-link>
        <div class="bucket__title">Корзина</div>
        <div class="bucket__list">
          <div
            class="bucket__item"
            v-for="(item, index) in this.$store.state.bucket"
            :key="index"
          >
            <img :src="item.img" alt="" class="bucket__img" />
            <div class="bucket__title">{{ item.title }}</div>
            <div class="bucket__title">Total Count: {{ item.totalCount }}</div>
          </div>
        </div>
      </div>
    
    </div>
    <my-footer/>
  </div>
</template>

<script>
import MyFooter from '../Footer/MyFooter.vue';
import MyHeader from "../Header/MyHeader.vue";
export default {
  components: { MyHeader, MyFooter },
  data() {
    return {
      data: this.$store.state.bucket,
    };
  },

  /**
 * <div class="list" v-for="(item, index) in paginatedTickers" :key="index">
        <item-of-catalog :item="item" />
      </div>
 */
};
</script>

<style>
.bucket__content {
  margin-top: 120px;
}

.back-to-shop {
  margin-bottom: 20px;
  font-size: 14px;
  text-decoration: none;
  color: black;
}

.bucket__title {
  font-size: 35px;
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
  width: 100px;
}
</style>
