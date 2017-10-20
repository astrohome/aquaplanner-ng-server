import Vue from 'vue'
import App from './App.vue'
import router from './router'

import Buefy from 'buefy'
import 'buefy/lib/buefy.css'

import './css/main.css'

Vue.use(Buefy)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
