import Vue from 'vue'
import Router from 'vue-router'
import LedTasks from '../components/led/Tasks.vue'
import LedSettings from '../components/led/Channel.vue'
import PeripheralSettings from '../components/peripheral/Settings.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  linkActiveClass: 'is-active',
  routes: [
    {
      path: '/led/scheduling',
      name: 'led-scheduling',
      component: LedTasks
    },
    {
      path: '/led/settings',
      name: 'led-settings',
      component: LedSettings
    },
    {
      path: '/peripheral/settings',
      name: 'peripheral-settings',
      component: PeripheralSettings
    },
    {
      path: '/peripheral/scheduling',
      name: 'peripheral-scheduling',
      component: PeripheralSettings
    }
  ]
})
