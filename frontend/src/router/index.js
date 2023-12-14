// Composables
import Register from "@/components/Register/Register.vue";
import Login from "@/components/Login/Login.vue";
import Card from "@/components/Card/Card.vue";
import Welcome from "@/layouts/WelcomePage.vue";
import Bucket from "@/components/Bucket/Bucket.vue";
import Admin from "@/components/AdminPanel/Admin.vue"
import { createRouter, createWebHistory } from 'vue-router'

import { isAdmin } from "@/utils";

const routes = [
  {
    path: '/categories/:id/products',
    component: () => import('@/layouts/Catalog.vue'),
  },
  {
    path: "/",
    component: Welcome,
  },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/bucket",
    component: Bucket,
  },
  {
    path: "/products/:id",
    component: Card,
  },
  {
    path: "/admin",
    component: Admin,
    beforeEnter: (to, from, next) => {
      if (isAdmin()) {
        next();
      }
      else {
        next(false);
      }
    }
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
