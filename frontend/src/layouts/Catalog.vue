<template>
  <div class="catalog">
    <my-header />
    <v-infinite-scroll
      class="catalog__infinite-scroll"
      :items="items"
      :onLoad="load"
    >
      <items :items="items" />
      <template v-slot:empty>
        <div></div>
      </template>
    </v-infinite-scroll>
    <my-footer />
  </div>
</template>

<script>
import { useRouter, useRoute } from "vue-router";
import MyHeader from "@/components/Header/MyHeader.vue";
import Items from "@/components/Items/Items.vue";
import MyFooter from "../components/Footer/MyFooter.vue";
import { getProducts, getCurrentBatchProducts } from "@/API/index.js";

export default {
  components: {
    MyHeader,
    Items,
    MyFooter,
  },

  data: () => ({
    page: 1,
    filters: ["Сначала подешевле", "Сначала подороже"],
    filter: "Сначала подешевле",
    items: [],
  }),

  
  methods: {
    async load({ done }) {
      console.log(2)
      await getCurrentBatchProducts(
        this.$route.fullPath,
        this.$route.query
      ).then((data) => {
        if (data.length === 0) {
          done("empty");
          this.$router.push({
            path: this.$route.path,
            query: { page: this.page },
          });
        } else {
          this.items.push(...data);
          this.$router.push({
            path: this.$route.path,
            query: { page: this.page++ },
          });
          done("ok");
        }
      });

    },
  },

  watch: {
    $route(to, from) {
      console.log(1)
      if (to.path !== from.path) {
        getCurrentBatchProducts(this.$route.fullPath, this.$route.query).then(
          (data) => {
            this.items = data;
            this.page = 2;
          }
        );
      }

      /*getCurrentBatchProducts(this.$route.fullPath, this.$route.query).then(
        (data) => {
          this.items = data;
        }
      );*/
    },
  },
};
</script>

<style>
.catalog {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.catalog__infinite-scroll {
  overflow-y: visible !important;
}
</style>
