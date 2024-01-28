<template>
  <!-- <div v-for="i in 9" :key="i"> -->
  <div class="flex my-10 items-center justify-between">
    <div class="flex items-center">
      <img src="@/img/student.jpg" class="w-14 h-14 rounded-full" alt="" />
      <p class="ml-5">김잼민</p>
      <img src="@/img/accuse.png" class="w-8 h-8 ml-2 mr-10" alt="" />
      <div class="flex ml-40">
        <p class="mr-5">평점</p>
        <StarScore :score="score" />
      </div>
    </div>
    <div>2023.10.12</div>
  </div>
  <div class="flex">
    <p class="text-lg">
      매너가 좀 많이 부족한 것 같아요. 그리고 무엇보다 학력 위조의 의심이 들 정도로 설명을 못하던데
      누구 가르치는 일은 반드시 접고 다른 일 알아보시면 좋겠습니다.
    </p>
  </div>
  <!-- </div> -->
</template>

<script setup lang="ts">
import StarScore from './StarScore.vue'

const score: number = 1
</script>

<style scoped></style>
