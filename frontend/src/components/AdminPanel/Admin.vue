<template>
  <div class="container__admin">
    <a @click="$router.back()" class="back-to-catalog"> Назад </a>
    <div class="admin">
      <div class="admin__title">Админ панель</div>
      <div class="admin__info">
        <div class="info__title">Администратор</div>
        <div class="info__id">id: {{ admin.id }}</div>
        <div class="info__name">Имя: {{ admin.name }}</div>
        <div class="info__surname">Фамилия: {{ admin.surname }}</div>
        <div class="info__mail">Почта: {{ admin.email }}</div>
      </div>

      <v-btn
        variant="elevated"
        color="orange"
        class="ma-3 admin__btns"
        @click="productsDialog = true"
      >
        Продукты
      </v-btn>

      <v-btn
        variant="elevated"
        color="orange"
        class="ma-3 admin__btns"
        @click="catalogDialog = true"
      >
        Каталог
      </v-btn>

      <v-btn
        variant="elevated"
        color="orange"
        class="ma-3 admin__btns"
        @click="propertyDialog = true"
      >
        Свойства
      </v-btn>

      <characteristic />


      <router-link to="/categories/1/products?page=1" class="back-to-catalog">
        Вернуться в каталог
      </router-link>

      <v-dialog v-model="productsDialog" persistent width="300">
        <v-card class="d-flex justify-center align-center">
          <v-card-title> Продукты </v-card-title>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              :loading="loadingAdd"
              @click="productAddDialogFunc"
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
              @click="productDelDialogFunc"
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
              @click="productEditDialogFunc"
            >
              Редактировать
            </v-btn>
          </v-card-text>
          
          <v-card-text>
            <characteristic-for-item/>
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
              :loading="loadingAddCategory"
              @click="categoryAddDialogFunc"
            >
              Добавить
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              :loading="loadingDelCategory"
              @click="categoryDelDialogFunc"
            >
              Удалить
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              :loading="loadingEditCategory"
              @click="categoryEditDialogFunc"
            >
              Переименовать
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              :loading="loadingMoveCategory"
              @click="categoryMoveDialogFunc"
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

      <v-dialog v-model="propertyDialog" persistent width="300">
        <v-card class="d-flex justify-center align-center">
          <v-card-title> Свойства </v-card-title>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              :loading="loadingPropertyAdd"
              @click="propertyAddDialogFunc"
            >
              Добавить
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              :loading="loadingPropertyDel"
              @click="propertyDelDialogFunc"
            >
              Удалить
            </v-btn>
          </v-card-text>
          <v-card-text>
            <v-btn
              variant="elevated"
              color="orange"
              class="ma-2 admin__btns"
              :loading="loadingPropertyRename"
              @click="propertyRenameDialogFunc"
            >
              Переименовать
            </v-btn>
          </v-card-text>
          <v-card-actions>
            <v-btn color="black" variant="text" @click="propertyDialog = false">
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
          <v-form v-model="form" fast-fail @submit.prevent="write">
            <v-card-title>
              <span class="text-h5 ma-2">Добавление товара</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="12" md="12">
                    <v-select
                      v-model="categoriesForAdd"
                      :items="listCategory"
                      :item-props="itemProps"
                      label="Категории"
                      hint="Выберите нужную категорию"
                      persistent-hint
                      :rules="[rules.required]"
                      clearable
                    ></v-select>
                  </v-col>

                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="product.name"
                      label="Название*"
                      clearable
                      :rules="[rules.required]"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="product.price"
                      label="Цена*"
                      type="number"
                      clearable
                      :rules="[rules.required]"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="product.amount"
                      label="Количество на складе*"
                      type="number"
                      clearable
                      :rules="[rules.required]"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="product.discount"
                      label="Скидка*"
                      type="number"
                      clearable
                      :rules="[rules.required]"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      bg-color="white"
                      color="black"
                      label="Описание товара*"
                      v-model="product.description"
                      clearable
                      :rules="[rules.required]"
                    ></v-textarea>
                  </v-col>
                  <v-col cols="12">
                    <v-file-input
                      label="Фотографии товара*"
                      multiple
                      v-model="product.photos"
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
              :items="listCategory"
              :item-props="itemProps"
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
              :items="listCategory"
              :item-props="itemProps"
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
                    <img class="edit-photo__img" :src="photo.url" />
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
                    v-model="newEditPhotos"
                  >
                  </v-file-input>

                  <v-btn
                    color="blue-darken-1"
                    variant="text"
                    @click="addNewPhotosInEdit"
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
              @click="editProduct"
            >
              Изменить
            </v-btn>
            <!-- Доделать с 400 строки, прикрутить логику редактирования -->
          </v-card-actions>
        </v-card>
      </v-dialog>

      <!-- Добавление новой категории в дерево каталога-->

      <v-dialog v-model="categoryAddDialog" persistent width="600">
        <v-card>
          <v-form v-model="formAddCategory" @submit.prevent="addNewCategory">
            <v-card-title> Добавить категорию </v-card-title>
            <v-card-text>
              <v-row>
                <v-col cols="12" sm="12" md="12">
                  <v-select
                    v-model="categoriesForAddCategory"
                    :items="listCategory"
                    :item-props="itemProps"
                    label="Категории"
                    hint="Выберите категорию в которую собираетесь добавлять новую"
                    persistent-hint
                    :rules="[rules.required]"
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    v-model="newCategory"
                    label="Название*"
                    :rules="[rules.required]"
                    clearable
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue-darken-1"
                variant="text"
                @click="categoryAddDialog = false"
              >
                Закрыть
              </v-btn>
              <v-btn
                :disabled="!formAddCategory"
                :loading="loadingFormAddCategory"
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

      <!-- Удаление категории из дерева каталога -->
      <v-dialog v-model="categoryDelDialog" persistent width="600">
        <v-card>
          <v-form v-model="formDelCategory" @submit.prevent="deleteCategory">
            <v-card-title> Удалить категорию </v-card-title>
            <v-card-text>
              <v-row>
                <v-col cols="12" sm="12" md="12">
                  <v-select
                    v-model="categoriesForDelete"
                    :items="listCategory"
                    :item-props="itemProps"
                    label="Категории"
                    hint="Выберите категорию которую собираетесь удалять"
                    persistent-hint
                    :rules="[rules.required]"
                  ></v-select>
                </v-col>
              </v-row>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue-darken-1"
                variant="text"
                @click="categoryDelDialog = false"
              >
                Закрыть
              </v-btn>
              <v-btn
                :disabled="!formDelCategory"
                :loading="loadingFormDelCategory"
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

      <!-- Переименование категории в дереве каталога -->
      <v-dialog v-model="categoryEditDialog" persistent width="600">
        <v-card>
          <v-form v-model="formEditCategory" @submit.prevent="editCategory">
            <v-card-title> Переименовать категорию </v-card-title>
            <v-card-text>
              <v-row>
                <v-col cols="12" sm="12" md="12">
                  <v-select
                    v-model="categoriesForRename"
                    :items="listCategory"
                    :item-props="itemProps"
                    label="Категории"
                    hint="Выберите категорию которую собираетесь переименовать"
                    persistent-hint
                    :rules="[rules.required]"
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    v-model="renameCategory"
                    label="Название*"
                    hint="Новое название"
                    persistent-hint
                    :rules="[rules.required]"
                    clearable
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue-darken-1"
                variant="text"
                @click="categoryEditDialog = false"
              >
                Закрыть
              </v-btn>
              <v-btn
                :disabled="!formEditCategory"
                :loading="loadingFormEditCategory"
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

      <!-- Перемещение категории в дереве каталога -->
      <v-dialog v-model="categoryMoveDialog" persistent width="600">
        <v-card>
          <v-form v-model="formMoveCategory" @submit.prevent="moveCategory">
            <v-card-title> Переместить категорию </v-card-title>
            <v-card-text>
              <v-row>
                <v-col cols="12" sm="12" md="12">
                  <v-select
                    v-model="categoriesForMoveChild"
                    :items="listCategory"
                    :item-props="itemProps"
                    label="Категории"
                    hint="Выберите категорию которую собираетесь переместить"
                    persistent-hint
                    :rules="[rules.required]"
                  ></v-select>
                </v-col>

                <v-col cols="12" sm="12" md="12">
                  <v-select
                    v-model="categoriesForMoveParent"
                    :items="listCategory"
                    :item-props="itemProps"
                    label="Категории"
                    hint="Выберите категорию в которую собираетесь перемещать"
                    persistent-hint
                    :rules="[rules.required]"
                  ></v-select>
                </v-col>
              </v-row>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue-darken-1"
                variant="text"
                @click="categoryMoveDialog = false"
              >
                Закрыть
              </v-btn>
              <v-btn
                :disabled="!formMoveCategory"
                :loading="loadingFormMoveCategory"
                color="blue-darken-1"
                variant="text"
                type="submit"
              >
                Переместить
              </v-btn>
            </v-card-actions>
          </v-form>
        </v-card>
      </v-dialog>

      <!-- Добавление свойства -->
      <v-dialog
        class="dialog"
        v-model="propertyAddDialog"
        persistent
        width="600"
      >
        <v-alert
          class="alertEdit"
          type="success"
          title="Успешно!"
          text="Свойство добавлено"
          v-model="alertPropertyAdd"
          :disabled="!alertPropertyAdd"
        ></v-alert>
        <v-card>
          <v-card-title> Добавить свойство </v-card-title>
          <v-card-text>
            <v-select
              v-model="categories"
              :items="listCategory"
              :item-props="itemProps"
              label="Категории"
              hint="Выберите нужную категорию"
              persistent-hint
              @update:modelValue="requestForSpecificCharacteristic"
            ></v-select>
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
            <div
              class="select__SpecificProducts"
              v-if="isLoadedSpecificCharacteristic"
            >
              <v-select
                v-model="specificCharacteristic"
                :items="characteristic"
                :item-props="itemProps"
                label="Характеристика"
                hint="Выберите характеристику, в которую собираетесь добавить свойство"
                persistent-hint
              ></v-select>
            </div>
            <div
              class="select__SpecificProducts"
              v-if="this.specificCharacteristic"
            >
              <v-col cols="12" sm="12" md="12">
                <v-text-field
                  v-model="addProperty"
                  label="Название*"
                  hint="Новое свойство"
                  persistent-hint
                  :rules="[rules.required]"
                  clearable
                  required
                ></v-text-field>
              </v-col>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="propertyAddRenameDeleteDialogClose"
            >
              Закрыть
            </v-btn>
            <v-btn
              color="blue-darken-1"
              variant="text"
              :disabled="!addProperty"
              @click="addPropertyFunc"
            >
              Добавить
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <!-- Удаление свойства -->
      <v-dialog
        class="dialog"
        v-model="propertyDeleteDialog"
        persistent
        width="600"
      >
        <v-alert
          class="alertDel"
          type="success"
          title="Успешно!"
          text="Свойство удалено"
          v-model="alertPropertyDel"
          :disabled="!alertPropertyDel"
        ></v-alert>
        <v-card>
          <v-card-title> Удаление свойства </v-card-title>
          <v-card-text>
            <v-select
              v-model="categories"
              :items="listCategory"
              :item-props="itemProps"
              label="Категории"
              hint="Выберите нужную категорию"
              persistent-hint
              @update:modelValue="requestForSpecificCharacteristic"
            ></v-select>
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
            <div
              class="select__SpecificProducts"
              v-if="isLoadedSpecificCharacteristic"
            >
              <v-select
                v-model="specificCharacteristic"
                :items="characteristic"
                :item-props="itemProps"
                label="Характеристика"
                hint="Выберите характеристику, в которой собираетесь удалить свойство"
                persistent-hint
              ></v-select>
            </div>
            <div
              class="select__SpecificProducts"
              v-if="this.specificCharacteristic"
            >
              <v-select
                v-model="specificPropertys"
                :items="specificCharacteristic.properties"
                :item-props="itemPropsProperty"
                label="Свойство"
                hint="Выберите свойство, которое собираетесь удалить"
                persistent-hint
              ></v-select>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="propertyAddRenameDeleteDialogClose"
            >
              Закрыть
            </v-btn>
            <v-btn
              color="blue-darken-1"
              variant="text"
              :disabled="!specificPropertys"
              @click="deletePropertyFunc"
            >
              Удалить
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <!-- Переименование свойства -->
      <v-dialog
        class="dialog"
        v-model="propertyRenameDialog"
        persistent
        width="600"
      >
        <v-alert
          class="alertEdit"
          type="success"
          title="Успешно!"
          text="Свойство изменен"
          v-model="alertPropertyRename"
          :disabled="!alertPropertyRename"
        ></v-alert>
        <v-card>
          <v-card-title> Переименовать свойство </v-card-title>
          <v-card-text>
            <v-select
              v-model="categories"
              :items="listCategory"
              :item-props="itemProps"
              label="Категории"
              hint="Выберите нужную категорию"
              persistent-hint
              @update:modelValue="requestForSpecificCharacteristic"
            ></v-select>
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
            <div
              class="select__SpecificProducts"
              v-if="isLoadedSpecificCharacteristic"
            >
              <v-select
                v-model="specificCharacteristic"
                :items="characteristic"
                :item-props="itemProps"
                label="Характеристика"
                hint="Выберите характеристику, в которой собираетесь переименовать свойство"
                persistent-hint
              ></v-select>
            </div>
            <div
              class="select__SpecificProducts"
              v-if="this.specificCharacteristic"
            >
              <v-select
                v-model="specificPropertys"
                :items="specificCharacteristic.properties"
                :item-props="itemPropsProperty"
                label="Свойство"
                hint="Выберите свойство, которое собираетесь переименовать"
                persistent-hint
              ></v-select>
              <div
                class="select__SpecificProducts"
                v-if="this.specificPropertys"
              >
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    v-model="renameProperty"
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
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="propertyAddRenameDeleteDialogClose"
            >
              Закрыть
            </v-btn>
            <v-btn
              color="blue-darken-1"
              variant="text"
              :disabled="!renameProperty"
              @click="renamePropertyFunc"
            >
              Изменить
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import uploadS3 from "@/S3_API/index.js";
import {
  getCatalog,
  getAllProducts,
  deleteSpecificProduct,
  deletePhotos,
  addNewPhotos,
  changeProducts,
  addNewProduct,
  addCategory,
  deleteCategory,
  renameCategory,
  moveCategory,
  getCharacteristic,
  deleteProperty,
  renameProperty,
  pushNewProperty,
} from "@/API/index.js";
import {
  getUniqueItem,
  splitArrayForDeleteAndAddArrays,
  getAdmin,
} from "@/utils/index.js";
import { reactive, toRaw } from "vue";
import Characteristic from "@/components/Characteristic/Characteristic.vue";
import CharacteristicForItem from '../Characteristic/CharacteristicForItem.vue';

