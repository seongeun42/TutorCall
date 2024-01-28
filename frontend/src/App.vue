<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import { RouterView } from "vue-router";

import StudentIndex from '@/pages/mainpage/StudentIndex.vue'
import TutorIndex from '@/pages/mainpage/TutorIndex.vue'
import MyPage from './pages/mypage/MyPage.vue'
</script>

<template>
  <NavBar />
  <Router-View id="main" />
</template>

<style scoped>
.test {
  height: 100%;
}
</style>
