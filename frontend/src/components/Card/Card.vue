<template>
  <div class="my-card">
    <my-header />
    <div v-if="card == null" class="progressCircular__card">
      <v-progress-circular
        indeterminate
        color="amber"
        :size="50"
        :width="5"
      ></v-progress-circular>
    </div>
    <div v-else class="container">
      <div class="my-card__content">
        <my-breadcrumb :items="itemsData" />
        <div class="my-card__card">
          <div class="card__title">{{ card.name }}</div>
          <div class="card__content">
            <v-carousel show-arrows="hover" style="width: 400px; height: 500px">
              <v-carousel-item
                v-for="(photo, index) in card.photos"
                :key="index"
                :src="photo.url"
                eager
              >
              </v-carousel-item>
            </v-carousel>
            <div class="card__text">
              <div class="card__description">{{ card.description }}</div>
              <div class="spec__title-and-admin">
                <div class="spec__title">Характеристики</div>
              </div>
              

              <v-table>
                <tbody>
                  <tr
                    v-for="(item, index) in Object.entries(card.properties)"
                    :key="index"
                  >
                    <td>{{ item[0] }}:</td>
                    <span
                      class="spec__item"
                      v-for="(value, jndex) in item[1]"
                      :key="jndex"
                    >
                      {{ value }}
                    </span>
                  </tr>
                </tbody>
              </v-table>
              <div class="card__buy">
                <div class="card__price-amount">
                  <div
                    class="card__discount--logged"
                    v-if="
                      this.$store.state.initialState.status.loggedIn &&
                      card.discount !== 0
                    "
                  >
                    <div class="card__discount">{{ card.price }}₽</div>
                    <div class="card__price--discount">
                      {{ (card.price * ((100 - card.discount)/100)).toFixed(0) }}₽
                    </div>
                  </div>

                  <div v-else class="card__price">{{ card.price }}₽</div>
                  <div
                    class="card__amount text-body-2"
                    v-if="card.amount === 0"
                  >
                    Нет в наличии
                  </div>
                  <div
                    class="card__amount text-body-2"
                    v-else-if="card.amount > 5"
                  >
                    В наличии
                  </div>
                  <div class="card__amount text-body-2" v-else>Мало</div>
                </div>
                <v-btn
                  variant="outlined"
                  color="orange"
                  class="text-none text-h6"
                  @click.stop="increment"
                >
                  Купить
                </v-btn>
              </div>
            </div>
          </div>

          <div class="comments">
            <div class="comments__header">
              <div class="comments__title">Отзывы</div>
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

            <div class="comments__content">
              <div
                class="comments__write"
                v-if="this.$store.state.initialState.status.loggedIn"
              >
                <v-textarea
                  bg-color="white"
                  color="black"
                  label="Написать отзыв"
                  v-model="writeComment"
                ></v-textarea>

                <div class="write__rating">
                  <div class="write__title">Оценка</div>
                  <v-slider
                    step="1"
                    show-ticks
                    tick-size="5"
                    :max="5"
                    v-model="slider"
                    thumb-label
                    color="orange"
                    class="write__slider"
                  ></v-slider>
                </div>
                <v-file-input
                  label="Прикрепите фотографии"
                  multiple
                  v-model="photos"
                ></v-file-input>
                <div class="write__btn">
                  <v-btn
                    :disabled="!writeComment"
                    variant="outlined"
                    color="orange"
                    class="text-none text-h6"
                    @click="writeNewComment"
                  >
                    Опубликовать
                  </v-btn>
                </div>
              </div>
              <div
                class="comment"
                v-for="(comment, index) in sortItems"
                :key="index"
              >
                <div class="comment__header">
                  <div class="author">
                    {{ comment.customer.name }}
                    {{ comment.customer.surname }}
                    {{
                      comment.date.split("T")[0].split("-").reverse().join(".")
                    }}
                  </div>
                  <div class="stars">
                    <v-rating
                      readonly
                      :length="5"
                      :size="32"
                      :model-value="comment.rating"
                      color="orange-lighten-1"
                      active-color="orange-lighten-1"
                    ></v-rating>
                  </div>
                </div>
                <div class="comment__text">
                  {{ comment.note }}
                </div>
                <template v-if="comment.photos.length !== 0">
                  <div class="comment__photos">
                    <v-dialog
                      width="1000"
                      v-for="(photo, index) in comment.photos"
                      :key="index"
                    >
                      <template v-slot:activator="{ props }">
                        <div v-bind="props" class="comment__photo">
                          <img :src="photo.url" alt="" class="comment__img" />
                          <div
                            class="photo__delete"
                            v-if="
                              this.$store.state.initialState.status.loggedIn
                            "
                          >
                            <v-btn
                              v-if="
                                this.$store.state.initialState.user.role ===
                                'ADMIN'
                              "
                              class="photo__delete-btn"
                              density="compact"
                              size="large"
                              icon="mdi-plus"
                              variant="text"
                              @click.stop="delPhoto(photo)"
                            />
                          </div>
                        </div>
                      </template>

                      <template v-slot:default="{ isActive }">
                        <img
                          :src="photo.url"
                          alt=""
                          class="comment__img--full"
                          @click="isActive.value = false"
                        />
                      </template>
                    </v-dialog>
                  </div>
                </template>
                <template v-if="this.$store.state.initialState.status.loggedIn">
                  <div class="comment__delete">
                    <v-btn
                      v-if="
                        this.$store.state.initialState.user.role === 'ADMIN'
                      "
                      class="comment__delete-btn"
                      density="compact"
                      icon="mdi-trash-can"
                      variant="plain"
                      @click.stop="delComment(comment)"
                    />
                  </div>
                </template>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyHeader from "@/components/Header/MyHeader.vue";
