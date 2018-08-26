<script>
import { Bar } from 'vue-chartjs'

export default {
  extends: Bar,
  props: ['stats'],
  mounted () {
    this.renderChart(this.chartData, {
      responsive: true,
      maintainAspectRatio: false,
      legend: {
        display: false
      }
    })
  },
  computed: {
    chartData: function () {
      let stats = this.stats
      let backgroundColors = []
      Object.keys(stats.aggregates).forEach(function (thing) {
        if (stats.selectedChoice.prompt === thing) {
          backgroundColors.push('#f91234')
        } else {
          backgroundColors.push('#028523')
        }
      })

      return {
        labels: Object.keys(this.stats.aggregates),
        datasets: [
          {
            label: 'Selection Count',
            backgroundColor: backgroundColors,
            data: Object.values(this.stats.aggregates)
          }
        ]
      }
    }
  }
}

</script>
