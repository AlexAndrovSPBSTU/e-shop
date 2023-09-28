<template>
  <div class="footer">
    <div class="container">
      <div class="footer__content">
        <div class="top__footer">
          <div class="top__footer-content">
            <div class="top__footer-title">Нам доверяют</div>
            <div class="top__footer-text">
              Работаем с 2000 года, за 23 года открыли более 1000 магазинов
            </div>
          </div>
          <div class="top__footer-content">
            <div class="top__footer-title">У нас покупают</div>
            <div class="top__footer-text">
              Более 20000 покупок в месяц совершают в нашем магазине
            </div>
          </div>
          <div class="top__footer-content">
            <div class="top__footer-title">Работаем 24/7</div>
            <div class="top__footer-text">
              Магазины работают круглосуточно, как отдел продажи, так и
              техподдержка
            </div>
          </div>
          <div class="top__footer-content">
            <div class="top__footer-title">Цены ниже рынка</div>
            <div class="top__footer-text">
              Наши цены ниже в среднем на 20%, нашли дешевле - вернем разницу!
            </div>
          </div>
        </div>
        <div class="bottom__footer">
          <div class="footer__info">
            <div class="footer__info-title">О нас</div>
            <ul class="footer__list">
              <li class="list-item">Главная</li>
              <li class="list-item">Каталог</li>
              <li class="list-item">FAQ</li>
            </ul>
          </div>
          <div class="footer__info">
            <div class="footer__info-title">Контакты</div>
            <ul class="footer__list">
              <li class="list-item">198555, Невский пр. 140, офис 140</li>
              <li class="list-item">8 (812) 123-45-67</li>
              <li class="list-item">Обратный звонок</li>
            </ul>
          </div>
          <div class="footer__info">
            <div class="footer__info-title">Ссылки</div>
            <ul class="footer__list">
              <li class="list-item">
                <v-icon
                  color="rgba(221, 42, 123)"
                  icon="mdi-instagram"
                  size="x-large"
                  v-bind="props"
                ></v-icon>
              </li>
              <li class="list-item">
                <v-icon
                  color="rgba(0, 66, 255)"
                  icon="mdi-facebook"
                  size="x-large"
                  v-bind="props"
                ></v-icon>
              </li>
              <li class="list-item">
                <v-icon
                  color="rgba(6, 162, 247)"
                  icon="mdi-twitter"
                  size="x-large"
                  v-bind="props"
                ></v-icon>
              </li>
            </ul>
          </div>
          <div class="footer__info">
            <div class="footer__info-title"></div>
            <ul class="footer__list">
              <li class="list-item">Политика конфиденциальности</li>
            </ul>
          </div>
        </div>
        <div class="footer__extra">
          Лучший магазин (с) 2023 Все права защищены. Интернет-магазин
                оборудования для салонов красоты
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.footer__content {
  display: flex;
  flex-direction: column;
}

.top__footer,
.bottom__footer {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.top__footer-content {
  background-color: white;
  border-radius: 20px;
  width: 315px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.2);
}

.top__footer-title {
  font-size: 21px;
  font-weight: 500;
}

.top__footer-text {
  max-width: 220px;
  text-align: center;
  font-size: 16px;
}

.bottom__footer {
  margin-top: 100px;
  margin-bottom: 50px;
}

.footer__info-title{
  font-weight: 500;
}


.list-item{
  max-width: 200px;
  list-style-type: none;
}

.footer__extra{
  text-align: center;
  margin-bottom: 30px;
}
</style>
