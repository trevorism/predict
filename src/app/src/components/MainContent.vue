<template>
  <div class="container">
    <b-loading :is-full-page="true" :active.sync="loading" :can-cancel="false"></b-loading>
    <div v-for="item in data" :key="item.id">
      <PredictionCard :predictionData="item"></PredictionCard>
    </div>
  </div>
</template>

<script>
import PredictionCard from './PredictionCard.vue'
import axios from 'axios'

export default {
  name: 'MainContent',
  components: {
    PredictionCard
  },
  mounted () {
    axios.get('/api/prediction')
      .then(response => {
        this.data = response.data
        this.loading = false
      })
      .catch(() => {
        this.loading = false
      })
  },
  data () {
    return {
      data: [],
      loading: true
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