import MyBreadcrumb from "@/components/MyBreadcrumb/MyBreadcrumb.vue";
import { useRoute } from "vue-router";
import uploadS3 from "@/S3_API/index.js";
import {
  getProduct,
  getCatalog,
  newComment,
  deleteComment,
  deletePhotos,
} from "@/API/index.js";

export default {
  components: {
    MyHeader,
    MyBreadcrumb,
  },
  data: () => ({
    card: null,

    comments: [
      {
        author: "Иванов И.",
        rating: 3,
        text: "Качественный, хороший звук. В этих наушниках хочется слушать музыку. Шумоподавление хорошо убирает гул (например шум работы ДВС автомобиля, обдува), но вот с резкими стуками, щелчками дело обстоит заметно хуже. Через несколько минут использования, перестаёшь их чувствовать в ушах. Заряд держат заявленное время. Честное отображение уровня (а не долгие 100%, а потом резкий спуск). Грамотная конструкция амбушюров с точки зрения стойкости к загрязнению от ушного канала.",
        photos: [
          "https://static.onlinetrade.ru/img/fullreviews/86437/21_big.JPG",
          "https://www.ferra.ru/imgs/2021/11/16/08/5029490/4b26dd764e6bac28f307d8c58bbf9c96e236297b.jpg",
          "https://img.freepik.com/free-photo/abstract-surface-and-textures-of-white-concrete-stone-wall_74190-8189.jpg?w=1380&t=st=1697560803~exp=1697561403~hmac=108d77af72d4f1d8b0d9b1160d8df236d3dcf3759ac9171abb360ffa7a90d55e",
        ],
      },
      {
        author: "Петров П.",
        rating: 5,
        text: "Таких басов вы ещё не слышали! 100% оригинал, прошли все проверки. Прошивка обновляется. При подключении к iPhone сразу прилетел месяц подписки Apple Music в подарок.",
        photos: [],
      },
      {
        author: "Иванов И.",
        rating: 4,
        text: "Качественный, хороший звук. В этих наушниках хочется слушать музыку. Шумоподавление хорошо убирает гул (например шум работы ДВС автомобиля, обдува), но вот с резкими стуками, щелчками дело обстоит заметно хуже. Через несколько минут использования, перестаёшь их чувствовать в ушах. Заряд держат заявленное время. Честное отображение уровня (а не долгие 100%, а потом резкий спуск). Грамотная конструкция амбушюров с точки зрения стойкости к загрязнению от ушного канала.",
        photos: [],
      },
      {
        author: "Петров П.",
        rating: 2,
        text: "Таких басов вы ещё не слышали! 100% оригинал, прошли все проверки. Прошивка обновляется. При подключении к iPhone сразу прилетел месяц подписки Apple Music в подарок.",
        photos: [],
      },
    ],

    items: [],

    idCategory: 0,

    breadCrumb: {
      id: 1,
      name: "Root",
      children: null,
    },

    filters: ["Рейтинг выше", "Рейтинг ниже"],
    filter: "Рейтинг выше",
    writeComment: "",
    slider: 5,
    photos: [],
  }),

  created() {
    const route = useRoute();

    getProduct(route.path).then((data) => {
      this.card = data;
      console.log(data);
    });

    getCatalog().then((data) => {
      this.$store.commit("setTreeView", data);
      this.breadCrumb.children = data;
    });
  },

  computed: {
    itemsData() {
      if (this.items.length !== 0) return this.items;

      if (this.card == null)
        return [
          {
            title: "Каталог",
            disabled: true,
          },
        ];

      function getPath(model, name) {
        var path,
          item = { name: model.name, id: model.id };

        if (!model || typeof model !== "object") return;

        if (model.name === name) {
            //this.idCategory = item.id
            return [item]
          };

        (model.children || []).some((child) => (path = getPath(child, name)));
        return path && [item, ...path];
      }

      const arr = getPath(this.breadCrumb, this.card.category);

      console.log(arr);
      this.idCategory = arr.find(value => value.name == this.card.category).id
      console.log("id category =", this.idCategory)

      arr.forEach((value) => {
        this.items.push({
          title: value.name,
          disabled: false,
          to: `/categories/${value.id}/products`,
        });
      });

      this.items[0].title = "Каталог";

      this.items.push({
        title: this.card.name,
        disabled: true,
      });
      return this.items;
    },

    sortItems() {
      return this.filter === "Рейтинг ниже"
        ? this.card.comments.sort((a, b) => a.rating - b.rating)
        : this.card.comments.sort((a, b) => a.rating - b.rating).reverse();
    },
  },
  methods: {
    increment() {
      this.$store.commit("increment");
      this.$store.commit("setItem", this.card);
    },

    async writeNewComment() {
      let url = this.photos.length !== 0 ? await uploadS3(this.photos) : [];

      setTimeout(async () => {
        await newComment(this.card.id, this.slider, url, this.writeComment);

        const route = useRoute();

        console.log(route);
        console.log(route.path);

        await getProduct(route.path).then((data) => {
          this.card = data;
        });

        this.writeComment = "";
        this.slider = 5;
        this.photos = [];
      }, 300);
    },

    async delComment(comment) {
      await deleteComment(comment.id);

      const route = useRoute();

      await getProduct(route.path).then((data) => {
        this.card = data;
      });
    },

    async delPhoto(photo) {
      await deletePhotos(`url=${photo.url}`);

      const route = useRoute();

      await getProduct(route.path).then((data) => {
        this.card = data;
      });
    },
  },

  watch: {
    filter() {
      this.sortItems;
    },
  },
};
</script>

