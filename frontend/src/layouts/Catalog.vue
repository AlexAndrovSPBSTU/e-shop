<template>
  <div class="catalog">
    <my-header/>
    <items/>
    <my-footer/>
  </div>
</template>

<script>
import MyHeader from "@/components/Header/MyHeader.vue";
import Items from "@/components/Items/Items.vue"
import MyFooter from '../components/Footer/MyFooter.vue';

export default {
  components: {
    MyHeader,
    Items,
    MyFooter,
  },
};
</script>

<style>
  
</style>
