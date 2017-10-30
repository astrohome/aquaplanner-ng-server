<template>
  <div>
    <div class="columns">
      <template v-for="field in fields">
        <div v-if="!field.id" class="column" :key="'_' + field.name" :id="'_' + field.name"
             :class="[field.titleClass]"
             v-html="field['title']"
        ></div>
      </template>
      <div class="column">
        Actions
      </div>
    </div>

    <template v-for="(item, index) in tableData">
      <div :key="item.id" class="column">
        <row :item="item" :fields="fields" :apiUrl="resourceUrl" :deletable="deletable"
           :editable="editable"
           v-on:destroy="destroy"
           v-on:update="update"
      ></row>
      </div>
    </template>
    <br>
    <create-item-form v-if="creatable" :fields="fields" :apiUrl="apiUrl"
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
        tableData: [],
        resourceUrl: ''
      }
    },
    beforeMount () {
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
      loadData (success = this.loadSuccess, failed = this.loadFailed) {
        this.fields.forEach((field, i) => {
          if (field.apiUrl !== undefined) {
            axios.get(field.apiUrl).then((result) => {
              this.$set(this.fields[i], 'options ', result.data)
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
      save: function () {
        this.loadData()
      }
    }
  }
</script>
