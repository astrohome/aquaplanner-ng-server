<template>
  <section>
    <b-modal :active.sync="showSelector" :width="350">
      <div class="card">
        <div class="card-content">
      <div class="color-picker__flyout">
        <div class="color-chip" v-bind:style="{'background-color': calcColor()}">
          <div class="color-chip__inner">
          </div>
        </div>
        <div class="color-picker__inner">
          <div class="control" v-bind:style="gradientH" style="background-image: linear-gradient(to right, rgb(253, 50, 50), rgb(253, 253, 50), rgb(50, 253, 50), rgb(50, 253, 253), rgb(50, 50, 253), rgb(253, 50, 253), rgb(253, 50, 50));">
            <input type="range" v-model="value.hue" @change="notify()" min="0" max="360">
          </div>
          <div class="control" v-bind:style="gradientS" style="background-image: linear-gradient(to right, rgb(253, 253, 253), rgb(0, 97, 253));">
            <input type="range" v-model="value.saturation" @change="notify()" min="0" max="100">
          </div>
          <div class="control" v-bind:style="gradientL" style="background-image: linear-gradient(to right, rgb(0, 0, 0), rgb(51, 129, 255));">
            <input type="range" v-model="value.lightness" @change="notify()" min="0" max="100">
          </div>
        </div>
      </div>
      </div>
      </div>
      <br>
      <button class="button is-success" @click="showSelector = false">Close</button>
    </b-modal>

    <div class="final" v-bind:style="{'background-color': calcColor(), 'cursor': calcPointer()}" @click="show()"></div>
  </section>
</template>


<script>
  export default {
    name: 'color-picker',
    props: {
      value: {
        default: () => {
          return {
            hue: 255,
            saturation: 100,
            lightness: 50
          }
        },
        type: Object
      },
      fieldName: {
        default: 'color',
        type: String
      },
      disabled: {
        default: false,
        type: Boolean
      }
    },
    data () {
      return {
        showSelector: false
      }
    },
    methods: {
      show () {
        if (!this.disabled) {
          this.showSelector = true
        }
      },
      calcColor () {
        var c = this.value.hue + ', ' + this.value.saturation + '%, ' + this.value.lightness + '%'
        var s = 'hsl(' + c + ')'
        return s
      },
      calcPointer () {
        if (this.disabled) { return 'not-allowed' } else return 'pointer'
      },
      notify () {
        this.$emit('change', {
          value: this.value,
          fieldName: this.fieldName
        })
      }
    },
    computed: {
      gradientH: function () {
        let stops = []
        for (let i = 0; i < 7; i++) {
          let h = i * 60

          let hsl = hsb2hsl(parseFloat(h / 360), parseFloat(this.value.saturation) / 100, parseFloat(this.value.lightness / 100))

          let c = hsl.h + ', ' + hsl.s + '%, ' + hsl.l + '%'
          stops.push('hsl(' + c + ')')
        }

        return {
          backgroundImage: 'linear-gradient(to right, ' + stops.join(', ') + ')'
        }
      },
      gradientS: function () {
        let stops = []
        let c
        let hsl = hsb2hsl(parseFloat(this.value.hue / 360), 0, parseFloat(this.value.lightness / 100))
        c = hsl.h + ', ' + hsl.s + '%, ' + hsl.l + '%'
        stops.push('hsl(' + c + ')')

        hsl = hsb2hsl(parseFloat(this.value.hue / 360), 1, parseFloat(this.value.lightness / 100))
        c = hsl.h + ', ' + hsl.s + '%, ' + hsl.l + '%'
        stops.push('hsl(' + c + ')')

        return {
          backgroundImage: 'linear-gradient(to right, ' + stops.join(', ') + ')'
        }
      },

      gradientL: function () {
        let stops = []
        let c

        let hsl = hsb2hsl(parseFloat(this.value.hue / 360), 0, 0)
        c = hsl.h + ', ' + hsl.s + '%, ' + hsl.l + '%'
        stops.push('hsl(' + c + ')')

        hsl = hsb2hsl(parseFloat(this.value.hue / 360), parseFloat(this.value.saturation / 100), 1)

        c = hsl.h + ', ' + hsl.s + '%, ' + hsl.l + '%'
        stops.push('hsl(' + c + ')')

        return {
          backgroundImage: 'linear-gradient(to right, ' + stops.join(', ') + ')'

        }
      }
    }
  }

  function hsb2hsl (h, s, b) {
    let hsl = {
      h: h
    }
    hsl.l = (2 - s) * b
    hsl.s = s * b

    if (hsl.l <= 1 && hsl.l > 0) {
      hsl.s /= hsl.l
    } else {
      hsl.s /= 2 - hsl.l
    }

    hsl.l /= 2

    if (hsl.s > 1) {
      hsl.s = 1
    }

    if (!hsl.s > 0) hsl.s = 0

    hsl.h *= 360
    hsl.s *= 100
    hsl.l *= 100

    return hsl
  }
</script>

<style scoped>
.final {
  width: 24px;
  height: 24px;
  margin: 0.5rem;
  border: 4px solid white;
  box-shadow: 0px 0px 1px rgba(0, 0, 0, 0.3);
  cursor: pointer;
}

.final:hover {
  border: 4px solid white;
  opacity: 0.8;
  box-shadow: 0px 0px 1px rgba(0, 0, 0, 0.3);
}

.color-picker__flyout {
  width: 200px;
  border: 1px solid #eee;
  border-radius: 4px;
  background: white;
  box-shadow: 0px 3px 7px rgba(0, 0, 0, 0.30);
  font-family: "Roboto", "Helvetica Neue", sans-serif;
}

.color-picker__inner {
  padding: 1.5rem 1rem;
}

.color-chip {
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  border-radius: 4px 4px 0 0;
}

.color-chip__inner {
  text-align: center;
}

.color-chip__subtitle {
  margin-top: 0.5rem;
  opacity: 0.7;
  font-weight: normal;
  font-size: 15px;
  text-shadow: 0px 1px 2px rgba(0, 0, 0, 0.5);
}

.color-chip__title {
  color: white;
  margin: 0;
  font-size: 50px;
  letter-spacing: 4px;
  font-family: Helvetica Neue;
  text-shadow: 0px 1px 2px rgba(0, 0, 0, 0.5);
}

.control {
  width: 100%;
  height: 12px;
  border-radius: 12px;
  border: 1px solid #ddd;
}

.control + .control {
  margin-top: 1rem;
}

.control input {
  width: 100%;
  margin: 0;
}

.control input[type=range] {
  -webkit-appearance: none;
  width: 100%;
  background: transparent;
}

.control input[type=range]:focus {
  outline: none;
}

.control input[type=range]::-ms-track {
  width: 100%;
  cursor: pointer;
  background: transparent;
  border-color: transparent;
  color: transparent;
}

.control input[type=range]::-webkit-slider-thumb {
  -webkit-appearance: none;
  border: 1px solid #ddd;
  height: 20px;
  width: 20px;
  border-radius: 50px;
  background: #ffffff;
  cursor: pointer;
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.12);
  margin-top: -4px;
}

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
