// main.js 是vue入口文件, 用来初始化vue实例并集成所需要的插件
import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

// 进行App中的数据渲染(App.vue), 替换#app(index.html)中的内容
new Vue({
  render: h => h(App),
}).$mount('#app')
