<template>
    <div>
        <div class="row">
            <button class="button" v-on:click="edit" v-if="!create">Add</button>
            <button class="button" v-on:click="save" v-if="create">Save</button>
            <button class="button" v-on:click="cancel" v-if="create">Cancel</button>
        </div>
        <div v-if="create" class="row">
            <div v-for="(field, index) in fields">
                <div>
                    <div class="form-group form-inline" v-if="field.creatable">
                        <label :for="field.name">{{ field.title }}</label>
                        <b-input type="text" class="form-control"
                               v-model="createForm[field.name]"
                               :id="field.name"
                        ></b-input>

                        <b-input v-if="(field.type === 'text')" class="form-control"
                               v-model="createForm[field.name]" type="text"
                        ></b-input>
                        <b-input v-else-if="(field.type === 'number')" class="form-control"
                               v-model="createForm[field.name]" type="number"
                        ></b-input>
                        <b-select v-else-if="field.type === 'select'" class="form-control"
                                v-model="createForm[field.name]">
                            <option v-for="option in channels" :key="option.id" :selected="option.id == createForm[field.name]"
                                    v-bind:value="option.channel">
                                {{ option.text }}
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

    export default {
        props: {
            fields: {
                type: Array,
                required: true
            },
            apiUrl: {
                type: String,
                required: true
            },
            channels: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                create: false,
                createForm: {}
            }
        },
        methods: {
            edit: function () {
                this.create = true;
            },
            save: function () {
                // send required to store a new item
                axios.post(this.apiUrl, this.createForm).then(response => {
                    //trigger event to collection
                    this.$emit('save', response.data);
                    // clear form
                    this.cancel();
                }, () => {
                    alert('Invalid data');
                });
            },
            cancel: function () {
                this.create = false;
                this.createForm = {};
            }
        }
    }
</script>
