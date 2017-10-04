<template>
    <div id="app">
        <p>{{ message }}</p>

        <input v-model="channel">
        <input v-model="startPwm">
        <input v-model="endPwm">
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
                startPwm: '',
                endPwm: '',
                gridColumns: ['id', 'channel', 'startPwm', 'endPwm'],
                gridData: [  ]
            }
        },
        created() {
            axios.get('/api/tasks').then((response) =>
                this.gridData = response.data
            )
                .catch(function (error) {
                    console.log(error);
                });
        },
        methods: {
            createTask: function () {
                axios.post('/api/tasks',
                    {"id": "", "channel": this.channel, "startPwm": this.startPwm, "endPwm": this.endPwm },
                    {
                        headers: {
                            'Content-type': 'application/json'
                        }
                    }).then((response) => {
                        axios.get('/api/tasks').then((response) =>
                            this.gridData = response.data
                        )
                        .catch(function (error) {
                            console.log(error);
                        })
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