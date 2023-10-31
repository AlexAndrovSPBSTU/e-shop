<template>
  <div class="my-card">
    <my-header />
    <div class="container">
      <div class="my-card__content">
        <my-breadcrumb :items="itemsData" />
        <div class="my-card__card">
          <div class="card__title">{{ card.title }}</div>
          <div class="card__content">
            <div class="card__img">
              <img :src="card.img" alt="" class="card__img-img" />
            </div>
            <div class="card__text">
              <div class="card__description">{{ card.description }}</div>
              <div class="card__buy">
                <div class="card__price-amount">
                  <div class="card__price">{{ card.price }}₽</div>
                  <div class="card__amount text-body-2">В наличии: много</div>
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

          <div class="spec">
            <div class="spec__title">Характеристики</div>
            <v-table>
              <tbody>
                <tr v-for="item in card.spec" :key="item.name">
                  <td>{{ item.name }}</td>
                  <td>{{ item.text }}</td>
                </tr>
              </tbody>
            </v-table>
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
                  v-model="fotos"
                ></v-file-input>
                <div class="write__btn">
                  <v-btn
                    variant="outlined"
                    color="orange"
                    class="text-none text-h6"
                    @click="write"
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
                  <div class="author">{{ comment.author }}</div>
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
                  {{ comment.text }}
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
                          <img :src="photo" alt="" class="comment__img" />
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
                          :src="photo"
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

//import upload from "@/S3_API/index.js"

export default {
  components: {
    MyHeader,
    MyBreadcrumb,
  },
  data: () => ({
    card: {
      id: 1241351341,
      title: "Phone 124134",
      img: "https://storage.yandexcloud.net/e-shop/5.jpg",
      description:
        "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius? Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?Lorem ipsum dolor sit amet consectetur, adipisicing elit. Aliquid atque consectetur fugiat, cum necessitatibus a animi doloribus qui eaque suscipit quo, dolore optio eum similique numquam minus id amet eius?",
      price: 999,
      amount: 999,
      spec: [
        {
          name: "Бренд",
          text: "APPLE",
        },
        {
          name: "Модель",
          text: "AirPods Pro 2 A2698 A2699 A2700",
        },
        {
          name: "Крепление",
          text: "в ушной раковине",
        },
        {
          name: "Тип конструкции",
          text: "внутриканальные",
        },
        {
          name: "Тип соединения",
          text: "беспроводные bluetooth",
        },
      ],
    },

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

    items: [
      {
        title: "Каталог",
        disabled: false,
        to: "/catalog",
      },
      {
        title: "Телефоны",
        disabled: false,
        to: "/login",
      },
      {
        title: "#",
        disabled: true,
      },
    ],

    filters: ["Рейтинг выше", "Рейтинг ниже"],
    filter: "Рейтинг выше",
    writeComment: "",
    slider: 5,
    fotos: [],
  }),
  created() {
    //fetch на сервер с запросом на получение определенного товара
    //fetch на сервер с запросом на получение комментов к этому товару
    //https://24c1624a-5fb0-4f0f-801c-1ebd2db7b6ac.selstorage.ru/123.jpg
  },
  computed: {
    itemsData() {
      this.items[2].title = this.card.title;
      return this.items;
    },

    sortItems() {
      return this.filter === "Рейтинг ниже"
        ? this.comments.sort((a, b) => a.rating - b.rating)
        : this.comments.sort((a, b) => a.rating - b.rating).reverse();
    },
  },
  methods: {
    increment() {
      this.$store.commit("increment");
      console.log(this.$store.state.count);
    },

    write() {
      
      let data = this.fotos.map(v => v);
      console.log(data);

      if (data.length > 0) {
        const formData = new FormData();

        formData.append("file", data);

        fetch("http://localhost:8001/uploadFile", {
          method: "POST",
          //headers: { "Content-Type": "multipart/form-data" },
          body: formData,
        }).then((response) => console.log(response));
      }
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

.card__img-img {
  max-width: 300px;
  margin-top: 10px;
  margin-left: 10px;
}

.card__text {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: 3%;
}

.card__description {
  margin-top: 20px;
  margin-right: 4%;
  padding-bottom: 2%;
  border-bottom: 1px solid rgba(0, 0, 0, 0.3);
}

.card__buy {
  width: 400px;
  margin-right: 5%;
  margin-left: auto;
  padding: 20px 30px;
  border-radius: 20px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.3);
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}

.card__price {
  font-weight: 500;
  font-size: 28px;
}

.spec > .v-table {
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.3);
  border-radius: 20px;
  margin-bottom: 100px;
}

.comments__title,
.spec__title {
  font-size: 30px;
  margin-bottom: 20px;
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
  width: 1000px;
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
