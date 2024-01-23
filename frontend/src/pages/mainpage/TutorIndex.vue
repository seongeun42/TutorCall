<template>
  <div class="bg-gray-200 font-sans font-thin px-6 bg-fixed bg-cover bg-no-repeat">
    <div class="mx-auto max-w-6xl bg-white py-10 px-6 lg:px-12 shadow-xl mb-24">
      <ReviewCarousel class="mt-10" />
      <AboutPage class="my-10" />
      <FindTutor />
    </div>
  </div>
</template>

<script setup lang="ts">
import AboutPage from './AboutPage.vue'
import FindTutor from './FindTutor.vue'
import ReviewCarousel from './ReviewCarousel.vue'
</script>

<style scoped></style>
