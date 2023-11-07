<template>
  <v-list>
    <v-list-item v-for="(item, index) in items" :key="index">
      <v-list-item-title>
        <v-menu open-on-hover location="end">
          <template v-slot:activator="{ props }">
            <div v-bind="props">{{ item.title }}</div>
          </template>

          <tree-view v-if="item.items" :items="item.items" />
        </v-menu>
      </v-list-item-title>
    </v-list-item>
  </v-list>
</template>

<script setup>
defineProps({
  items: Array,
});
</script>

<style></style>
