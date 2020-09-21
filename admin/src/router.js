// 路由配置
import Vue from 'vue';
import Router from 'vue-router'
import Login from './views/login'
import Admin from './views/admin'
import Welcome from './views/admin/welcome'
import Chapter from './views/admin/chapter'

Vue.use(Router);

// mode: history/hash => 代表了不同的URL 格式
// children 子路由不需要加 '/'
export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '*',
        redirect: "/login"
    },{
        path: '/login',
        component: Login
    },{
        path: '/',
        name: 'admin',
        component: Admin,
        children: [{
            path: 'welcome',
            name: 'welcome',
            component: Welcome
        },{
            path: 'business/chapter',
            name: 'business/chapter',
            component: Chapter
        }]
    }]
})