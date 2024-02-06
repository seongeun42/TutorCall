<template>
  <div class="">
    <p class="font-bold text-2xl">최근 1년 수익</p>
    <ProfitLineChart class="h-96" />
    <p class="mt-32 font-bold text-2xl">학생 수 대비 수입</p>
    <ProfitBarChart class="h-96" />
  </div>
</template>

<script setup lang="ts">
import ProfitBarChart from './ProfitBarChart.vue'
import ProfitLineChart from './ProfitLineChart.vue'
</script>

<style scoped></style>
