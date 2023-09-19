<template>
  <div class="catalog">
    <my-header/>
    <items/>
  </div>
</template>

<script>
import MyHeader from "@/components/Header/MyHeader.vue";
import Items from "@/components/Items/Items.vue"

export default {
  components: {
    MyHeader,
    Items,
  },
};
</script>

<style>
  
</style>
