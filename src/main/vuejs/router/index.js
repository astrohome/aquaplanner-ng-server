import Vue from 'vue'
import Router from 'vue-router'
import Tasks from '../components/led/Tasks.vue'
import Channel from '../components/led/Channel.vue'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/led/tasks',
      name: 'Tasks',
      component: Tasks
    },
    {
      path: '/led/channels',
      name: 'Channel',
      component: Channel
    }
  ]
})
