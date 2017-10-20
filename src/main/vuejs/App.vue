<template>
    <div id="app">
        <router-link to="/led/tasks" class="list-group-item">Tasks</router-link>
        <router-link to="/led/channels" class="list-group-item">Channel settings</router-link>

        <router-view></router-view>
    </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>