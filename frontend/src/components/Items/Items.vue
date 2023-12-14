<template>
  <div class="items">
    <div class="container">
      <!--<div class="filters">
        <v-select 
          v-model="filter" 
          :items=filters
          variant="underlined" 
          label="Сортировать по"
          class="my-select"
        >
        </v-select>
      </div>-->
      <div class="list" v-for="(item, index) in items" :key="index">
        <item-of-catalog :item="item" />
      </div>

      <!--<div class="text-center">
        <v-container>
          <v-row justify="center">
            <v-col cols="8">
              <v-container class="max-width">
                <v-pagination
                  v-model="page"
                  class="my-4"
                  :length="Math.ceil(items.length / 6)"
                  :total-visible="4"
                ></v-pagination>
              </v-container>
            </v-col>
          </v-row>
        </v-container>
      </div>-->
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
  margin: 120px 0;
}

.filters{
  display: flex;
  justify-content: flex-end;
}

.my-select{
  max-width: 190px;
}
</style>
