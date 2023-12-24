<template>
  <div class="catalog">
    <my-header />
    <div class="catalog__content">
      <div class="filters">
        <div class="catalog__name" v-if="this.categoryName !== ''">{{ this.categoryName }}</div>
        <div class="catalog__name" v-else>Каталог</div>
        <v-select
          v-model="filter"
          :items="filters"
          variant="underlined"
          label="Сортировать по"
          class="my-select"
        >
        </v-select>
      </div>
      <div class="container">
        <div class="main">
          <div class="utilsOfCatalogAside">
            <div class="utilsOfCatalogAside__filters">
              <div class="filters__title">Фильтры</div>
              <div class="filters__price">
                <div class="price__title">Цена</div>
                <div class="price__inputs">
                  <v-text-field
                    v-model="selectedFilters[0].from"
                    type="number"
                    placeholder="0"
                    required
                    variant="outlined"
                    hide-details="auto"
                  ></v-text-field>
                  -
                  <v-text-field
                    v-model="selectedFilters[0].to"
                    type="number"
                    placeholder="100000"
                    required
                    variant="outlined"
                    hide-details="auto"
                  ></v-text-field>
                </div>
              </div>
              <div
                class="filters__other"
                v-for="(filter, index) in dataOfCharacteristic"
                :key="index"
              >
                <v-expansion-panels>
                  <v-expansion-panel>
                    <v-expansion-panel-title>
                      {{ filter.name }}
                    </v-expansion-panel-title>
                    <v-expansion-panel-text>
                      <div class="filter__isRange" v-if="filter.isRange">
                        <v-text-field
                          v-model="selectedFilters[index + 1].from"
                          type="number"
                          placeholder="0"
                          required
                          variant="outlined"
                          hide-details="auto"
                        ></v-text-field>
                        -
                        <v-text-field
                          v-model="selectedFilters[index + 1].to"
                          type="number"
                          placeholder="100000"
                          required
                          variant="outlined"
                          hide-details="auto"
                        ></v-text-field>
                      </div>
                      <div class="filter__noRange" v-else>
                        <!-- eslint ругается-->
                        <template
                          v-for="(property, jndex) in filter.properties"
                          :key="jndex"
                        >
                          <v-checkbox
                            v-model="selectedFilters[index + 1].properties"
                            :label="property.value"
                            :value="property.value"
                            hide-details="auto"
                          ></v-checkbox>
                        </template>
                      </div>
                    </v-expansion-panel-text>
                  </v-expansion-panel>
                </v-expansion-panels>
              </div>
              <div class="filters__btn">
                <v-btn
                  variant="elevated"
                  color="orange"
                  class="ma-3 admin__btns"
                  @click="filtersApply"
                >
                  Применить
                </v-btn>
              </div>
            </div>
          </div>
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
import MyHeader from "@/components/Header/MyHeader.vue";
import Items from "@/components/Items/Items.vue";
import MyFooter from "../components/Footer/MyFooter.vue";
import {
  getCurrentBatchProducts,
  getCharacteristic,
  getCatalog,
} from "@/API/index.js";

