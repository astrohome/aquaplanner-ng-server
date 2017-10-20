import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import Channel from './components/channels/Channel.vue'
import Buefy from 'buefy'
import 'buefy/lib/buefy.css'

import './css/main.css'

Vue.use(VueRouter);
Vue.use(Buefy);

Vue.config.productionTip = false;

const routes = [
    { path: '/led/tasks', component: App},
    {path: '/led/channels', component: Channel}
    ];


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
