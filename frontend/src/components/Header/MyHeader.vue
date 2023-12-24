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

            <tree-view :children="this.$store.state.treeViewItem" />
          </v-menu>
        </div>

        <div class="header__right">
          <admin-panel />

          <router-link to="/bucket" class="header__bucket">
            <span
              class="header__bucket--active"
              v-if="this.$store.getters['getCount'] > 0"
              >{{ this.$store.getters["getCount"] }}</span
            >
            <v-icon
              color="rgba(155, 155, 155)"
              icon="mdi-cart-outline"
              size="x-large"
            ></v-icon
          ></router-link>

          <v-menu
            open-on-hover
            v-if="!this.$store.state.initialState.status.loggedIn"
          >
            <template v-slot:activator="{ props }">
              <v-icon
                color="rgba(155, 155, 155)"
                icon="mdi-account"
                size="x-large"
                v-bind="props"
              ></v-icon>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title
                  ><router-link to="/login"
                    >Войти</router-link
                  ></v-list-item-title
                >
              </v-list-item>
              <v-list-item>
                <v-list-item-title
                  ><router-link to="/register"
                    >Регистрация</router-link
                  ></v-list-item-title
                >
              </v-list-item>
            </v-list>
          </v-menu>

          <template v-else>
            <p class="user-name">{{ $store.getters["getUserName"] }}</p>
            <div class="logout" @click="handleLogout">
              <v-icon
                color="rgba(155, 155, 155)"
                icon="mdi-logout"
                size="x-large"
              ></v-icon>
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminPanel from "../AdminPanel/AdminPanel.vue";
import TreeView from "../TreeView/TreeView.vue";
import { getCatalog, getCart } from "@/API/index.js";

export default {
  components: { TreeView, AdminPanel },
  data: () => ({}),

  beforeCreate() {
    const user = JSON.parse(localStorage.getItem("user"));
      if(user){
        getCart().then((res) => {
          //console.log(res)
          this.$store.commit("setCount", res.totalCount);
        })
      }
  },

  mounted() {
    getCatalog().then((data) => {
      this.$store.commit("setTreeView", data);
    });
  },

  methods: {
    handleLogout() {
      this.$store.dispatch("logout");
      if(this.$route.path == "/bucket"){
        this.$router.push("/")
      }
    },
  },
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

.header__bucket {
  text-decoration: none;
}

.header__bucket--active {
  position: relative;
  left: 38px;
  top: -14px;
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

.logout {
  cursor: pointer;
}
</style>
