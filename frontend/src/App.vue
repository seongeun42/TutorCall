<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'

import StudentIndex from '@/pages/mainpage/StudentIndex.vue'
import TutorIndex from '@/pages/mainpage/TutorIndex.vue'
import MyPage from './pages/mypage/MyPage.vue'
import QA from './pages/board/q&a/QA.vue'
</script>

<template>
  <NavBar />
  <!-- <StudentIndex /> -->
  <!-- <TutorIndex /> -->
  <MyPage />
  <!-- <QA /> -->
</template>

<style scoped>
.test {
  height: 100%;
}
</style>
