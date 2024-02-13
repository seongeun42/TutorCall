<script setup lang="ts">
import type { review } from '@/interface/common/interface';
import StarScore from './StarScore.vue'
const props = defineProps<{data: review}>();
const score:number = Math.round((props.data.communicationRate+props.data.mannerRate+props.data.professionalismRate)/3)

</script>
<template>
  <!-- <div v-for="i in 9" :key="i"> -->
  <div class="flex my-10 items-center justify-between">
    <div class="flex items-center">
      <img src="@/img/student.jpg" class="w-14 h-14 rounded-full" alt="" />
      <p class="ml-5">나의 리뷰</p>
      <img src="@/img/accuse.png" class="w-8 h-8 ml-2 mr-10" alt="" />
      <div class="flex ml-40">
        <p class="mr-5">평점</p>
        <StarScore :score="score" />
      </div>
    </div>
    <div>{{ data.createAt }}</div>
  </div>
  <div class="flex">
    <p class="text-lg">
      {{ data.content }}
    </p>
  </div>
  <!-- </div> -->
</template>
<style scoped>
</style>
