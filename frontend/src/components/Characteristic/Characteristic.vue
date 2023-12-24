<template>
  <div class="">
    <v-btn
      variant="elevated"
      color="orange"
      class="ma-3 admin__btns"
      @click="characteristicDialog = true"
    >
      Характеристики
    </v-btn>

    <v-dialog v-model="characteristicDialog" persistent width="300">
      <v-card class="d-flex justify-center align-center">
        <v-card-title> Характеристики </v-card-title>
        <v-card-text>
          <v-btn
            variant="elevated"
            color="orange"
            class="ma-2 admin__btns"
            :loading="loadingAdd"
            @click="characteristicAddDialogFunc"
          >
            Добавить
          </v-btn>
        </v-card-text>
        <v-card-text>
          <v-btn
            variant="elevated"
            color="orange"
            class="ma-2 admin__btns"
            :loading="loadingDel"
            @click="characteristicDelDialogFunc"
          >
            Удалить
          </v-btn>
        </v-card-text>
        <v-card-text>
          <v-btn
            variant="elevated"
            color="orange"
            class="ma-2 admin__btns"
            :loading="loadingEdit"
            @click="characteristicEditDialogFunc"
          >
            Переименовать
          </v-btn>
        </v-card-text>

        <v-card-actions>
          <v-btn
            color="black"
            variant="text"
            @click="characteristicDialog = false"
          >
            Закрыть
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!--Добавление характиристики-->
    <v-dialog
      class="dialog"
      v-model="characteristicAddDialog"
      persistent
      width="600"
    >
      <v-alert
        class="alertCharacteristicAdd"
        type="success"
        title="Успешно!"
        text="Характеристика добавлена"
        v-model="alertCharacteristicAdd"
        :disabled="!alertCharacteristicAdd"
      ></v-alert>
      <v-card>
        <v-form
          v-model="form"
          fast-fail
          @submit.prevent="addCharacteristicFunc"
        >
          <v-card-title>
            <span class="text-h5 ma-2">Добавление характеристики</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" sm="12" md="12">
                  <v-select
                    v-model="categories"
                    :items="listCategory"
                    :item-props="itemProps"
                    label="Категории"
                    hint="Выберите нужную категорию"
                    persistent-hint
                    @update:modelValue="requestForSpecificCharacteristic"
                  ></v-select>
                </v-col>
                <div
                  class="progressCircular__SpecificProducts"
                  v-if="loadingSpecificCharacteristic"
                >
                  <v-progress-circular
                    indeterminate
                    color="amber"
                    :size="50"
                    :width="5"
                  ></v-progress-circular>
                </div>
                <v-col cols="12" sm="12" md="12">
                  <div
                    class="select__SpecificProducts"
                    v-if="isLoadedSpecificCharacteristic"
                  >
                    <v-col cols="12" sm="12" md="12">
                      <v-text-field
                        v-model="newCategory"
                        label="Название*"
                        :rules="[rules.required]"
                        clearable
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col>
                      <v-switch
                        color="primary"
                        v-model="isRange"
                        label="Это диапазон?"
                      ></v-switch>
                    </v-col>
                    <div v-if="this.isRange">
                      <v-col>
                        <v-text-field
                          v-model="from"
                          type="number"
                          clearable
                          label="От*"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col>
                        <v-text-field
                          v-model="to"
                          type="number"
                          clearable
                          label="До*"
                          required
                        ></v-text-field>
                      </v-col>
                    </div>
                  </div>
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
              @click="characteristicDialogClose"
            >
              Закрыть
            </v-btn>
            <v-btn
              :loading="loading"
              color="blue-darken-1"
              variant="text"
              type="submit"
            >
              Добавить
            </v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <!--Удаление характиристики-->
    <v-dialog v-model="characteristicDelDialog" persistent width="600">
      <v-card>
        <v-form
          v-model="formDelCharacteristic"
          @submit.prevent="deleteCharacteristicFunc"
        >
          <v-card-title> Удалить характеристику </v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="12" sm="12" md="12">
                <v-select
                  v-model="categories"
                  :items="listCategory"
                  :item-props="itemProps"
                  label="Категории"
                  hint="Выберите нужную категорию"
                  persistent-hint
                  @update:modelValue="requestForSpecificCharacteristic"
                ></v-select>
              </v-col>
              <div
                class="progressCircular__SpecificProducts"
                v-if="loadingSpecificCharacteristic"
              >
                <v-progress-circular
                  indeterminate
                  color="amber"
                  :size="50"
                  :width="5"
                ></v-progress-circular>
              </div>
              <v-col cols="12" sm="12" md="12">
                <div class="select__SpecificProducts" v-if="categories">
                  <v-col cols="12" sm="12" md="12">
                    <v-select
                      v-model="characteristic"
                      :items="arrayCharacteristic"
                      :item-props="itemProps"
                      label="Категории"
                      hint="Выберите категорию, в которой собираетесь удалять"
                      persistent-hint
                      :rules="[rules.required]"
                    ></v-select>
                  </v-col>
                </div>
              </v-col>
            </v-row>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="characteristicDialogClose"
            >
              Закрыть
            </v-btn>
            <v-btn
              :disabled="!formDelCharacteristic"
              :loading="loadingFormDelCharacteristic"
              color="blue-darken-1"
              variant="text"
              type="submit"
            >
              Удалить
            </v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <!-- Переименование характиристики -->
    <v-dialog v-model="characteristicEditDialog" persistent width="600">
      <v-card>
        <v-form
          v-model="formEditCharacteristic"
          @submit.prevent="renameCharacteristicFunc"
        >
          <v-card-title> Переименовать характиристику </v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="12" sm="12" md="12">
                <v-select
                  v-model="categories"
                  :items="listCategory"
                  :item-props="itemProps"
                  label="Категории"
                  hint="Выберите нужную категорию"
                  persistent-hint
                  @update:modelValue="requestForSpecificCharacteristic"
                ></v-select>
              </v-col>
              <div
                class="progressCircular__SpecificProducts"
                v-if="loadingSpecificCharacteristic"
              >
                <v-progress-circular
                  indeterminate
                  color="amber"
                  :size="50"
                  :width="5"
                ></v-progress-circular>
              </div>
              <v-col cols="12" sm="12" md="12">
                <div class="select__SpecificProducts" v-if="categories">
                  <v-col cols="12" sm="12" md="12">
                    <v-select
                      v-model="characteristic"
                      :items="arrayCharacteristic"
                      :item-props="itemProps"
                      label="Характеристики"
                      hint="Выберите характиристику которую собираетесь переименовать"
                      persistent-hint
                      :rules="[rules.required]"
                    ></v-select>
                  </v-col>
                  <div
                    class="progressCircular__SpecificProducts"
                    v-if="characteristic"
                  >
                    <v-col cols="12" sm="12" md="12">
                      <v-text-field
                        v-model="renameCharacteristic"
                        label="Название*"
                        hint="Новое название"
                        persistent-hint
                        :rules="[rules.required]"
                        clearable
                        required
                      ></v-text-field>
                    </v-col>
                  </div>
                </div>
              </v-col>
            </v-row>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="characteristicDialogClose"
            >
              Закрыть
            </v-btn>
            <v-btn
              :disabled="!formEditCharacteristic"
              :loading="loadingFormEditCharacteristic"
              color="blue-darken-1"
              variant="text"
              type="submit"
            >
              Переименовать
            </v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import {
  addCharacteristic,
  getCatalog,
  getCharacteristic,
  deleteCharacteristic,
  renameCharacteristic,
} from "@/API/index.js";
import { getUniqueItem } from "@/utils/index.js";

