import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import 'bootstrap'

import 'bootstrap/dist/css/bootstrap.min.css'
import './css/main.css'

Vue.use(VueRouter);

Vue.config.productionTip = false;

const UserHome = { template: '<div>Home</div>' };

const routes = [
    { path: '/led/tasks', component: App},
    { path: '/led/home', component: UserHome }
    ];
    //{ path: '/led-config', component: LedConfig }


const router = new VueRouter({
    routes // short for `routes: routes`
});

const app = new Vue({
    router
}).$mount('#app');

/*
new Vue({
    el: '#app',
    render: h => h(App),
    template: '<App/>',
    components: { App }
});
*/
