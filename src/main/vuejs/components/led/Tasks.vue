<template>
  <section class="section">
    <div class="container">
      <editable-table-grid :apiUrl="apiUrl"
                           :fields="fields"
                           :editable="editable"
                           :deletable="deletable"
                           :creatable="creatable"
      ></editable-table-grid>
    </div>
  </section>
</template>

<script>
import EditableTableGrid from '../common/table/EditableTable.vue'

export default {
  name: 'led-tasks',
  components: {
    'editable-table-grid': EditableTableGrid
  },
  data () {
    return {
      apiUrl: '/api/led/tasks',
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
          apiUrl: '/api/led/channels',
          keyProp: 'channel',
          valueProp: 'name',
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
