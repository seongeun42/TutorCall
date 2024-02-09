<template>
  <div class="bg-gray-200 font-sans font-thin px-6 bg-fixed bg-cover bg-no-repeat">
    <div class="mx-auto max-w-6xl bg-white py-20 px-6 lg:px-12 shadow-xl mb-24">
      <template v-if="!userStore.isTutor"
        ><ProblemUpload class="mb-10" />
        <AboutPage class="mb-10" />
        <ReviewCarousel
      /></template>
      <template v-if="userStore.isTutor"
        ><ReviewCarousel class="mt-10" />
        <AboutPage class="my-10" />
        <FindTutor
      /></template>
    </div>
  </div>
</template>

<script setup lang="ts">
import AboutPage from '@/pages/mainpage/AboutPage.vue'
import ProblemUpload from '@/pages/mainpage/ProblemUpload.vue'
import ReviewCarousel from '@/pages/mainpage/ReviewCarousel.vue'
import FindTutor from '@/pages/mainpage/FindTutor.vue'
import { useUserStore } from '@/store/userStore'

const userStore = useUserStore()
</script>

<style scoped></style>