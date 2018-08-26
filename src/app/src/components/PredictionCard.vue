<template>
  <div class="card">
    <div class="card-content">
      <b-tabs position="is-centered" v-model="activeTab">
        <b-tab-item label="Prediction">
          <div class="level">
          <div class="level-left">
            <div class="level-item is-size-4" v-if="card.question">
              {{card.question.prompt}}
            </div>
          </div>
          <div class="level-right"><div class="level-item is-size-4">{{card.category}} </div></div>
          </div>
          <div class="content">
            <ChoicesForm :choices="card.choices" @submitted="submittedPrediction"></ChoicesForm>
          </div>
        </b-tab-item>
        <b-tab-item label="Stats" :disabled="statsDisabled">
          <div v-if="!statsDisabled">
            <statistics :width="900" :height="240" :stats="stats"></statistics>
          </div>
        </b-tab-item>
      </b-tabs>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import ChoicesForm from './ChoicesForm'
import Statistics from './Statistics'

export default {
  name: 'PredictionCard',
  components: {Statistics, ChoicesForm},
  props: ['predictionData'],
  data () {
    return {
      activeTab: 0,
      statsDisabled: true,
      card: {},
      stats: {}
    }
  },
  mounted () {
    axios.get('/api/card/' + this.predictionData.id)
      .then(response => {
        this.card = response.data
      })
      .catch(() => {
      })
  },
  methods: {
    submittedPrediction: function (stats) {
      this.activeTab = 1
      this.stats = stats
      this.statsDisabled = false
    }
  }
}
</script>

<style scoped>
</style>
