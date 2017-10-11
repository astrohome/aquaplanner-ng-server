<template>
    <div class="row">
        <template v-for="(field, index) in fields">

            <div v-if="(!field.id)" class="col col-md-2">

                <input v-if="(field.type === 'text')" class="form-control"
                       v-model="item[field.name]" type="text" :disabled="!editMode || !field.editable"
                >
                <input v-else-if="(field.type === 'number')" class="form-control"
                       v-model="item[field.name]" type="number" :disabled="!editMode || !field.editable"
                >
                <select v-else-if="field.type === 'select'" class="form-control"
                        v-model="item[field.name]" :disabled="!editMode || !field.editable">
                    <option v-for="option in channels" :selected="option.channel == item[field.name]"
                            v-bind:value="option.channel">
                        {{ option.text }}
                    </option>
                </select>
            </div>
        </template>
        <div class="col col-md-2" v-if="editable || deletable">
            <button type="button" class="btn btn-info"
                    v-on:click="edit" v-if="!editMode && editable"
            >
                Edit
            </button>

            <button type="button" class="btn btn-default"
                    v-on:click="cancelEdit" v-if="editMode && editable"
            >
                Cancel
            </button>

            <button type="button" class="btn btn-primary"
                    v-on:click="update(item, editForm)" v-if="editMode && editable"
            >
                Update
            </button>

            <button type="button" class="btn btn-danger"
                    v-on:click="destroy(item)" v-if="!editMode && deletable"
            >
                Delete
            </button>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        props: ['item', 'channels', 'fields', 'apiUrl', 'editable', 'deletable'],
        data() {
            return {
                editMode: false,
                editForm: {}
            }
        },
        methods: {
            edit() {
                this.editMode = true;
                let self = this;

                // get fields for the form
                this.fields.forEach(function (field, i) {
                    self.editForm[field.name] = self.item[field.name];
                });
            },
            cancelEdit() {
                this.editMode = false;
                let self = this;
                // clear the form
                this.fields.forEach(function (field, i) {
                    self.editForm[field.name] = '';
                });
            },
            update(oldItem, newItem) {
                this.edit();
                // send request to update data
                axios.patch(this.apiUrl + oldItem.id, newItem).then(response => {
                    this.$emit('update', response.data);
                    this.cancelEdit();
                }, (response) => {
                    alert('Invalid data');
                });
            },
            destroy(item) {
                // send request to delete item
                axios.delete(this.apiUrl + item.id).then(response => {
                    this.$emit('destroy', item);
                }, (response) => {
                    alert('Invalid data');
                });
            },
        }
    }
</script>
