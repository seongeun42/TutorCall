<template>
  <div class="w-100% mt-20">
    <div class="mb-4">
      <div class="block text-3xl text-center truncate">
        <p class="font-black text-center">김선생님도 과외 홍보 게시판에 등록해보세요</p>
      </div>
    </div>
    <div class="flex flex-row space-x-5 justify-center mt-10">
      <!-- <img src="https://via.placeholder.com/200x300" class="w-full h-auto opacity-75" />
      <img src="https://via.placeholder.com/200x300" class="w-full h-auto opacity-75" />
      <img src="https://via.placeholder.com/200x300" class="w-full h-auto opacity-75" />
      <img src="https://via.placeholder.com/200x300" class="w-full h-auto opacity-75" /> -->
      <RecommendedTutorVue />
      <RecommendedTutorVue />
      <RecommendedTutorVue />
      <RecommendedTutorVue />
    </div>
  </div>
</template>

<script setup lang="ts">
import RecommendedTutorVue from './RecommendedTutor.vue'
</script>

<style scoped></style>
