<template>
  <div class="card">
    <div class="card-content">
      <b-tabs position="is-centered" v-model="activeTab">
        <b-tab-item label="Prediction">
          <div class="level">
          <div class="level-left">
            <div class="level-item is-size-4">
              {{card.question.prompt}}
            </div>
          </div>
          <div class="level-right"><div class="level-item is-size-4">{{card.category}} </div></div>
          </div>
          <div class="content">
            <ChoicesForm :choices="card.choices" @submitted="submittedPrediction"></ChoicesForm>
          </div>
        </b-tab-item>
        <b-tab-item label="Stats">
          {{stats}}
        </b-tab-item>
      </b-tabs>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import ChoicesForm from './ChoicesForm'

export default {
  name: 'PredictionCard',
  components: {ChoicesForm},
  props: ['predictionData'],
  data () {
    return {
      activeTab: 0,
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
    }
  }
}
</script>

<style scoped>
</style>