export default {
  components: {
    Characteristic,
    CharacteristicForItem
  },

  data: () => ({
    admin: getAdmin(),

    propertyDialog: false,

    productsDialog: false, //product
    productAddDialog: false, //addProduct
    productDeleteDialog: false, //delProduct
    productEditDialog: false, //editProduct

    catalogDialog: false,
    categoryAddDialog: false,
    categoryDelDialog: false,
    categoryEditDialog: false,
    categoryMoveDialog: false,

    categoriesForAdd: null, //addProduct
    categories: null, //delProduct, editProduct
    specificProducts: null, //delProduct, editProduct
    products: null, //delProduct, editProduct

    listCategory: [],

    nowEditProduct: {
      name: null,
      price: null,
      amount: null,
      description: null,
      photos: [],
      discount: null,
    },

    original: {
      name: null,
      price: null,
      amount: null,
      description: null,
      photos: [],
      discount: null,
    }, // editProduct

    newEditPhotos: [], //product

    product: {
      name: null,
      price: null,
      amount: null,
      description: null,
      photos: [],
      discount: null,
    }, //addProduct

    form: false, //addProduct
    loading: false, //addProduct
    loadingAdd: false, //addProduct
    loadingDel: false, //delProduct
    loadingEdit: false, //editProduct
    loadingSpecificProducts: false, //delProduct, editProduct
    isLoadedSpecificProducts: false, //delProduct, editProduct
    alertProductAdd: false, //addProduct
    alertProductDel: false, //delProduct
    alertProductEdit: false, //editProduct

    ///////////////////////////////
    categoriesForAddCategory: "",
    categoriesForDelete: "",
    categoriesForRename: "",
    categoriesForMoveChild: "",
    categoriesForMoveParent: "",

    formAddCategory: false, //addCategory
    formDelCategory: false, //delCategory
    formEditCategory: false, //addCategory
    formMoveCategory: false, //delCategory

    loadingFormAddCategory: false, //addCategory
    loadingFormDelCategory: false, //delCategory
    loadingFormEditCategory: false, //editCategory
    loadingFormMoveCategory: false, //moveCategory

    loadingMoveCategory: false, //moveCategory
    loadingAddCategory: false, //addCategory
    loadingDelCategory: false, //delCategory
    loadingEditCategory: false, //editCategory

    loadingCategory: false,
    isLoadedSpecificCategory: false,

    newCategory: "",
    renameCategory: "",

    propertyAddDialog: false,
    propertyDeleteDialog: false,
    propertyRenameDialog: false,

    alertPropertyAdd: false, //addProperty
    alertPropertyDel: false, //delProperty
    alertPropertyRename: false, //editProperty

    loadingSpecificCharacteristic: false,
    isLoadedSpecificCharacteristic: false,

    loadingCharacteristicAdd: false,
    loadingCharacteristicDel: false,
    loadingCharacteristicRename: false,

    characteristic: [],

    specificCharacteristic: null,
    specificPropertys: null,

    renameProperty: null,
    addProperty: null,

    rules: {
      required: (value) => {
        return !!value || "Поле должно быть заполнено";
      },
    },
  }),

  methods: {
    getPropertyDialogBool(event) {
      this.propertyDialog = event;
    },

    goToAdmin() {
      this.$router.push("/admin");
    },

    async productAddDialogFunc() {
      this.loadingAdd = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingAdd = false;
        this.productAddDialog = true;
      });
    },

    async write() {
      if (!this.form) return;

      if (
        this.product.name == undefined ||
        this.product.price == undefined ||
        this.product.amount == undefined ||
        this.product.description == undefined ||
        this.product.discount == undefined ||
        this.product.photos == undefined ||
        this.categoriesForAdd == undefined
      )
        return;

      this.loading = true;

      let url = await uploadS3(this.product.photos);
      //console.log(url);

      let data = JSON.stringify({
        name: this.product.name,
        price: Number(this.product.price),
        amount: Number(this.product.amount),
        description: this.product.description,
        photos: url,
        discount: Number(this.product.discount),
      });

      addNewProduct(data, this.categoriesForAdd.id).then((r) => {
        this.product.name = undefined;
        this.product.price = undefined;
        this.product.amount = undefined;
        this.product.description = undefined;
        this.product.discount = undefined;
        this.product.photos = undefined;
        this.categoriesForAdd = undefined;
        this.form = false;
        this.loading = false;
        this.alertProductAdd = true;
      });

      setTimeout(() => (this.alertProductAdd = false), 1000);
    },

    async productDelDialogFunc() {
      this.loadingDel = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingDel = false;
        this.productDeleteDialog = true;
      });
    },

    requestForSpecificProducts() {
      this.loadingSpecificProducts = true;
      this.specificProducts = "";

      getAllProducts(this.categories.id).then((data) => {
        this.products = data;
        this.loadingSpecificProducts = false;
        this.isLoadedSpecificProducts = true;
      });
    },

    requestForSpecificCharacteristic() {
      this.loadingSpecificCharacteristic = true;
      this.specificCharacteristic = "";
      this.renameProperty = null;

      getCharacteristic(this.categories.id, "?isRange=false").then((data) => {
        this.characteristic = data;
        this.loadingSpecificCharacteristic = false;
        this.isLoadedSpecificCharacteristic = true;
      });
    },

    requestCategory() {
      this.loadingCategory = true;

      setTimeout(() => {
        this.loadingCategory = false;
        this.isLoadedSpecificProducts = true;
      }, 1000);
    },

    async deleteProduct() {
      deleteSpecificProduct(this.specificProducts.id).then((r) => {
        this.categories = "";
        this.alertProductDel = true;
        this.isLoadedSpecificProducts = false;
        this.specificProducts = null;
      });

      setTimeout(() => (this.alertProductDel = false), 2000);
    },

    itemProps(item) {
      return {
        title: item.name,
      };
    },

    //itemPropsProperty
    itemPropsProperty(item) {
      return {
        title: item.value,
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

    async productEditDialogFunc() {
      this.loadingEdit = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingEdit = false;
        this.productEditDialog = true;
      });
    },

    changeEditProduct(event) {
      //console.log(event);
      this.nowEditProduct = reactive({ ...toRaw(event) });

      this.original = reactive({ ...toRaw(this.nowEditProduct) });
      //console.log(this.original);
    },

    delPhoto(photo) {
      //console.log(photo);
      this.nowEditProduct.photos = this.nowEditProduct.photos.filter(
        (delPhoto) => delPhoto !== photo
      );
    },

    async addNewPhotosInEdit() {
      //console.log(this.newEditPhotos);

      let url = await uploadS3(this.newEditPhotos);

      let obj = [];
      setTimeout(() => {
        for (let i = 0; i < url.length; i++) {
          obj.push({ url: url[i] });
        }
        this.nowEditProduct.photos = this.nowEditProduct.photos.concat(obj);
      }, 1600);
    },

    async editProduct() {
      //сравнить объекты, найти поля с отличиями, эти поля передать в query параметры url запроса

      let resultObject = {};

      for (let key in this.nowEditProduct) {
        // console.log(key);
        if (this.nowEditProduct[key] !== this.original[key]) {
          resultObject[key] = toRaw(this.nowEditProduct[key]);
        }
      }

      //console.log(toRaw(resultObject));

      if (resultObject.photos !== undefined) {
        let photosForChange = [...resultObject.photos]; // массив для запросов

        photosForChange = photosForChange.map((v) => toRaw(v));
        let originalPhotos = [...this.original.photos.map((v) => toRaw(v))];

        let [photosForAdd, photosForDelete] = splitArrayForDeleteAndAddArrays(
          originalPhotos,
          photosForChange
        );

        delete resultObject.photos;

        let changeProductURL = new URLSearchParams(resultObject);

        if (photosForDelete.length != 0) {
          let deletePhotosURL = new URLSearchParams();
          photosForDelete.forEach((photo) => {
            deletePhotosURL.append("url", photo.url);
          });
          //console.log(deletePhotosURL.toString());
          deletePhotos(deletePhotosURL.toString());
        }

        if (photosForAdd.length != 0) {
          let photos = [];

          photosForAdd.forEach((photo) => {
            photos.push(photo.url);
          });
          //console.log(photos);

          addNewPhotos(photos, "product", this.original.id);
        }

        changeProducts(changeProductURL, this.original.id).then((res) => {
          this.categories = null;
          this.original = null;
          this.specificProducts = null;
          this.nowEditProduct = null;
          this.isLoadedSpecificProducts = false;
        });
      } else {
        let changeProductURL = new URLSearchParams(resultObject);
        changeProducts(changeProductURL, this.original.id).then((res) => {
          this.categories = null;
          this.original = null;
          this.specificProducts = null;
          this.nowEditProduct = null;
          this.isLoadedSpecificProducts = false;
        });
      }
    },

    async categoryAddDialogFunc() {
      this.loadingAddCategory = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingAddCategory = false;
        this.categoryAddDialog = true;
      });
    },

    async categoryDelDialogFunc() {
      this.loadingDelCategory = true;
      // послать запрос на все категории

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingDelCategory = false;
        this.categoryDelDialog = true;
      });
    },

    async categoryEditDialogFunc() {
      this.loadingEditCategory = true;
      // послать запрос на все категории

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingEditCategory = false;
        this.categoryEditDialog = true;
      });
    },

    async categoryMoveDialogFunc() {
      this.loadingMoveCategory = true;
      // послать запрос на все категории

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingMoveCategory = false;
        this.categoryMoveDialog = true;
      });
    },

    async addNewCategory() {
      if (!this.formAddCategory) return;

      this.loading = true;

      addCategory(this.newCategory, this.categoriesForAddCategory.id).then(
        (res) => {
          this.categoriesForAddCategory = null;
          this.newCategory = null;
          this.loading = false;
        }
      );
    },

    async deleteCategory() {
      if (!this.formDelCategory) return;

      this.loading = true;

      deleteCategory(this.categoriesForDelete.id).then((res) => {
        this.categoriesForDelete = null;
        this.loading = false;
      });
    },

    async editCategory() {
      if (!this.formEditCategory) return;

      this.loading = true;

      renameCategory(this.renameCategory, this.categoriesForRename.id).then(
        (res) => {
          this.categoriesForRename = null;
          this.renameCategory = null;
          this.loading = false;
        }
      );
    },

    async moveCategory() {
      if (!this.formMoveCategory) return;

      this.loading = true;

      moveCategory(
        this.categoriesForMoveChild.id,
        this.categoriesForMoveParent.id
      ).then((res) => {
        this.categoriesForMoveChild = null;
        this.categoriesForMoveParent = null;
        this.loading = false;
      });
    },

    async propertyAddDialogFunc() {
      this.loadingCharacteristicAdd = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingCharacteristicAdd = false;
        this.propertyAddDialog = true;
      });
    },

    async propertyDelDialogFunc() {
      this.loadingCharacteristicDel = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingCharacteristicDel = false;
        this.propertyDeleteDialog = true;
      });
    },

    propertyAddRenameDeleteDialogClose() {
      this.isLoadedSpecificCharacteristic = false;
      this.categories = null;
      this.specificCharacteristic = null;
      this.specificPropertys = null;

      this.propertyRenameDialog === true
        ? ((this.propertyRenameDialog = false), (this.renameProperty = null))
        : this.propertyDeleteDialog === true
        ? (this.propertyDeleteDialog = false)
        : ((this.propertyAddDialog = false), (this.addProperty = null));
    },

    async propertyRenameDialogFunc() {
      this.loadingCharacteristicRename = true;

      await getCatalog().then((data) => {
        this.listCategory = getUniqueItem(data, "id");
        this.$store.commit("setTreeView", data);
        this.loadingCharacteristicRename = false;
        this.propertyRenameDialog = true;
      });
    },

    async addPropertyFunc() {
      this.loading = true;

      pushNewProperty({value: this.addProperty}, this.specificCharacteristic.id).then((res) => {
        this.isLoadedSpecificCharacteristic = false;
        this.categories = null;
        this.specificCharacteristic = null;
        this.loading = false;
        this.addProperty = null;
      });
    },

    async deletePropertyFunc() {
      this.loading = true;

      let delProp = this.specificCharacteristic.properties.find(
        (v) => v.value === this.specificPropertys
      );

      deleteProperty(delProp.id).then((res) => {
        this.isLoadedSpecificCharacteristic = false;
        this.categories = null;
        this.specificCharacteristic = null;
        this.specificPropertys = null;
        this.loading = false;
      });
    },

    async renamePropertyFunc() {
      this.loading = true;

      let renameProp = this.specificCharacteristic.properties.find(
        (v) => v.value === this.specificPropertys
      );

      renameProperty(this.renameProperty, renameProp.id).then((res) => {
        this.isLoadedSpecificCharacteristic = false;
        this.categories = null;
        this.specificCharacteristic = null;
        this.specificPropertys = null;
        this.loading = false;
        this.renameProperty = null;
      });
    },
  },
};
</script>

<style>
.admin {
  max-width: 80%;
  margin: 0 auto 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.admin__title {
  text-align: center;
  font-size: 30px;
  font-weight: 500;
  color: #ff9800;
  margin-bottom: 20px;
}

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

.back-to-catalog {
  text-align: center;
  cursor: pointer;
  text-decoration: underline;
  color: rgb(85, 26, 139);
}
</style>
