<template>
  <v-hover v-slot="{ isHovering, props }">
    <router-link :to="'/catalog/' + item.title" class="go-to-login__link">
      <v-sheet
        class="item d-flex rounded-lg ma-2 pa-4"
        v-bind="props"
        :elevation="isHovering ? 24 : 6"
      >
        <img :src="item.img" alt="" class="item__img"/>
        <div class="item__title">{{ item.title }}</div>
        <div class="item__description">{{ item.description }}...</div>
        <div class="price-btn">
          <div class="item__price">Цена: {{ item.price }}</div>
          <v-btn variant="outlined"> Купить </v-btn>
        </div>
      </v-sheet>
    </router-link>
  </v-hover>
</template>

<script setup>
const props = defineProps({
  item: {
    type: Object,
    required: true,
    default: () => {},
  },
});
</script>

<style>
.item {
  flex-direction: column;
  align-items: center;
  max-width: 260px;
}

.item__img {
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
