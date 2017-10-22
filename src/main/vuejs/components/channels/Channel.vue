<template>
    <div>
        <slider v-model="colors" @change-color="onChange"></slider>
    </div>
</template>

<script>
import { Slider } from 'vue-color'

export default {
  name: 'led-channel-settings',
  components: {
    Slider
  },
  data () {
    return {
      colors: {
        hex: '#194d33',
        a: 1
      }
    }
  },
  methods: {
    // onChange method called when the event 'change-color' is emitted
    onChange (val) {
      this.colors = val
    }
  }
}
</script>
