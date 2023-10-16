<template>
  <v-hover v-slot="{ isHovering, props }">
    <div class="go-to-login__link" @click="handleClick">
      <v-sheet
        class="item d-flex rounded-lg ma-2 pa-4"
        v-bind="props"
        :elevation="isHovering ? 24 : 6"
      >
        <img :src="item.img" alt="" class="item__img" />
        <div class="item__text">
          <div class="item__text-content">
            <div class="item__title">{{ item.title }}</div>
            <div class="item__description">{{ item.description }}...</div>
          </div>

          <div class="price-btn">
            <v-btn variant="outlined" @click.stop="addItems">Купить </v-btn>
            <div class="item__price">Цена: {{ item.price }}</div>
          </div>
        </div>
      </v-sheet>
    </div>
  </v-hover>
</template>

<script>
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
      this.$store.commit("increment");

      this.$store.commit("setItem", this.item)
    },

    handleClick() {
      console.log("click");
      this.$router.push("/catalog/" + this.item.title);
    },
  },
};
</script>

<style>
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
  align-items: center;
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