export default {
  components: {
    MyHeader,
    Items,
    MyFooter,
  },

  data: () => ({
    page: 1,
    categories: 0,
    filters: [
      "Сначала подешевле",
      "Сначала подороже",
      "По скидке",
      "По рейтингу",
    ],
    filter: "По рейтингу",
    items: [],
    order: 4,
    queryArray: [],
    price: "",

    catalog: [],
    categoryName: "",

    dataOfCharacteristic: [],

    //filters//
    priceFrom: null,
    priceTo: null,

    selectedFilters: [{ name: "price", from: "", to: "" }],
    ///////////
  }),

  beforeCreate() {
    console.log(this.$route);

    getCatalog().then((data) => {
      this.categoryName = this.findCategoryNameById(
        this.$route.params.id,
        data
      );
      //console.log(data);
      //console.log(this.$route.params.id);
      //console.log(this.categoryName);
    });

    this.page = 1;
    this.$router.push({
      query: { page: this.page, order: 4 },
    });

    getCharacteristic(this.$route.params.id).then((res) => {
      this.dataOfCharacteristic = res;
      //console.log(this.dataOfCharacteristic);

      res.forEach((characteristic) => {
        let temp = { name: characteristic.name };
        if (characteristic.isRange) {
          temp.from = "";
          temp.to = "";
          temp.id = characteristic.id;
        } else {
          temp.properties = [];
          temp.id = characteristic.id;
        }
        this.selectedFilters.push(temp);
      });
    });
  },

  methods: {
    async load({ done }) {
      try {
        const url =
          this.page === 1
            ? this.$route.fullPath
            : this.$route.fullPath.replace(/page=\d+/, `page=${this.page}`);
        const data = await getCurrentBatchProducts(url);

        //console.log(toRaw(this.items));
        //console.log(data);

        if (data.length === 0) {
          done("empty");
        } else {
          if (this.items.find((item) => item.id === data[0].id)) {
            //console.log("load", this.page);
            this.items = this.items.filter(
              (a) => !data.map((b) => b.id).includes(a.id)
            );
            this.updateRoute(this.page);
            this.page = 2;
          } else {
            //console.log("load", this.page);
            this.items.push(...data);
            this.updateRoute(this.page);
            this.page++;
          }
          done("ok");
        }
      } catch (error) {
        console.error("Error loading data:", error);
        done("error");
      }
    },

    filtersApply() {
      let queryString = "";
      this.price = "";
      this.selectedFilters.forEach((filter, index) => {
        if (index === 0) {
          if (filter.from != "" || filter.to != "") {
            filter.from != ""
              ? (this.price += `${filter.from}`)
              : (this.price += `_`);
            filter.to != ""
              ? (this.price += `-${filter.to}`)
              : (this.price += `-_`);
          }
        } else {
          if (filter.properties != undefined) {
            // no range
            if (filter.properties.length !== 0) {
              queryString += `&filter=${filter.name}`;

              let selecFil = this.dataOfCharacteristic.find(
                (fil) => fil.id === filter.id
              );

              //console.log(selecFil)
              filter.properties.forEach((prop) => {
                let propId = selecFil.properties.find(
                  (p) => p.value === prop
                ).id;
                //console.log(propId)
                queryString += `-${propId}`;
              });
            }
          } else {
            // is range
            if (filter.from != "" || filter.to != "") {
              queryString += `&filter=${filter.name}`;
              filter.from != ""
                ? (queryString += `-${filter.from}`)
                : (queryString += `-_`);
              filter.to != ""
                ? (queryString += `-${filter.to}`)
                : (queryString += `-_`);
            }
          }
        }
      });
      queryString = queryString.replace(/\s/g, "_");
      this.queryArray = queryString.split("&filter=");
      this.queryArray.shift();
      //console.log(queryString);
      //console.log(this.queryArray);
      if (queryString !== "") {
        if (this.price != "") {
          this.$router.push({
            query: {
              page: 1,
              filter: this.queryArray,
              order: this.order,
              price: this.price,
            },
          });
        } else {
          this.$router.push({
            query: {
              page: 1,
              filter: this.queryArray,
              order: this.order,
            },
          });
        }
      } else {
        if (this.price != "") {
          this.$router.push({
            query: {
              page: 1,
              order: this.order,
              price: this.price,
            },
          });
        } else {
          this.$router.push({
            query: {
              page: 1,
              order: this.order,
            },
          });
        }
      }
    },

    updateRoute(page) {
      if (this.queryArray.length === 0) {
        this.$router.push({
          query: {
            page: page,
            order: this.order,
            price: this.price || undefined, // убираем пустую строку, если нет цены
          },
        });
      } else {
        this.$router.push({
          query: {
            page: page,
            filter: this.queryArray,
            order: this.order,
            price: this.price || undefined,
          },
        });
      }
    },

    findCategoryNameById(id, categories) {
      for (const category of categories) {
        if (category.id == id) {
          return category.name;
        }

        if (category.children && category.children.length > 0) {
          const childResult = this.findCategoryNameById(id, category.children);
          if (childResult) {
            return childResult;
          }
        }
      }

      return ''; // Категория не найдена
    },
  },

  watch: {
    $route(to, from) {
      getCatalog().then((data) => {
        this.categoryName = this.findCategoryNameById(
          this.$route.params.id,
          data
        );
        //console.log(data);
        //console.log(this.$route.params.id);
        //console.log(this.categoryName);
      });
      this.isAnotherCategory = false;
      //console.log("123", to, from);
      if (to.path !== from.path) {
        //console.log("change");
        this.filter = "По рейтингу";

        getCurrentBatchProducts(this.$route.fullPath).then((data) => {
          this.items = data;
          this.page = 2;
        });

        getCharacteristic(this.$route.params.id).then((res) => {
          this.dataOfCharacteristic = res;
          //console.log(this.dataOfCharacteristic);

          this.selectedFilters = [{ name: "price", from: "", to: "" }];
          this.queryArray = "";
          res.forEach((characteristic) => {
            let temp = { name: characteristic.name };
            if (characteristic.isRange) {
              temp.from = "";
              temp.to = "";
              temp.id = characteristic.id;
            } else {
              temp.properties = [];
              temp.id = characteristic.id;
            }
            this.selectedFilters.push(temp);
          });
        });
      } else if (to.path == from.path) {
        if (to.query.order != from.query.order) {
          //console.log("order");
          getCurrentBatchProducts(this.$route.fullPath).then((data) => {
            this.items = data;
            this.page = 2;
          });
        } else if (to.query.price != from.query.price) {
          //console.log("price");
          getCurrentBatchProducts(this.$route.fullPath).then((data) => {
            this.items = data;
            this.page = 2;
          });
        } else if (to.query.filter != from.query.filter) {
          //console.log("query");
          getCurrentBatchProducts(this.$route.fullPath).then((data) => {
            this.items = data;
            this.page = 2;
          });
        }
      }
    },

    filter(to, from) {
      this.order = this.filters.findIndex((value) => value === this.filter) + 1;
      //console.log("this.isAnotherCategory:", this.isAnotherCategory);

      this.updateRoute(1);
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

.catalog__name {
  font-size: 30px;
}

.filters {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 90%;
  margin: 0 auto;
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
  background-color: white;
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  margin-top: 24px;
  min-width: 300px;
  margin-bottom: 24px;
}

.filters__title {
  text-align: center;
  margin-top: 20px;
  font-size: 23px;
}

.filters__price {
  padding-bottom: 10px;
  margin: 0 10px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.3);
}

.v-expansion-panel {
  border-radius: 0 !important;
}

.price__title,
.filter__title {
  font-size: 18px;
}

.price__inputs,
.filter__isRange {
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
}

.filters__btn {
  text-align: center;
}
</style>
