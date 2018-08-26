<template>
  <section>
    <div v-for="choice in choices" :key="choice.id">
      <div class="field">
        <b-radio v-model="choiceValue" :native-value=choice.id :disabled="disabled">
          {{choice.prompt}}
        </b-radio>
      </div>
    </div>

    <footer>
      <button class="button is-primary" :disabled="disabled" @click="invokeButton">
        Submit
      </button>
    </footer>
  </section>
</template>

<script>
import axios from 'axios'

export default {
  props: ['choices'],
  data () {
    return {
      choiceValue: null,
      disabled: false
    }
  },
  methods: {
    invokeButton: function () {
      let choiceValue = this.choiceValue
      let selectedChoice = this.choices.find(function (element) {
        return element.id === choiceValue
      })
      if (!selectedChoice) {
        return
      }

      axios.post('/api/card/', selectedChoice)
        .then(response => {
          this.$emit('submitted', response.data)
          this.disabled = true
        })
        .catch(() => {
        })
    }
  }
}

</script>
