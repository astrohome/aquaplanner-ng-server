<template>
    <div class="container-fluid" id="app">
        <p>{{ message }}</p>

        <div class="row">
            <div class="col-lg-1">
                <input type="number" class="form-control" max="8" min="1" v-model="channel" required placeholder="Channel">
            </div>
            <div class="col-lg-1">
                <div class="input-group">
                    <input type="number" class="form-control" max="100" min="0" required v-model="startPwm">
                    <span class="input-group-addon">%</span>
                </div>
            </div>
            <div class="col-lg-1">
                <div class="input-group">
                    <input type="number" class="form-control" max="100" min="0" required v-model="endPwm">
                    <span class="input-group-addon">%</span>
                </div>
            </div>
            <div class="col-lg-1">
                <div class="input-group">
                    <input type="text" required class="form-control" v-model="startTime"/>
                </div>
            </div>
            <div class="col-lg-1">
                <div class="input-group">
                    <input type="text" required class="form-control" v-model="endTime" />
                </div>
            </div>
            <button class="btn btn-primary" v-on:click="createTask">Create new task</button>
        </div>


        <tasks-grid
                :data="gridData"
                :columns="gridColumns">
        </tasks-grid>
        <tasks-graph :data="{
                  labels: ['January', 'February', 'March'],
                  datasets: [
                                    {
                                        label: 'GitHub Commits',
                                        backgroundColor: '#f87979',
                                        data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11]
                                    }
                  ]
        }"
                     :width="400"
                     :height="200">

        </tasks-graph>
    </div>
</template>

<script>
    import TasksGrid from './components/TasksGrid.vue'
    import TasksGraph from './components/TasksGraph.vue'
    import axios from 'axios'

    export default  {
        name: 'app',
        components:  { 'tasks-grid': TasksGrid, 'tasks-graph': TasksGraph },
        data() {
            return {
                message: 'Hello Vue.js!',
                id: '',
                channel: '',
                startPwm: '',
                endPwm: '',
                startTime: '',
                endTime: '',
                gridColumns: ['id', 'channel', 'startPwm', 'endPwm', 'startTime', 'endTime'],
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
                    {"id": "", "channel": this.channel, "startPwm": this.startPwm, "endPwm": this.endPwm, "startTime": this.startTime, "endTime": this.endTime },
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