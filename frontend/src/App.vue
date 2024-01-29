<script setup lang="ts">
import { ref, type Ref } from 'vue'
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
#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh; /* 100% viewport height */
}
.content {
  padding-bottom: 20px;
}
</style>
<style scoped></style>
