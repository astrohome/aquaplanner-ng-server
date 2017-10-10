<template>
    <div class="row">
        <template v-for="(field, index) in fields">

                <div class="col border-dark col-lg-1">
                    <input v-if="field.type === 'text' && editMode && field.editable" type="text" class="form-control"
                           v-model="item[field.name]"
                    >
                    <select v-else-if="field.type === 'select' && editMode && field.editable" v-model="item[field.name]"
                            class="form-control">
                        <option v-for="option in channels" v-bind:value="option.value">
                            {{ option.text }}
                        </option>
                    </select>
                    <span class="form-control" v-else>{{ item[field.name] }}</span>
                </div>
        </template>
        <div class="col col-lg-2" v-if="editable || deletable">
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
                update
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
