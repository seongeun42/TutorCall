<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import SideBar from '@/pages/tutorcall/SideBar.vue'
import TutorCallPage from '@/pages/tutorcall/TutorCallPage.vue'
import SpeechBubble from '@/pages/tutorcall/SpeechBubble.vue'
</script>

<template>
  <div class="flex flex-col test">
    <!--    <NavBar />-->
    <!--    <TutorCallPage class="h-full overflow-hidden relative" />-->
    <SpeechBubble class="w-60"> </SpeechBubble>
  </div>
</template>

<style scoped>
.test {
  height: 100%;
}
</style>