export default {
  data: () => ({
    form: false,
    formDelCharacteristic: false,

    characteristicDialog: false,

    characteristicAddDialog: false,
    characteristicDelDialog: false,
    characteristicEditDialog: false,

    alertCharacteristicAdd: false,

    newCategory: "",

    isRange: false,
    from: null,
    to: null,

    data: [],
    listCategory: null,
    categories: null,
    arrayCharacteristic: [],
    characteristic: null,

    formDelCharacteristic: false,
    loadingFormDelCharacteristic: false,

    formEditCharacteristic: false,
    loadingFormEditCharacteristic: false,
    renameCharacteristic: null,

    loadingCharacteristicAdd: false,
    loadingCharacteristicEdit: false,
    loadingCharacteristicDel: false,

    loadingSpecificCharacteristic: false,
    specificCharacteristic: null,
    isLoadedSpecificCharacteristic: false,

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

    async characteristicAddDialogFunc() {
      this.loadingCharacteristicAdd = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingCharacteristicAdd = false;
        this.characteristicAddDialog = true;
      });
    },

    async characteristicDelDialogFunc() {
      this.loadingCharacteristicAdd = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingCharacteristicAdd = false;
        this.characteristicDelDialog = true;
      });
    },

    async characteristicEditDialogFunc() {
      this.loadingCharacteristicAdd = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingCharacteristicAdd = false;
        this.characteristicEditDialog = true;
      });
    },

    requestForSpecificCharacteristic() {
      this.loadingSpecificCharacteristic = true;
      this.specificCharacteristic = "";
      this.renameCharacteristic = null;

      getCharacteristic(this.categories.id).then((data) => {
        this.arrayCharacteristic = data;
        this.loadingSpecificCharacteristic = false;
        this.isLoadedSpecificCharacteristic = true;
      });
    },

    async addCharacteristicFunc() {
      if (this.isRange === true && (this.from == null || this.to == null)) {
        console.log(1);
        return;
      }

      if (Number(this.from) > Number(this.to)) {
        let swap = this.to;
        this.to = this.from;
        this.from = swap;
      }

      let newCharacteristic = {
        name: this.newCategory,
        isRange: this.isRange,
        from: Number(this.from),
        to: Number(this.to),
      };

      await addCharacteristic(newCharacteristic, this.categories.id).then(
        (res) => {
          this.newCategory = "";
          this.isRange = false;
          this.from = null;
          this.to = null;
          this.categories = null;
          this.isLoadedSpecificCharacteristic = false;
        }
      );
    },

    async deleteCharacteristicFunc() {
      this.loadingFormDelCharacteristic = true;

      await deleteCharacteristic(this.characteristic.id).then((res) => {
        this.characteristic = null;
        this.categories = null;
        this.loadingFormDelCharacteristic = false;
        this.isLoadedSpecificCharacteristic = false;
      });
    },

    async renameCharacteristicFunc() {
      this.loadingFormEditCharacteristic = true;

      await renameCharacteristic(
        this.renameCharacteristic,
        this.characteristic.id
      ).then((res) => {
        this.characteristic = null;
        this.categories = null;
        this.renameCharacteristic = null;
        this.loadingFormEditCharacteristic = false;
        this.isLoadedSpecificCharacteristic = false;
      });
    },

    characteristicDialogClose() {
      this.categories = null;
      this.isLoadedSpecificCharacteristic = false;

      this.characteristicAddDialog === true
        ? ((this.newCategory = ""),
          (this.isRange = false),
          (this.from = null),
          (this.to = null),
          (this.characteristicAddDialog = false))
        : this.characteristicDelDialog === true
        ? ((this.characteristic = null),
          (this.loadingFormDelCharacteristic = false),
          (this.characteristicDelDialog = false))
        : ((this.characteristic = null),
          (this.characteristicEditDialog = false),
          (this.renameCharacteristic = null),
          (this.loadingFormEditCharacteristic = false));
    },
  },
};
</script>

<style></style>
