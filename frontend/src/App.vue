<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'

import StudentIndex from '@/pages/mainpage/StudentIndex.vue'
import TutorIndex from '@/pages/mainpage/TutorIndex.vue'
import ChatRoom from '@/components/ChatRoom.vue'
</script>

<template>
  <NavBar />
  <!-- <StudentIndex /> -->
  <TutorIndex />
  <ChatRoom />
</template>

<style scoped>
.test {
  height: 100%;
}
</style>