<style>
.my-card__content {
  margin-top: 100px;
}

.my-card__card {
  margin: 0 4%;
}

.card__title {
  font-size: 30px;
  font-weight: 500;
  color: rgba(0, 0, 0, 0.8);
}

.card__content {
  background-color: white;
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.3);
  border-radius: 20px;
  display: flex;
  gap: 60px;
  margin-bottom: 100px;
}

.v-window--show-arrows-on-hover {
  border-bottom-left-radius: 20px !important;
}

.card__img {
  max-width: 600px;
  margin-top: 10px;
  margin-left: 10px;
}

.card__text {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: 3%;
  width: 65%;
}

.card__description {
  margin-top: 60px;
  margin-right: 4%;
  padding-bottom: 2%;
  border-bottom: 1px solid rgba(0, 0, 0, 0.3);
}

.card__buy {
  width: 400px;
  max-height: 110px;
  margin-right: 5%;
  margin-left: auto;
  padding: 20px 30px;
  border-radius: 20px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.3);
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  justify-content: space-between;
}

.card__price {
  font-weight: 500;
  font-size: 28px;
}

.card__price--discount {
  font-weight: 500;
  font-size: 28px;
}

.card__discount {
  font-weight: 500;
  font-size: 21px;
  color: gray;
  text-decoration: line-through;
  text-decoration-style: solid;
  text-decoration-thickness: 1px;
  position: relative;
  top: 18px;
  left: 100px;
}

/*.spec > .v-table {
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.3);
  border-radius: 20px;
  margin-bottom: 100px;
}*/

/*td{
  height: 37px !important;
  max-width: 20px !important;
}*/

tr,
td {
  display: flex;
  align-items: center;
}

td {
  min-width: 150px;
}

.spec__item {
  text-align: center;
  min-width: 52px;
  padding: 5px 10px;
  background-color: orange;
  border-radius: 20px;
  color: white;
  font-weight: 500;
  margin: 3px;
  cursor: pointer;
}

.spec__item:hover {
  background-color: rgb(255, 136, 0);
}

.comments__title {
  font-size: 30px;
  margin-bottom: 20px;
}

.spec__title {
  font-size: 20px;
}

.comments__header {
  display: flex;
  justify-content: space-between;
}

.my-select {
  width: 190px;
}

.comments__content {
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.3);
  border-radius: 20px;
  background-color: white;
}

.comments__write {
  padding: 20px;
}

.write__rating {
  max-width: 300px;
}

.write__title {
  margin-bottom: 30px;
}

.write__btn {
  display: flex;
  justify-content: flex-end;
}

.comment {
  margin-bottom: 20px;
  margin: 20px;
  padding: 20px;
  border-bottom: 1px solid rgba(128, 128, 128, 0.478);
}

.comment:last-child {
  border: none;
}

.comment__header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.comment__text {
  margin-bottom: 20px;
}

.v-rating__item label {
  margin: 0;
}

.comment__delete {
  display: flex;
  justify-content: flex-end;
}

.comment__photos {
  display: flex;
  flex-direction: row;
  gap: 20px;
}

.comment__img {
  max-width: 200px;
  height: 140px;
}

.comment__img--full {
  aspect-ratio: 4 / 3;
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

.progressCircular__card {
  position: absolute;
  top: 50%;
  left: 50%;
}

.spec__title-and-admin{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  
}
</style>
