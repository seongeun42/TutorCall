<template>
  <Bar id="my-chart-id" :options="chartOptions" :data="chartData" />
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { Bar } from 'vue-chartjs'
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

// const props = defineProps(['chartData', 'chartOptions'])
const chartData = ref({
  labels: ['전달력', '매너', '전문성'],
  datasets: [
    {
      label: '만족',
      data: [40, 20, 12],
      backgroundColor: 'rgba(75, 192, 192, 0.2)',
      borderColor: 'rgba(75, 192, 192, 1)',
      borderWidth: 1
    },
    {
      label: '불만족',
      data: [10, 30, 8],
      backgroundColor: 'rgba(255, 99, 132, 0.2)',
      borderColor: 'rgba(255, 99, 132, 1)',
      borderWidth: 1
    }
  ]
})

const chartOptions = ref({
  responsive: true,
  scales: {
    y: {
      beginAtZero: true
    }
  }
})
onMounted(() => {})
</script>

<style>
#my-chart-id {
  width: 60%;
  height: 70%;
}
</style>
