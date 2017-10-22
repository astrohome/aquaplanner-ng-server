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
import EditableTableGrid from './table/EditableTable.vue'

export default {
  name: 'app',
  components: {
    'editable-table-grid': EditableTableGrid
  },
  data () {
    return {
      apiUrl: '/api/tasks',
      channelsUrl: '/api/channels',
      classes: 'table table-bordered',
      editable: true, // items could be modified
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
          apiUrl: '/api/channels',
          editable: true,
          creatable: true
        },
        {
          title: 'PWM',
          type: 'number',
          name: 'pwm',
          editable: true,
          creatable: true
        },
        {
          title: 'At time',
          type: 'text',
          name: 'atTime',
          editable: true,
          creatable: true
        }
      ]
    }
  }
}
</script>
