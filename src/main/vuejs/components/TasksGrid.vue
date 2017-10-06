<!-- component template -->
<template>
  <table class="table table-bordered table-hover">
    <thead>
      <tr>
        <th v-for="key in columns">
          {{ key }}
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="entry in data">
        <td v-for="key in columns">
          {{entry[key]}}
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>

    import moment from 'moment'

    export default {
        name: 'tasks-grid',
        props: {
            data: Array,
            columns: Array
        },
        filter: {
            formatDate: function(value) {
                if (value) {
                    return moment(String(value)).format('hh:mm')
                }
            }
        }
    }
</script>