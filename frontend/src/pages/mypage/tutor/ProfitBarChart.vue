<template>
  <div class="w-full h-full flex justify-center items-center">
    <Bar id="my-chart-id" :options="chartOptions" :data="chartData" />
  </div>
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
  PointElement,
  CategoryScale,
  LinearScale
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, PointElement, CategoryScale, LinearScale)
const chartData = ref({
  labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
  datasets: [
    {
      label: '과외 학생 수',
      backgroundColor: '#d161fe',
      data: [40, 39, 10, 40, 39, 80, 40, 50, 50, 60, 80, 70]
    },
    {
      label: '과외 1건당 평균 수익',
      backgroundColor: '#94b7fd',
      data: [50000, 39000, 100000, 40000, 39000, 80000, 40000, 50000, 50000, 60000, 80000, 70000]
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
