<template>
  <div class="header">
    <div class="container">
      <div class="header__content">
        <div class="header__catalog">
          <v-menu open-on-hover>
            <template v-slot:activator="{ props }">
              <v-icon
                color="rgba(155, 155, 155)"
                icon="mdi-store"
                size="x-large"
                v-bind="props"
              ></v-icon>
              <div class="catalog__title" v-bind="props">E-SHOP</div>
            </template>

            <v-list>
              <v-list-item v-for="(item, index) in items" :key="index">
                <v-list-item-title>
                  <v-menu open-on-hover location="end">
                    <template v-slot:activator="{ props }">
                      <div v-bind="props">{{ item.title }}</div>
                    </template>

                    <v-list>
                      <v-list-item v-for="(item, index) in items" :key="index">
                        <v-list-item-title>
                          {{ item.title }}
                        </v-list-item-title>
                      </v-list-item>
                    </v-list>
                  </v-menu>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>

        <div class="header__right">
          <router-link to="/bucket"
          class="bucket"
            >
            <span class="bucket--active" v-if="this.$store.getters['getCount'] > 0">{{this.$store.getters['getCount']}}</span>
            <v-icon
              color="rgba(155, 155, 155)"
              icon="mdi-cart-outline"
              size="x-large"
            ></v-icon
          ></router-link>
          <router-link to="/login"
            ><v-icon
              color="rgba(155, 155, 155)"
              icon="mdi-account"
              size="x-large"
            ></v-icon
          ></router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    items: [
      { title: "Click Me" },
      { title: "Click Me" },
      { title: "Click Me" },
      { title: "Click Me 2" },
    ],

  }),
};
</script>

<style>
.header {
  width: 100%;
  height: 80px;
  background-color: #ffffff;
  position: fixed;
  z-index: 10;
}

.header__content {
  height: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}

.header__catalog {
  display: flex;
  align-items: center;
}

.header__right {
  display: flex;
  gap: 10px;
  align-items: center;
}

.bucket{
  text-decoration: none;  
}

.bucket--active{
  position: relative;
  left: 39px;
  top: -12px;
  z-index: 999;
  padding: 1px 5px;
  background-color: orange;
  border-radius: 50px;
  font-size: 14px;
  font-weight: 400;
  color: #ffffff;
}

.catalog__title {
  font-size: 28px;
  font-weight: 500;
  font-style: italic;
  color: rgba(0, 0, 0, 0.65);
  cursor: pointer;
}
</style>
