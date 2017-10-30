<template>
  <section class="section">
    <div class="container">
      <editable-table-grid :apiUrl="apiUrl" :fields="fields" :editable="editable" :deletable="deletable" :creatable="creatable"></editable-table-grid>
    </div>
  </section>
</template>

<script>
import EditableTableGrid from '../common/table/EditableTable.vue'

export default {
  name: 'led-channels',
  components: {
    'editable-table-grid': EditableTableGrid
  },
  data () {
    return {
      apiUrl: '/api/channels',
      editable: true, // items could be modified
      creatable: true, // items could be created
      deletable: true, // items could be deleted
      fields: [
        {
          title: 'Channel',
          type: 'select',
          name: 'channel',
          options: [],
          apiUrl: '/api/led/physical-channels',
          keyProp: 'id',
          valueProp: 'text',
          editable: true,
          creatable: true
        },
        {
          title: 'Color',
          type: 'color',
          name: 'color',
          editable: true,
          creatable: true
        },
        {
          title: 'Name',
          type: 'text',
          name: 'name',
          editable: true,
          creatable: true
        }
      ]
    }
  }
}
</script>
