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
            <div v-for="choice in card.choices" :key="choice.id">
              {{choice.prompt}}
            </div>
          </div>
          <div class="content">
            <button class="button is-success" type="button">Submit</button>

          </div>
        </b-tab-item>
        <b-tab-item label="Stats">
          Chart here with data
        </b-tab-item>
      </b-tabs>
    </div>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'PredictionCard',
  props: ['predictionData'],
  data () {
    return {
      activeTab: 0,
      card: {}
    }
  },
  mounted () {
    axios.get('/api/card/' + this.predictionData.id)
      .then(response => {
        this.card = response.data
      })
      .catch(() => {
      })
  }
}
</script>

<style scoped>
</style>
