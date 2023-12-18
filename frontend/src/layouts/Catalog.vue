<template>
  <div class="catalog">
    <my-header />
    <div class="catalog__content">
      <div class="utilsOfCatalogTop">
        <characteristic />
        {{dataOfCharacteristic[0]}}
        <div class="filters">
          <v-select
            v-model="filter"
            :items="filters"
            variant="underlined"
            label="Сортировать по"
            class="my-select"
          >
          </v-select>
        </div>
      </div>
      <div class="container">
        <div class="main">
          <div class="utilsOfCatalogAside">sdsd</div>
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
        </div>
      </div>
    </div>

    <my-footer />
  </div>
</template>

<script>
import { useRouter, useRoute } from "vue-router";
import MyHeader from "@/components/Header/MyHeader.vue";
import Items from "@/components/Items/Items.vue";
import MyFooter from "../components/Footer/MyFooter.vue";
import { getCurrentBatchProducts, getCharacteristic } from "@/API/index.js";

export default {
  components: {
    MyHeader,
    Items,
    MyFooter
  },

  data: () => ({
    page: 1,
    categories: 0,
    filters: ["Сначала подешевле", "Сначала подороже"],
    filter: "Сначала подешевле",
    items: [],

    dataOfCharacteristic: [],
  }),

  beforeCreate() {
    console.log("beforeCreate");
    this.page = 1;
    this.$router.push({
      query: { page: this.page },
    });


    getCharacteristic(this.$route.params.id).then((res) => {
      this.dataOfCharacteristic = res;
      console.log(this.dataOfCharacteristic)
    });
  },
  
  created() {
    //http://localhost:8080/categories/2/characteristics

    console.log(this.$route);
  },
  beforeMount() {
    console.log("beforeMount");
  },
  mounted() {
    console.log("mounted");
  },
  beforeUpdate() {
    console.log("beforeUpdate");
  },
  updated() {
    console.log("updated");
  },

  methods: {
    async load({ done }) {
      //console.log("load");

      if (this.page === 1) {
        await getCurrentBatchProducts(this.$route.fullPath).then((data) => {
          if (data.length === 0) {
            done("empty");
          } else {
            this.items.push(...data);
            this.page++;
            done("ok");
          }
        });
      } else {
        let updatedUrl = this.$route.fullPath.replace(
          /page=\d+/,
          `page=${this.page}`
        );
        //console.log(updatedUrl);

        await getCurrentBatchProducts(updatedUrl).then((data) => {
          if (data.length === 0) {
            done("empty");
          } else {
            this.items.push(...data);
            this.$router.push({
              path: this.$route.path,
              query: { page: this.page++ },
            });
            done("ok");
          }
        });
      }
    },
  },

  watch: {
    $route(to, from) {
      if (to.path !== from.path) {
        //console.log("change")
        getCurrentBatchProducts(this.$route.fullPath).then((data) => {
          this.items = data;
          this.page = 2;
        });

        getCharacteristic(this.$route.params.id).then((res) => {
          this.dataOfCharacteristic = res;
          console.log(this.dataOfCharacteristic)
        });
      }
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

.catalog__content {
  margin-top: 120px;
}

.utilsOfCatalogTop {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}

.catalog__infinite-scroll {
  overflow-y: visible !important;
}

.catalog__btns {
  color: white !important;
}

.main {
  display: flex;
  flex-direction: row;
}

.utilsOfCatalogAside {
  background-color: aqua;
  border-radius: 10px;
  margin-top: 24px;
}
</style>
