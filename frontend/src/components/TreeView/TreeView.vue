<template>
  <v-list>
    <v-list-item v-for="(item, index) in children" :key="index">
      <v-list-item-title>
        <v-menu open-on-hover location="end">
          <template v-slot:activator="{ props }">
            <div v-bind="props" @click="goTO(item.id)" class="treeViewItem">
              {{ item.name }}
            </div>
          </template>

          <tree-view v-if="item.children" :children="item.children" />
        </v-menu>
      </v-list-item-title>
    </v-list-item>
  </v-list>
</template>

<script setup>
import { useRouter } from "vue-router";
defineProps({
  children: Array,
});

const router = useRouter();

function goTO(id) {
  router.push(`/categories/${id}/products?page=1&order=4`);
}
</script>

<style>
.treeViewItem{
  cursor: pointer;
}
</style>
