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
              <div class="catalog__title" v-bind="props">Каталог</div>
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
          <v-icon
            color="rgba(155, 155, 155)"
            icon="mdi-cart-outline"
            size="x-large"
          ></v-icon>
          <v-icon
            color="rgba(155, 155, 155)"
            icon="mdi-account"
            size="x-large"
          ></v-icon>
        </div>
        
      </div>
    </div>
  </div>
</template>

<script>
import { storeToRefs } from 'pinia';
import { useStore } from '@/store/index.js'

const store = useStore();

const { initialState } = storeToRefs(store);

console.log(initialState)


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

.catalog__title {
  font-size: 25px;
  color: rgba(0, 0, 0, 0.7);
  cursor: pointer;
}
</style>
