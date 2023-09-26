<template>
  <div class="welcome">
    <my-header />
    <div class="welcome__carousel">
        <my-carousel/>
      </div>
    <div class="container">
        <div class="categories">
            <div class="category">
                <img src="src\assets\categoryimage1.png" class="category__img "/>
                <div class="category__title">Смартфоны</div>
            </div>
            <div class="category">
                <img src="src\assets\categoryimage2.png" class="category__img "/>
                <div class="category__title">Телевизоры</div>
            </div>
            <div class="category">
                <img src="src\assets\categoryimage3.png" class="category__img "/>
                <div class="category__title">Ноутбуки</div>
            </div>
            <div class="category">
                <img src="src\assets\categoryimage4.png" class="category__img "/>
                <div class="category__title">Аксессуары</div>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import MyHeader from "@/components/Header/MyHeader.vue";
import MyCarousel from '../components/MyCarousel/MyCarousel.vue';

export default {
  components: {
    MyHeader,
    MyCarousel,
  },
};
</script>

<style>
    .welcome__carousel{
        margin-top: 80px;
        margin-bottom: 40px;
    }

    .categories{
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
    }

    .category{
        background-color: white;
        border-radius: 20px;
        text-align: center;
        width: 320px;
        box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.2);
        padding: 20px;
    }
</style>
