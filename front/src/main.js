import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/normal.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import api from './api/index'
Vue.prototype.$api = api
//echarts
import * as echarts from 'echarts'
Vue.prototype.$echarts = echarts
// console.log('---echarts---', echarts);

// 持久化存储
// let user = localStorage.getItem('user')
// if (user) {
//   user = JSON.parse(user)
//   store.commit('loginModule/setUser', user)
// }

Vue.use(ElementUI)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


