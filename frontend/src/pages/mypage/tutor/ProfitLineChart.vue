<template>
  <div class="flex justify-center items-center">
    <Line id="my-chart-id" :options="chartOptions" :data="chartData" />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { Line } from 'vue-chartjs'
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  PointElement,
  CategoryScale,
  LinearScale
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, LineElement, PointElement, CategoryScale, LinearScale)
const chartData = ref({
  labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
  datasets: [
    {
      label: '2024년',
      backgroundColor: '#4cb700',
      data: [
        100000, 130000, 170000, 420000, 390000, 800000, 400000, 500000, 500000, 600000, 800000,
        700000
      ]
    }
  ]
})

const chartOptions = ref({
  responsive: true,
  maintainAspectRatio: false
})
</script>

<style scoped>
/* #my-chart-id {
    width: 1000px;
    height: 1000px;
  } */
</style>
