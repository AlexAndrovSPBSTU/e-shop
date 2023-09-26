<template>
  <v-carousel cycle hide-delimiters :show-arrows="false">
    <v-carousel-item v-for="(_, i) in 3" :key="i">
      <v-sheet height="100%">
        <img src="src\assets\welcome-img.png" alt="" class="carousel_img" />
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style>
</style>
