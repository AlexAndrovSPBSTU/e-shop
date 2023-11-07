<template>
  <div v-if="this.$store.state.initialState.status.loggedIn">
    <div v-if="this.$store.state.initialState.user.role === 'ADMIN'">
      <v-btn
        variant="outlined"
        color="orange"
        class="text-none text-h6"
        @click="adminDialog = true"
      >
        Редактировать
      </v-btn>

      <v-dialog v-model="adminDialog" persistent width="300">
        <v-card class="d-flex justify-center align-center">
          <v-card-title> Панель Администратора </v-card-title>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="productsDialog = true"
            >
              Продукты
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="catalogDialog = true"
            >
              Каталог
            </v-btn>
          </v-card-text>

          <v-card-actions>
            <v-btn color="black" variant="text" @click="adminDialog = false">
              Закрыть
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="productsDialog" persistent width="300">
        <v-card class="d-flex justify-center align-center">
          <v-card-title> Продукты </v-card-title>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="productAddDialog = true"
            >
              Добавить
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="dialog3 = true"
            >
              Удалить
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="dialog3 = true"
            >
              Редактировать
            </v-btn>
          </v-card-text>

          <v-card-actions>
            <v-btn color="black" variant="text" @click="productsDialog = false">
              Закрыть
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="catalogDialog" persistent width="300">
        <v-card class="d-flex justify-center align-center">
          <v-card-title> Каталог </v-card-title>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="dialog2 = true"
            >
              Добавить
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="dialog3 = true"
            >
              Удалить
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="dialog3 = true"
            >
              Редактировать
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              @click="dialog3 = true"
            >
              Переместить
            </v-btn>
          </v-card-text>

          <v-card-actions>
            <v-btn color="black" variant="text" @click="catalogDialog = false">
              Закрыть
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog
        class="dialog"
        v-model="productAddDialog"
        persistent
        width="1024"
      >
        <v-alert
          class="alert"
          type="success"
          title="Успешно!"
          text="Товар добавлен"
          v-model="alert"
          :disabled="!alert"
        ></v-alert>
        <v-card>
          <v-form v-model="form" @submit.prevent="write">
            <v-card-title>
              <span class="text-h5 ma-2">Добавление продукта</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="product.name"
                      label="Название*"
                      :rules="[rules.required]"
                      clearable
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="product.price"
                      label="Цена*"
                      type="number"
                      :rules="[rules.required]"
                      clearable
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="product.amount"
                      label="Количество на складе*"
                      type="number"
                      :rules="[rules.required]"
                      clearable
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="product.discount"
                      label="Скидка*"
                      type="number"
                      :rules="[rules.required]"
                      clearable
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      bg-color="white"
                      color="black"
                      label="Описание товара*"
                      v-model="product.description"
                      :rules="[rules.required]"
                      clearable
                      required
                    ></v-textarea>
                  </v-col>
                  <v-col cols="12">
                    <v-file-input
                      label="Фотографии товара*"
                      multiple
                      v-model="product.photos"
                      :rules="[rules.required]"
                      required
                    ></v-file-input>
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
                @click="productAddDialog = false"
              >
                Закрыть
              </v-btn>
              <v-btn
                :disabled="!form"
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
      <!-- <add-product :AddDialog="productAddDialog"/> -->
    </div>
  </div>
</template>

<script>
import AddProduct from "./AddProduct/AddProduct.vue";
import uploadS3 from "@/S3_API/index.js";

export default {
  components: { AddProduct },
  data: () => ({
    adminDialog: false,
    productsDialog: false,
    productAddDialog: false,
    productDeleteDialog: false,
    productEditDialog: false,

    catalogDialog: false,
    categoryAddDialog: false,
    categoryDeleteDialog: false,
    categoryEditDialog: false,
    categoryMoveDialog: false,

    product: {
      name: null,
      price: null,
      amount: null,
      description: null,
      photos: [],
      discount: null,
    },

    form: false,
    loading: false,
    alert: false,

    rules: {
      required: (value) => !!value || "Поле должно быть заполнено",
    },
  }),

  methods: {
    async write() {
      if (!this.form) return;

      this.loading = true;

      let url = await uploadS3(this.product.photos);
      console.log(url);

      this.product.name = null;
      this.product.price = null;
      this.product.amount = null;
      this.product.description = null;
      this.product.discount = null;
      this.product.photos = [];

      this.loading = await false;

      this.alert = await true;
      await setTimeout(() => (this.alert = false), 2000);
    },
  },
};
</script>

<style>
.admin__btns {
  color: white !important;
  width: 200px;
}

.alert {
  position: absolute !important;
  z-index: 99999999;
  bottom: 557px;
  animation: ani 2s cubic-bezier(0.12, 0.44, 0.46, 0.99);
}

@keyframes ani {
  0%,
  100% {
    opacity: 0;
  }
  20% {
    opacity: 1;
  }
  50% {
    opacity: 1;
  }
}
</style>
