<template>
  <div class="bg-gray-200 font-sans font-thin px-6 bg-fixed bg-cover bg-no-repeat">
    <div class="mx-auto max-w-6xl bg-white py-20 px-6 lg:px-12 shadow-xl mb-24">
      <template v-if="!isTutor"
        ><ProblemUpload class="mb-10" />
        <AboutPage class="mb-10" />
        <ReviewCarousel
      /></template>
      <template v-if="isTutor"
        ><ReviewCarousel class="mt-10" />
        <AboutPage class="my-10" />
        <FindTutor
      /></template>
    </div>
  </div>
</template>

<script setup lang="ts">
import AboutPage from './AboutPage.vue'
import ProblemUpload from './ProblemUpload.vue'
import ReviewCarousel from './ReviewCarousel.vue'
import FindTutor from './FindTutor.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'

// 추후 로직 구현 필요(비회원 & 학생 / 선생님 회원 구분)
const isTutor: Ref<boolean> = ref(false)
</script>

<style scoped></style>
