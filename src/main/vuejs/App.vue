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
    </div>
</template>

<script>
    import EditableTableGrid from './components/EditableTable.vue'
    import axios from 'axios'

    export default {
        name: 'app',
        components: {
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
