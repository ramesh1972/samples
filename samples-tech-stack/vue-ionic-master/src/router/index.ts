import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import Props from '../views/Props.vue'
import LearnVue from '../views/LearnVue.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/learnVue'
  },
  {
    path: '/learnVue',
    name: 'LearnVue',
    component: LearnVue
  },
  {
    path: '/props',
    name: 'Props',
    component: Props
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
