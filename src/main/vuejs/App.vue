<template>
    <div class="container-fluid">

        <div>
            <editable-table-grid :apiUrl="apiUrl"
                                 :fields="fields"
                                 :classes="classes"
                                 :editable="editable"
                                 :deletable="deletable"
                                 :creatable="creatable"
                                 :channelsUrl="channelsUrl"
            ></editable-table-grid>
        </div>

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
    import TasksGraph from './components/TasksGraph.vue'
    import EditableTableGrid from './components/EditableTable.vue'
    import axios from 'axios'

    export default {
        name: 'app',
        components: {
            'tasks-graph': TasksGraph,
            'editable-table-grid': EditableTableGrid
        },
        data() {
            return {
                apiUrl: '/api/tasks',
                channelsUrl: '/api/channels',
                classes: 'table table-bordered',
                editable: true,  // items could be modified
                creatable: true, // items could be created
                deletable: true, // items could be deleted
                fields: [
                    {
                        title: 'ID',
                        type: 'text',
                        name: 'id',
                        id: true,
                        editable: false,
                        creatable: false
                    },
                    {
                        title: 'Channel',
                        type: 'select',
                        name: 'channel',
                        editable: true,
                        creatable: true
                    },
                    {
                        title: 'Start PWM',
                        type: 'number',
                        name: 'startPwm',
                        editable: true,
                        creatable: true
                    },
                    {
                        title: 'End PWM',
                        type: 'number',
                        name: 'endPwm',
                        editable: true,
                        creatable: true
                    },
                    {
                        title: 'Start time',
                        type: 'text',
                        name: 'startTime',
                        editable: true,
                        creatable: true
                    },
                    {
                        title: 'End time',
                        type: 'text',
                        name: 'endTime',
                        editable: true,
                        creatable: true
                    }
                ]
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
