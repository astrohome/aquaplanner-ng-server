<template>
    <div id="app">
        <p>{{ message }}</p>

        <input v-model="id">
        <input v-model="channel">
        <button v-on:click="createTask">Submit</button>

        <tasks-grid
                :data="gridData"
                :columns="gridColumns">
        </tasks-grid>
    </div>
</template>

<script>
    import TasksGrid from './Grid.vue'
    import axios from 'axios'

    export default  {
        name: 'app',
        components:  { 'tasks-grid': TasksGrid },
        data() {
            return {
                message: 'Hello Vue.js!',
                id: '',
                channel: '',
                gridColumns: ['name', 'power'],
                gridData: [
                    { name: 'Chuck Norris', power: Infinity },
                    { name: 'Bruce Lee', power: 9000 },
                    { name: 'Jackie Chan', power: 7000 },
                    { name: 'Jet Li', power: 8000 }
                ]
            }
        },

        methods: {
            createTask: function () {
                debugger;
                axios.post('/api/tasks',
                    {"id": this.id, "channel": this.channel},
                    {
                        headers: {
                            'Content-type': 'application/json'
                        }
                    });
            }
        }
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