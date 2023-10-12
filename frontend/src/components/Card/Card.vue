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
            <div class="comments__title">Отзывы</div>
            <div class="comments__content">
              <div class="comment">
                <div class="comment__header">
                  <div class="author">Иванов И.</div>
                  <div class="stars">
                    <v-rating
                      readonly
                      :length="5"
                      :size="32"
                      :model-value="3"
                      color="orange-lighten-1"
                      active-color="orange-lighten-1"
                    ></v-rating>
                  </div>
                </div>
                <div class="comment__text">
                  Качественный, хороший звук. В этих наушниках хочется слушать
                  музыку. Шумоподавление хорошо убирает гул (например шум работы
                  ДВС автомобиля, обдува), но вот с резкими стуками, щелчками
                  дело обстоит заметно хуже. Через несколько минут
                  использования, перестаёшь их чувствовать в ушах. Заряд держат
                  заявленное время. Честное отображение уровня (а не долгие
                  100%, а потом резкий спуск). Грамотная конструкция амбушюров с
                  точки зрения стойкости к загрязнению от ушного канала.
                </div>
                <div class="comment__delete">
                  <v-btn
                    class="comment__delete-btn"
                    density="compact"
                    icon="mdi-trash-can"
                    variant="plain"
                    @click.stop="delAllItems(item)"
                  />
                </div>
              </div>
              <div class="comment">
                <div class="comment__header">
                  <div class="author">Петров П.</div>
                  <div class="stars">
                    <v-rating
                      readonly
                      :length="5"
                      :size="32"
                      :model-value="5"
                      color="orange-lighten-1"
                      active-color="orange-lighten-1"
                    ></v-rating>
                  </div>
                </div>
                <div class="comment__text">
                  Таких басов вы ещё не слышали! 100% оригинал, прошли все
                  проверки. Прошивка обновляется. При подключении к iPhone сразу
                  прилетел месяц подписки Apple Music в подарок.
                </div>
              </div>
              <div class="comment">
                <div class="comment__header">
                  <div class="author">Иванов И.</div>
                  <div class="stars">
                    <v-rating
                      readonly
                      :length="5"
                      :size="32"
                      :model-value="3"
                      color="orange-lighten-1"
                      active-color="orange-lighten-1"
                    ></v-rating>
                  </div>
                </div>
                <div class="comment__text">
                  Качественный, хороший звук. В этих наушниках хочется слушать
                  музыку. Шумоподавление хорошо убирает гул (например шум работы
                  ДВС автомобиля, обдува), но вот с резкими стуками, щелчками
                  дело обстоит заметно хуже. Через несколько минут
                  использования, перестаёшь их чувствовать в ушах. Заряд держат
                  заявленное время. Честное отображение уровня (а не долгие
                  100%, а потом резкий спуск). Грамотная конструкция амбушюров с
                  точки зрения стойкости к загрязнению от ушного канала.
                </div>
              </div>
              <div class="comment">
                <div class="comment__header">
                  <div class="author">Петров П.</div>
                  <div class="stars">
                    <v-rating
                      readonly
                      :length="5"
                      :size="32"
                      :model-value="5"
                      color="orange-lighten-1"
                      active-color="orange-lighten-1"
                    ></v-rating>
                  </div>
                </div>
                <div class="comment__text">
                  Таких басов вы ещё не слышали! 100% оригинал, прошли все
                  проверки. Прошивка обновляется. При подключении к iPhone сразу
                  прилетел месяц подписки Apple Music в подарок.
                </div>
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

export default {
  components: {
    MyHeader,
    MyBreadcrumb,
  },
  data: () => ({
    card: {
      id: 1241351341,
      title: "Phone 124134",
      img: "https://aizgbnkooo.cloudimg.io/v7/site/device_model_gsrhdald_1579987939839.jpeg?p=md",
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
  }),
  created() {
    //fetch на сервер с запром на получение определенного товара
  },
  computed: {
    itemsData() {
      this.items[2].title = this.card.title;
      return this.items;
    },
  },
  methods: {
    increment() {
      this.$store.commit("increment");
      console.log(this.$store.state.count);
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

.comments__content {
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.3);
  border-radius: 20px;
  background-color: white;
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

.v-rating__item label {
  margin: 0;
}

.comment__delete{
  display: flex;
  justify-content: flex-end;
}
</style>
