<template>
  <div>
    <div>
      <button class="button" v-on:click="edit" v-if="!create">Add</button>
      <button class="button" v-on:click="save" v-if="create">Save</button>
      <button class="button" v-on:click="cancel" v-if="create">Cancel</button>
    </div>
    <div v-if="create">
      <div v-bind:key="field.name" v-for="(field, index) in fields">
        <div>
          <div v-if="field.creatable">
            <label :for="field.name">{{ field.title }}</label>
            <b-input v-if="(field.type === 'text')"
                     v-model="createForm[field.name]" type="text"
            ></b-input>
            <b-input v-else-if="(field.type === 'number')"
                     v-model.number="createForm[field.name]" type="number"
            ></b-input>
            <div v-else-if="(field.type === 'color')" class="level">
              <color-picker v-model="createForm[field.name]"></color-picker>
            </div>
            <b-select v-else-if="field.type === 'select'"
                      v-model="createForm[field.name]">
              <option v-for="option in field.options" :key="option[field.keyProp]" :value="option[field.keyProp]" :selected="option[field.keyProp] == createForm[field.name]">
                {{ option[field.valueProp] }}
              </option>
            </b-select>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import ColorPicker from '../color/ColorPicker'

  export default {
    components: {
      'color-picker': ColorPicker
    },
    props: {
      fields: {
        type: Array,
        required: true
      },
      apiUrl: {
        type: String,
        required: true
      }
    },
    data () {
      return {
        color: {
          h: 255,
          s: 80,
          l: 50
        },
        create: false,
        createForm: { }
      }
    },
    methods: {
      updateColor (val) {
        this.createForm['hue'] = val.h
        this.createForm['saturation'] = val.s
        this.createForm['lightness'] = val.l
      },
      edit: function () {
        this.create = true
      },
      save: function () {
        // send required to store a new item
        axios.post(this.apiUrl, this.createForm).then(
          response => {
            // trigger event to collection
            this.$emit('save', response.data)
            // clear form
            this.cancel()
          },
          () => {
            alert('Invalid data')
          }
        )
      },
      cancel: function () {
        this.create = false
        this.createForm = {}
      }
    }
  }
</script>
