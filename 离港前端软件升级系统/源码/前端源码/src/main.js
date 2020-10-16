import Vue from 'vue';
import App from './App.vue';
import router from "./router";
import store from './store';
// 导入公共样式
import './assets/css/main.css';
// 导入element-ui
import ElementUI from 'element-ui';
// 导入element-ui样式
import 'element-ui/lib/theme-chalk/index.css';
// 导入element-ui
import VueResource from 'vue-resource';
// Vue使用ElementUI
Vue.use(ElementUI);
//Vue使用http
Vue.use(VueResource);
Vue.config.productionTip = false;
//登录方法
router.beforeEach((to, from, next) => {
  // document.title = `${to.meta.title} | vue-manage-system`;
  if (to.path === '/login') {
    next();
  } else {
    const token = localStorage.getItem('token');
    if (token == null || token == '') {
      next('/login');
    } else {
      next();
    }
  }
});

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
