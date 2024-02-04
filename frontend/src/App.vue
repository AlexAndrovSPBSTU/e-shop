<template>
  <router-view />
</template>

<script setup>
const mode = import.meta.env.MODE;
console.log("Режим работы приложения:", mode);
</script>

<style>
#app {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.container {
  height: 100%;
  width: 90%;
  margin: 0 auto;
}
</style>
