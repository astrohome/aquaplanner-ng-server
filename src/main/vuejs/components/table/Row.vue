<template>
    <div class="row">
        <template v-for="(field, index) in fields">

            <div v-if="(!field.id)">

                <b-input v-if="(field.type === 'text')"
                       v-model="item[field.name]" type="text" :disabled="!editMode || !field.editable"
                ></b-input>
                <b-input v-else-if="(field.type === 'number')"
                         v-model="item[field.name]" type="number" :disabled="!editMode || !field.editable"
                ></b-input>
                <b-select placeholder="Select a channel" v-else-if="field.type === 'select'"
                        v-model="item[field.name]" :disabled="!editMode || !field.editable">
                    <option v-for="option in field.options" :key="option.id" :selected="option.channel == item[field.name]"
                            v-bind:value="option.channel">
                        {{ option.text }}
                    </option>
                </b-select>
            </div>
        </template>
        <div v-if="editable || deletable">
            <button class="button" @click="edit" v-if="!editMode && editable">
                Edit
            </button>

            <button class="button"
                    @click="cancelEdit" v-if="editMode && editable"
            >
                Cancel
            </button>

            <button class="button"
                    @click="update(item, editForm)" v-if="editMode && editable"
            >
                Update
            </button>

            <button class="button"
                    @click="destroy(item)" v-if="!editMode && deletable"
            >
                Delete
            </button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  props: ['item', 'fields', 'apiUrl', 'editable', 'deletable'],
  data () {
    return {
      editMode: false,
      editForm: {}
    }
  },
  methods: {
    edit () {
      this.editMode = true
      let self = this

      // get fields for the form
      this.fields.forEach(function (field, i) {
        self.editForm[field.name] = self.item[field.name]
      })
    },
    cancelEdit () {
      this.editMode = false
      let self = this
      // clear the form
      this.fields.forEach(function (field, i) {
        self.editForm[field.name] = ''
      })
    },
    update (oldItem, newItem) {
      this.edit()
      // send request to update data
      axios.patch(this.apiUrl + oldItem.id, newItem).then(
        response => {
          this.$emit('update', response.data)
          this.cancelEdit()
        },
        response => {
          alert('Invalid data')
        }
      )
    },
    destroy (item) {
      // send request to delete item
      axios.delete(this.apiUrl + item.id).then(
        response => {
          this.$emit('destroy', item)
        },
        response => {
          alert('Invalid data')
        }
      )
    }
  }
}
</script>
