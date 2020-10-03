// main.js 是vue入口文件, 用来初始化vue实例并集成所需要的插件
import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

Vue.config.productionTip = false;
// Vue.prototype.xxx 可以理解为Vue组件的全局变量, 可以在任意Vue组件中通过this.xxx来获得.
// $为全局变量属性的一个约定
Vue.prototype.$ajax = axios;

// 进行App中的数据渲染(app.vue), 替换#app(index.html)中的内容
new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});

console.log("环境：", process.env.NODE_ENV);