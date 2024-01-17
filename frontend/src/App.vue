<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import SideBar from '@/pages/tutorcall/SideBar.vue'
import TutorCallPage from '@/pages/tutorcall/TutorCallPage.vue'
import MatchCall from '@/pages/tutorcall/MatchCall.vue'
import MatchText from '@/pages/tutorcall/MatchText.vue'
</script>

<template>
  <div class="flex flex-col test">
    <NavBar />
    <!--    <TutorCallPage class="h-full overflow-hidden relative" />-->
    <div class="h-full relative flex justify-center items-center">
      <MatchText></MatchText>
      <MatchCall></MatchCall>
    </div>
  </div>
</template>

<style scoped>
.test {
  height: 100%;
}
</style>
