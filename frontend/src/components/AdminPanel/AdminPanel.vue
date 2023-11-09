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
              :loading="loadingDel"
              @click="delDialog"
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
              @click="editDialog"
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

      <!-- Добавление нового товара -->
      <v-dialog
        class="dialog"
        v-model="productAddDialog"
        persistent
        width="1024"
      >
        <v-alert
          class="alertAdd"
          type="success"
          title="Успешно!"
          text="Товар добавлен"
          v-model="alertProductAdd"
          :disabled="!alertProductAdd"
        ></v-alert>
        <v-card>
          <v-form v-model="form" @submit.prevent="write">
            <v-card-title>
              <span class="text-h5 ma-2">Добавление товара</span>
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

      <!-- Удаление товара -->
      <v-dialog
        class="dialog"
        v-model="productDeleteDialog"
        persistent
        width="600"
      >
        <v-alert
          class="alertDel"
          type="success"
          title="Успешно!"
          text="Товар удален"
          v-model="alertProductDel"
          :disabled="!alertProductDel"
        ></v-alert>
        <v-card>
          <v-card-title> Удаление товара </v-card-title>
          <v-card-text>
            <v-select
              v-model="categories"
              :items="[
                'Alabama',
                'Alaska',
                'American Samoa',
                'Arizona',
                'Arkansas',
                'California',
              ]"
              label="Категории"
              hint="Выберите нужную категорию"
              persistent-hint
              @update:modelValue="requestForSpecificProducts"
            ></v-select>
            <div
              class="progressCircular__SpecificProducts"
              v-if="loadingSpecificProducts"
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
                v-model="specificProducts"
                :items="products"
                :item-props="itemProps"
                label="Товар"
                hint="Выберите товар, который собираетесь удалить"
                persistent-hint
              ></v-select>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="closeEditDelDialog"
            >
              Закрыть
            </v-btn>
            <v-btn
              color="blue-darken-1"
              variant="text"
              :disabled="!specificProducts"
              @click="deleteProduct"
            >
              Удалить
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <!-- Редактирование товара -->
      <v-dialog
        class="dialog"
        v-model="productEditDialog"
        persistent
        width="800"
      >
        <v-alert
          class="alertEdit"
          type="success"
          title="Успешно!"
          text="Товар изменен"
          v-model="alertProductEdit"
          :disabled="!alertProductEdit"
        ></v-alert>
        <v-card>
          <v-card-title> Изменить товар </v-card-title>
          <v-card-text>
            <v-select
              v-model="categories"
              :items="[
                'Alabama',
                'Alaska',
                'American Samoa',
                'Arizona',
                'Arkansas',
                'California',
              ]"
              label="Категории"
              hint="Выберите нужную категорию"
              persistent-hint
              @update:modelValue="requestForSpecificProducts"
            ></v-select>
            <div
              class="progressCircular__SpecificProducts"
              v-if="loadingSpecificProducts"
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
                v-model="specificProducts"
                :items="products"
                :item-props="itemProps"
                label="Товар"
                hint="Выберите товар, который собираетесь изменить"
                persistent-hint
                @update:modelValue="changeEditProduct"
              ></v-select>
            </div>
            <div class="edit__SpecificProducts" v-if="specificProducts">
              <v-row class="mt-4">
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    v-model="nowEditProduct.name"
                    :placeholder="specificProducts.name.toString()"
                    clearable
                    hint="Название продукта"
                    persistent-hint
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    v-model="nowEditProduct.price"
                    type="number"
                    :placeholder="specificProducts.price.toString()"
                    clearable
                    hint="Цена продукта"
                    persistent-hint
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    v-model="nowEditProduct.amount"
                    type="number"
                    :placeholder="specificProducts.amount.toString()"
                    clearable
                    hint="Количество на складе"
                    persistent-hint
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    v-model="nowEditProduct.discount"
                    type="number"
                    :placeholder="specificProducts.discount.toString()"
                    clearable
                    hint="Скидка для зарегистрированных пользователей"
                    persistent-hint
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <!-- тут подумать над тем, как в описание передать модель коммента, мб создавать копию эдита перед запуском меню -->
                  <v-textarea
                    bg-color="white"
                    color="black"
                    label="Описание товара*"
                    v-model="nowEditProduct.description"
                    rows="8"
                    row-height="30"
                    clearable
                  ></v-textarea>
                </v-col>
                <v-col cols="12" class="edit-photo__imgs">
                  <div
                    class="edit-photo__imgs-container"
                    v-for="photo in nowEditProduct.photos"
                    :key="photo"
                  >
                    <img class="edit-photo__img" :src="photo" />
                    <div class="photo__delete">
                      <v-btn
                        class="photo__delete-btn"
                        density="compact"
                        size="large"
                        icon="mdi-plus"
                        variant="text"
                        @click.stop="delPhoto(photo)"
                      />
                    </div>
                  </div>
                </v-col>
                <v-col cols="12" class="edit-Addphoto__imgs">
                  <v-file-input
                    label="Новые фотографии товара"
                    multiple
                    v-model="nowEditProduct.photos"
                  >
                    
                    <!--Допилить загрузку фото!!!!-->
                  </v-file-input>

                  <v-btn
                    color="blue-darken-1"
                    variant="text"
                    @click="closeEditDelDialog"
                  >
                    Загрузить
                  </v-btn>
                </v-col>
              </v-row>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="closeEditDelDialog"
            >
              Закрыть
            </v-btn>
            <v-btn
              color="blue-darken-1"
              variant="text"
              :disabled="!specificProducts"
              @click="productEditDialog = false"
            >
              Изменить
            </v-btn>
            <!-- Доделать с 400 строки, прикрутить логику редактирования -->
          </v-card-actions>
        </v-card>
      </v-dialog>
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
    productAddDialog: false, //add
    productDeleteDialog: false, //del
    productEditDialog: true, //edit

    catalogDialog: false,
    categoryAddDialog: false,
    categoryDeleteDialog: false,
    categoryEditDialog: false,
    categoryMoveDialog: false,

    categories: "", //del, edit
    specificProducts: null, //del, edit
    products: [
      {
        name: "Alabama",
        id: 1,
        photos: [
          "https://storage.yandexcloud.net/e-shop/121.jpg",
          "https://storage.yandexcloud.net/e-shop/122.jpg",
          "https://storage.yandexcloud.net/e-shop/123.jpg",
          "https://storage.yandexcloud.net/e-shop/124.jpg",
          "https://storage.yandexcloud.net/e-shop/125.jpg",
        ],
        description:
          "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius? Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?",
        price: 999,
        amount: 999,
        discount: 5,
      },
      {
        name: "Alaska",
        id: 2,
        photos: ["https://storage.yandexcloud.net/e-shop/123.jpg"],
        description:
          "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius? Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?",
        price: 999,
        amount: 999,
        discount: 5,
      },
      {
        name: "American Samoa",
        id: 3,
        photos: ["https://storage.yandexcloud.net/e-shop/123.jpg"],
        description:
          "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius? Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?",
        price: 999,
        amount: 999,
        discount: 5,
      },
      {
        name: "Arizona",
        id: 4,
        photos: ["https://storage.yandexcloud.net/e-shop/123.jpg"],
        description:
          "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius? Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?",
        price: 999,
        amount: 999,
        discount: 5,
      },
    ], //del, edit

    nowEditProduct: {
      name: null,
      price: null,
      amount: null,
      description: null,
      photos: [],
      discount: null,
    },

    product: {
      name: null,
      price: null,
      amount: null,
      description: null,
      photos: [],
      discount: null,
    }, //add

    form: false, //add
    loading: false, //add
    loadingDel: false, //del
    loadingEdit: false, //edit
    loadingSpecificProducts: false, //del, edit
    isLoadedSpecificProducts: false, //del, edit
    alertProductAdd: false, //add
    alertProductDel: false, //del
    alertProductEdit: false, //edit

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

      this.loading = false;

      this.alertProductAdd = true;
      setTimeout(() => (this.alertProductAdd = false), 1000);
    },

    delDialog() {
      this.loadingDel = true;
      // послать запрос на все категории, включить кружок загрузки
      // как только загрузилось, показываем окно с удалением
      setTimeout(() => {
        this.loadingDel = false;
        this.productDeleteDialog = true;
      }, 500);
    },

    requestForSpecificProducts() {
      this.loadingSpecificProducts = true;
      setTimeout(() => {
        this.loadingSpecificProducts = false;
        this.isLoadedSpecificProducts = true;
      }, 1000);
      // послать запрос на продукты из этой категории, включить кружок загрузки
      // как только загрузилось, показываем новой селект с продуктами
      // если пользователь выбрал товар, показать кнопку на удаление
    },

    async deleteProduct() {
      // послать запрос на удаление по айдишнику
      // вернуть все состояния в исходные
      this.categories = ""; //del

      this.alertProductDel = true;
      this.isLoadedSpecificProducts = false;
      this.specificProducts = null;
      setTimeout(() => (this.alertProductDel = false), 2000);
    },

    itemProps(item) {
      return {
        title: item.name,
      };
    },

    closeEditDelDialog() {
      this.categories = "";
      this.specificProducts = null;
      this.isLoadedSpecificProducts = false;
      this.productEditDialog === true
        ? (this.productEditDialog = false)
        : (this.productDeleteDialog = false);
    },

    editDialog() {
      this.loadingEdit = true;
      // послать запрос на все категории, включить кружок загрузки
      // как только загрузилось, показываем окно с удалением
      setTimeout(() => {
        this.loadingEdit = false;
        this.productEditDialog = true;
      }, 500);

      /*if (this.productEditDialog) {
        this.nowEditProduct.description = this.specificProducts.description;
        this.nowEditProduct.photos = this.specificProducts.photos;
      }*/
    },

    changeEditProduct(event) {
      this.nowEditProduct = { ...event };
      console.log(this.nowEditProduct);
    },

    delPhoto(photo) {
      console.log(photo);
      //this.nowEditProduct.photos.forEach(v => console.log(v))
      this.nowEditProduct.photos = this.nowEditProduct.photos.filter(
        (delPhoto) => delPhoto !== photo
      );
    },
  },
};
</script>

<style>
.admin__btns {
  color: white !important;
  width: 200px;
}

.alertAdd,
.alertDel {
  position: absolute !important;
  z-index: 99999999;
  animation: ani 2s cubic-bezier(0.12, 0.44, 0.46, 0.99);
}

.alertAdd {
  bottom: 563px;
}

.alertDel {
  bottom: 120px;
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

.progressCircular__SpecificProducts {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin: 20px 0;
}

.select__SpecificProducts {
  margin: 50px 0 0 0;
}

.edit-photo__imgs {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

.edit-photo__imgs-container {
  height: 140px;
  margin: 5px;
}

.edit-photo__img {
  max-width: 200px;
  height: 140px;
}

.edit-Addphoto__imgs {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.photo__delete {
  position: relative;
  top: -145px;
  display: flex;
  justify-content: flex-end;
  z-index: 1000;
}

.photo__delete-btn {
  text-shadow: 1px 1px 1px rgba(0, 0, 0);
  color: white;
  transform: rotate(45deg);
  font-size: 24px;
}
</style>
