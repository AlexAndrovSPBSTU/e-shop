<template>
  <div class="">
    <v-btn
      variant="elevated"
      color="orange"
      class="ma-3 admin__btns"
      @click="propertyDialog = true"
    >
      Свойства
    </v-btn>

    <v-dialog v-model="propertyDialog" persistent width="300">
      <v-card class="d-flex justify-center align-center">
        <v-card-title> Добавить свойство в товар </v-card-title>
        <v-card-text>
          <v-btn
            variant="elevated"
            color="orange"
            class="ma-2 admin__btns"
            :loading="loadingAdd"
            @click="propertyAddInProductDialog"
          >
            Добавить
          </v-btn>
        </v-card-text>
        <v-card-actions>
          <v-btn color="black" variant="text" @click="propertyDialog = false">
            Закрыть
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!--Добавление свойства-->
    <v-dialog
      class="dialog"
      v-model="propertyAddInProduct"
      persistent
      width="600"
    >
      <v-alert
        class="alertPropertyAdd"
        type="success"
        title="Успешно!"
        text="Товар добавлен"
        v-model="alertPropertyAdd"
        :disabled="!alertPropertyAdd"
      ></v-alert>
      <v-card>
        <v-card-title>
          <span class="text-h5 ma-2">Добавление свойства в товар</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="12" md="12">
                <v-card-text>
                  <v-select
                    v-model="categories"
                    :items="listCategory"
                    :item-props="itemProps"
                    label="Категории"
                    hint="Выберите нужную категорию"
                    persistent-hint
                    @update:modelValue="
                      requestForSpecificProductsAndCharacteristic
                    "
                  ></v-select>
                  <div
                    class="progressCircular__SpecificProducts"
                    v-if="loadingSpecificProductsAndCharacteristic"
                  >
                    <v-progress-circular
                      indeterminate
                      color="amber"
                      :size="50"
                      :width="5"
                    ></v-progress-circular>
                  </div>
                  <div
                    class="select__SpecificProducts"
                    v-if="isLoadedSpecificProducts"
                  >
                    <v-select
                      v-model="specificProduct"
                      :items="products"
                      :item-props="itemProps"
                      label="Товар"
                      hint="Выберите товар, в который собираетесь добавлять свойство"
                      persistent-hint
                      @update:modelValue="requestForSpecificProducts"
                    ></v-select>
                  </div>
                  <div class="select__SpecificProducts" v-if="specificProduct">
                    <v-select
                      v-model="specificCharacteristic"
                      :items="characteristics"
                      :item-props="itemProps"
                      label="Характеристики"
                      hint="Выберите характеристику, из который будете выбирать свойство"
                      persistent-hint
                      @update:modelValue="requestForSpecificCharacteristic"
                    ></v-select>
                  </div>
                  <div
                    class="select__SpecificProducts"
                    v-if="specificCharacteristic"
                  >
                    <div
                      class="select__SpecificProducts"
                      v-if="!specificCharacteristic.isRange"
                    >
                      <v-select
                        v-model="specificProperty"
                        :items="specificCharacteristic.properties"
                        :item-props="itemPropsProperty"
                        label="Свойства"
                        hint="Выберите свойство, которое хотите добавить"
                        persistent-hint
                      ></v-select>
                    </div>
                    <div class="select__SpecificProducts" v-else>
                      <v-col>
                        <div class="">
                          От: {{ this.specificCharacteristic.from }} до
                          {{ this.specificCharacteristic.to }}
                        </div>
                        <v-text-field
                          v-model="specificProperty"
                          type="number"
                          clearable
                          label="До*"
                          required
                        ></v-text-field>
                      </v-col>
                    </div>
                  </div>
                </v-card-text>
              </v-col>
            </v-row>
          </v-container>
          <small>*обязательные поля</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue-darken-1"
            variant="text"
            @click="propertyAddDialogClose"
          >
            Закрыть
          </v-btn>
          <v-btn
            :disabled="!specificProperty"
            :loading="loading"
            color="blue-darken-1"
            variant="text"
            @click="addPropertyInProductFunc"
          >
            Добавить
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import {
  getCatalog,
  getAllProducts,
  addPropertyInProduct,
  getCharacteristic,
} from "@/API/index.js";
import { getUniqueItem } from "@/utils/index.js";

export default {
  props: {
    idCategory: {
      type: Number,
      required: true,
    },
  },

  data: () => ({
    propertyDialog: false,
    propertyAddInProduct: false,
    alertPropertyAdd: false,

    categories: null,
    specificProduct: null,
    specificCharacteristic: null,
    specificProperty: null,

    listCategory: [],
    products: [],
    characteristics: [],
    properties: [],

    loading: false,
    loadingSpecificProductsAndCharacteristic: false,

    rules: {
      required: (value) => {
        return !!value || "Поле должно быть заполнено";
      },
    },
  }),

  methods: {
    itemProps(item) {
      return {
        title: item.name,
      };
    },

    itemPropsProperty(item) {
      return {
        title: item.value,
      };
    },

    async propertyAddInProductDialog() {
      this.loading = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loading = false;
        this.propertyAddInProduct = true;
      });
    },

    async requestForSpecificProductsAndCharacteristic() {
      this.loadingSpecificProductsAndCharacteristic = true;

      this.specificProduct = null;
      this.specificCharacteristic = null;
      this.specificProperty = null;

      await getAllProducts(this.categories.id).then((data) => {
        this.products = data;
        this.isLoadedSpecificProducts = true;
      });

      await getCharacteristic(this.categories.id).then((data) => {
        this.characteristics = data;
        this.isLoadedSpecificCharacteristic = true;

        this.loadingSpecificProductsAndCharacteristic = false;
      });
    },

    requestForSpecificProducts() {
      this.specificCharacteristic = null;
      this.specificProperty = null;
    },

    requestForSpecificCharacteristic() {
      this.specificProperty = null;
    },

    propertyAddDialogClose() {
      this.propertyAddInProduct = false;

      this.categories = null;
      this.specificProduct = null;
      this.specificCharacteristic = null;
      this.specificProperty = null;
    },

    async addPropertyInProductFunc() {
      this.loading = true;

      let idProp = this.specificCharacteristic.properties.find(
        (v) => v.value === this.specificProperty
      );

      await addPropertyInProduct(this.specificProduct.id, idProp.id).then((data) => {
        this.loading = false;
        this.categories = null;
        this.specificProduct = null;
        this.specificCharacteristic = null;
        this.specificProperty = null;
      });
    },
  },
};
</script>

<style>
.property__btns {
  color: white !important;
  margin-right: 20px;
}
</style>
