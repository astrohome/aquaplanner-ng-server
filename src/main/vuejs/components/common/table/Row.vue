<template>
  <div class="columns">
    <template v-for="(field, index) in fields">
      <div class="column" v-bind:key="field.id" v-if="(!field.id)">
        <b-input v-if="(field.type === 'text')" v-model="item[field.name]" type="text" :disabled="!editMode || !field.editable"></b-input>
        <b-input v-else-if="(field.type === 'number')" v-model.number="item[field.name]" type="number" :disabled="!editMode || !field.editable"></b-input>
        <div v-else-if="(field.type === 'color')">
          <color-picker v-model="item[field.name]" :fieldName="field.name" v-on:change="calcColor" :disabled="!editMode || !field.editable"></color-picker>
          <b-input v-model="item.color.hue" type="text" :disabled="!editMode || !field.editable"></b-input>
          <b-input v-model="item.color.saturation" type="text" :disabled="!editMode || !field.editable"></b-input>
          <b-input v-model="item.color.lightness" type="text" :disabled="!editMode || !field.editable"></b-input>
        </div>
        <b-select v-else-if="field.type === 'select'" v-model="item[field.name]" :disabled="!editMode || !field.editable">
          <option v-for="option in field.options" :key="option[field.keyProp]" :value="option[field.keyProp]" :selected="option[field.keyProp] == item[field.name]">
            {{ option[field.valueProp] }}
          </option>
        </b-select>
      </div>
    </template>
    <div class="column" v-if="editable || deletable">
      <button class="button" @click="edit" v-if="!editMode && editable">
        <b-icon icon="edit"></b-icon>
        <span>Edit</span>
      </button>

      <button class="button" @click="cancelEdit" v-if="editMode && editable">
        <b-icon icon="cancel"></b-icon>
        <span>Cancel</span>
      </button>

      <button class="button is-success" @click="update(item, editForm)" v-if="editMode && editable">
        <b-icon icon="save"></b-icon>
        <span>Save</span>
      </button>

      <button class="button is-danger" @click="destroy(item)" v-if="!editMode && deletable">
        <b-icon icon="delete"></b-icon>
        <span>Delete</span>
      </button>
    </div>
  </div>
</template>


<script>
import axios from 'axios'
import ColorPicker from '../color/ColorPicker'

export default {
  props: ['item', 'fields', 'apiUrl', 'editable', 'deletable'],
  components: {
    'color-picker': ColorPicker
  },
  data () {
    return {
      editMode: false,
      editForm: {}
    }
  },
  computed: {
    calcColor: function (val) {
      console.log(val)
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
      this.$dialog.confirm({
        title: 'Deleting',
        message: 'Are you sure you want to <b>delete</b> your account? This action cannot be undone.',
        confirmText: 'Delete Account',
        type: 'is-danger',
        hasIcon: true,
        onConfirm: () => {
          // send request to delete item
          axios.delete(this.apiUrl + item.id).then(
            response => {
              this.$emit('destroy', item)
              this.$toast.open('Account deleted!')
            },
            response => {
              alert('Invalid data')
            }
          )
        }
      })
    }
  }
}
</script>
