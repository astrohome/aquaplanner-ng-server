<template>
    <div class="container-fluid" id="app">
        <p>{{ message }}</p>

        <div class="row">
            <div class="col-lg-3">
                <input type="number" class="form-control" max="8" min="1" v-model="channel" placeholder="Channel">
            </div>
            <div class="col-lg-3">
                <div class="input-group">
                    <input type="number" class="form-control" max="100" min="0" v-model="startPwm">
                    <span class="input-group-addon">%</span>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="input-group">
                    <input type="number" class="form-control" max="100" min="0" v-model="endPwm">
                    <span class="input-group-addon">%</span>
                </div>
            </div>
            <button class="btn btn-primary" v-on:click="createTask">Create new task</button>
        </div>


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