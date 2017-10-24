<template>
  <div class="container-fluid">

    <div>
      <editable-table-grid :apiUrl="apiUrl"
                           :fields="fields"
                           :editable="editable"
                           :deletable="deletable"
                           :creatable="creatable"
      ></editable-table-grid>
    </div>
  </div>
</template>

<script>
  import EditableTableGrid from '../common/table/EditableTable.vue'

  export default {
    name: 'led-channels',
    components: {
      'editable-table-grid': EditableTableGrid
    },
    data() {
      return {
        apiUrl: '/api/channels',
        editable: true, // items could be modified
        creatable: true, // items could be created
        deletable: true, // items could be deleted
        fields: [
          {
            title: 'ID',
            type: 'number',
            name: 'id',
            id: true,
            editable: false,
            creatable: false
          },
          {
            title: 'Color',
            type: 'text',
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
          },
          {
            title: 'Channel',
            type: 'select',
            name: 'channel',
            apiUrl: '/api/physical-channels',
            editable: true,
            creatable: true
          }
        ]
      }
    }
  }
</script>
