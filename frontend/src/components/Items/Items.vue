<template>
  <div class="items">
      <div class="list" v-for="(item, index) in items" :key="index">
        <item-of-catalog :item="item" />
      </div>
  </div>
</template>

<script setup>
import ItemOfCatalog from "@/components/ItemOfCatalog/ItemOfCatalog.vue";
import MyPagination from "../Pagination/MyPagination.vue";

defineProps({
  items: Array,
});

</script>

<style>
.items {
  flex: 1;
}

.my-select{
  max-width: 190px;
}
</style>
