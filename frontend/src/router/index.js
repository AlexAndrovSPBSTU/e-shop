// Composables
import Register from "@/components/Register/Register.vue";
import Login from "@/components/Login/Login.vue";
import Card from "@/components/Card/Card.vue";
import Welcome from "@/layouts/WelcomePage.vue";
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/catalog',
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
    path: "/catalog/:title",
    component: Card,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
