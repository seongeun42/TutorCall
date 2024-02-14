<template>
  <div class="flex justify-center items-center">
    <p class="font-bold text-3xl">평균 총점</p>
    <div class="flex mx-32">
      <div class="flex" v-for="index in score" :key="index">
        <img src="@/img/star.png" class="star" alt="" />
      </div>
      <p class="mx-12 text-3xl">{{ score }}점</p>
    </div>
  </div>
  <ReviewChart class="my-32" />
  <p class="font-bold text-2xl">학생 리뷰</p>
  <p class="border-2 my-10"></p>
  <!-- <ReviewHistory /> -->
</template>

<script setup lang="ts">
import ReviewChart from './ReviewChart.vue'
import ReviewHistory from './ReviewHistory.vue'

const score: number = 4
</script>

<style scoped>
.star {
  width: 30px;
  height: 30px;
}
</style>
