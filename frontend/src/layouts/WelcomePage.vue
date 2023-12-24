<template>
  <div class="welcome">
    <my-header />
    <div class="welcome__carousel">
      <my-carousel />
    </div>
    <div class="main__container">
      <div class="categories">
        <router-link to="/categories/2/products?page=1&order=4" class="category">
          <img src="src\assets\categoryimage1.png" class="category__img" />
          <div class="category__title">Смартфоны</div>
        </router-link>
        <router-link to="/categories/2/products?page=1&order=4" class="category">
          <img src="src\assets\categoryimage2.png" class="category__img" />
          <div class="category__title">Телевизоры</div>
        </router-link>
        <router-link to="/categories/3/products?page=1&order=4" class="category">
          <img src="src\assets\categoryimage3.png" class="category__img" />
          <div class="category__title">Ноутбуки</div>
        </router-link>
        <router-link to="/categories/3/products?page=1&order=4" class="category">
          <img src="src\assets\categoryimage4.png" class="category__img" />
          <div class="category__title">Аксессуары</div>
        </router-link>
      </div>

      <div class="advertising">
          <div class="advertising__title"> Информационный блок</div>
          <div class="advertising__content">
          <div class="advertising__block">
            <img src="\src\assets\banner.png" alt="" class="advertising__block--back-img" />
          </div>
          <div class="advertising__block">
            <div class="advertising__block-1">
              <img
                src="\src\assets\productimage.png"
                alt=""
                class="advertising__img"
              />
              <div class="advertising__text">
                <div class="advertising__text-content">
                  <div class="advertising__title">HEllo</div>
                  <div class="advertising__description">HEllo...</div>
                </div>

                <div class="price-btn">
                  <v-btn variant="outlined" @click.stop>Купить </v-btn>
                  <div class="advertising__price">Цена: 999</div>
                </div>
              </div>
            </div>
            <div class="advertising__block-2">
              <img
                src="\src\assets\productimage.png"
                alt=""
                class="advertising__img"
              />
              <div class="advertising__text">
                <div class="advertising__text-content">
                  <div class="advertising__title">HEllo</div>
                  <div class="advertising__description">HEllo...</div>
                </div>

                <div class="price-btn">
                  <v-btn variant="outlined" @click.stop>Купить </v-btn>
                  <div class="advertising__price">Цена: 999</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="advertising">
          <div class="advertising__title"> Информационный блок</div>
          <div class="advertising__content">
          <div class="advertising__block">
            <img src="\src\assets\banner_2.png" alt="" class="advertising__block--back-img" />
          </div>
          <div class="advertising__block">
            <div class="advertising__block-1">
              <img
                src="\src\assets\airpods.png"
                alt=""
                class="advertising__img"
              />
              <div class="advertising__text">
                <div class="advertising__text-content">
                  <div class="advertising__title">HEllo</div>
                  <div class="advertising__description">HEllo...</div>
                </div>

                <div class="price-btn">
                  <v-btn variant="outlined" @click.stop>Купить </v-btn>
                  <div class="advertising__price">Цена: 999</div>
                </div>
              </div>
            </div>
            <div class="advertising__block-2">
              <img
                src="\src\assets\productimage3.png"
                alt=""
                class="advertising__img"
              />
              <div class="advertising__text">
                <div class="advertising__text-content">
                  <div class="advertising__title">HEllo</div>
                  <div class="advertising__description">HEllo...</div>
                </div>

                <div class="price-btn">
                  <v-btn variant="outlined" @click.stop>Купить </v-btn>
                  <div class="advertising__price">Цена: 999</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <my-footer/>
  </div>
</template>

<script>
import MyHeader from "@/components/Header/MyHeader.vue";
import MyCarousel from "../components/MyCarousel/MyCarousel.vue";
import MyFooter from '../components/Footer/MyFooter.vue';

export default {
  components: {
    MyHeader,
    MyCarousel,
    MyFooter,
    
  },
};
</script>

<style>
.welcome__carousel {
  margin-top: 80px;
  margin-bottom: 40px;
}

.main__container{
  width: 80%;
  margin: 0 auto;
}

.categories {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 100px;
}

.category {
  background-color: white;
  border-radius: 20px;
  text-align: center;
  width: 320px;
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.2);
  padding: 20px;
  text-decoration: none;
  color: #000;
  font-size: 22px;
}

.category:hover {
  box-shadow: 0px 1px 15px rgba(0, 0, 0, 0.2);
  background-color: rgb(236, 236, 236);
}

.advertising{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 100px;
}

.advertising__title{
    font-weight: 500;
    font-size: 45px;
    margin-bottom: 50px;
}

.advertising__content{
    width: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-around;
    flex-wrap: wrap;
}

.advertising__block{
    display: flex;
    flex-direction: row;
    gap: 20px
}

.advertising__block-1,
.advertising__block-2{
    background-color: white;
    width: 300px;
    border-radius: 20px;
    box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.2);
    display: flex;
    flex-direction: column;
    align-items: center;
}

.advertising__block-1:hover,
.advertising__block-2:hover {
  box-shadow: 0px 1px 15px rgba(0, 0, 0, 0.2);
  background-color: rgb(236, 236, 236);
}

.advertising__block--back-img{
    max-width: 100%;
    
}

.advertising__img {
  height: 250px;
  max-width: 200px;
}

.advertising__text {
  height: inherit;
  display: flex;
  flex-direction: column;
  align-items: center;
  flex: 1;
}

.advertising__title {
  font-size: 26px;
  color: rgba(0, 0, 0, 0.8);
  margin-bottom: 10px;
}

.advertising__description {
  font-weight: 300;
}

.price-btn {
  display: flex;
  flex-direction: column-reverse;
  align-items: center;
  margin-bottom: 20px;
}
</style>
