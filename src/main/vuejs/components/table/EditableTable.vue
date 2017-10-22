<template>
  <div>
    <div class="row" v-cloak>
      <template v-for="field in tableFields">
        <div v-if="!field.id" class="col col-md-2" :key="'_' + field.name" :id="'_' + field.name"
             :class="[field.titleClass]"
             v-html="field['title']"
        ></div>
      </template>
      <div class="col col-md-2">
        Actions
      </div>
    </div>

    <template v-for="(item, index) in tableData">
      <row :item="item" :fields="tableFields" :apiUrl="resourceUrl" :deletable="deletable"
           :editable="editable"
           v-on:destroy="destroy"
           v-on:update="update"
      ></row>
    </template>
    <br>
    <create-item-form v-if="creatable" :fields="tableFields" :apiUrl="apiUrl"
                      v-on:save="save"></create-item-form>
  </div>
</template>

<script>
  import CreateItemForm from './CreateItemForm.vue'
  import Row from './Row.vue'
  import axios from 'axios'

  export default {
    components: {
      row: Row,
      'create-item-form': CreateItemForm
    },
    props: {
      apiUrl: {
        type: String,
        required: true
      },
      fields: {
        type: Array,
        required: true
      },
      classes: {
        type: String,
        required: false
      },
      editable: {
        type: Boolean,
        required: false,
        default: true
      },
      creatable: {
        type: Boolean,
        required: false,
        default: true
      },
      deletable: {
        type: Boolean,
        required: false,
        default: true
      }
    },
    data () {
      return {
        tableFields: [],
        tableData: [],
        resourceUrl: ''
      }
    },
    mounted () {
      // get unified fields
      this.normalizeFields()
      // get unified api url
      this.normalizeUrl()
      // load data
      this.loadData()
    },
    methods: {
      normalizeUrl () {
        if (typeof this.apiUrl === 'undefined') {
          console.error('You need to provide "api url" prop.')
          return
        }
        this.resourceUrl = this.apiUrl.trim('/')
        this.resourceUrl = this.apiUrl + '/'
      },
      normalizeFields () {
        this.tableFields = []
        // check fields
        if (typeof this.fields === 'undefined') {
          console.error('You need to provide "fields" prop.')
          return
        }
        let self = this
        let obj
        // normilize to one style
        this.fields.forEach(function (field, i) {
          if (typeof field === 'string') {
            obj = {
              name: field,
              title: field,
              type: field,
              titleClass: '',
              id: false,
              options: [],
              apiUrl: '',
              editable: true,
              creatable: true
            }
          } else {
            obj = {
              name: field.name,
              title: field.title === undefined ? field.name : field.title,
              titleClass: field.titleClass === undefined ? '' : field.titleClass,
              type: field.type === undefined ? 'text' : field.type,
              apiUrl: field.apiUrl,
              id: field.id === undefined ? false : field.id,
              options: field.options === undefined ? [] : field.options,
              editable: field.editable === undefined ? true : field.editable,
              creatable: field.creatable === undefined ? true : field.creatable
            }
          }
          self.tableFields.push(obj)
        })
      },
      loadData (success = this.loadSuccess, failed = this.loadFailed) {
        this.tableFields.forEach((field) => {
          if (field.apiUrl !== undefined) {
            axios.get(field.apiUrl).then((result) => {
              field.options = result.data
            })
          }
        })
        axios.get(this.apiUrl).then(success, failed)
      },
      loadSuccess (response) {
        this.tableData = response.data
      },
      loadFailed (response) {
        console.error('load-error', response)
      },
      destroy: function (item) {
        // destroy from the collection
        let index = this.tableData.indexOf(item)
        this.tableData.splice(index, 1)
      },
      update: function (item) {
        // update in the collection
        let index = this.tableData.indexOf(item)
        this.tableData[index] = item
      },
      save: function (item) {
        // add a new item to the collection
        this.tableData.push(item)
      }
    }
  }
</script>
