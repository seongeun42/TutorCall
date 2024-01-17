<template>
  <div class="bg-gray-200 font-sans font-thin px-6 bg-fixed bg-cover bg-no-repeat">
    <div class="mx-auto max-w-6xl bg-white py-20 px-6 lg:px-12 shadow-xl mb-24">
      <ProblemUpload />
      <AboutPage />
      <FindTutor />
    </div>
  </div>
</template>

<script setup lang="ts">
import AboutPage from './AboutPage.vue'
import FindTutor from './FindTutor.vue';
import ProblemUpload from './ProblemUpload.vue'
</script>

<style scoped></style>
