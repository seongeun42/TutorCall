<script setup lang="ts">
import { ref, type Ref } from 'vue'
import NavBar from '@/components/NavBar.vue'
import MainPage from '@/pages/mainpage/MainPage.vue'
import Mypage from '@/pages/mypage/MyPage.vue'
import StudentNavBar from './components/StudentNavBar.vue'
import NotLoginNavBar from './components/NotLoginNavBar.vue'
const isTutor: Ref<boolean> = ref(false)
const isLogin: Ref<boolean> = ref(false)
</script>

<template>
  <div v-if="!isLogin">
    <NotLoginNavBar />
  </div>
  <div v-else>
    <NavBar v-if="isTutor" />
    <StudentNavBar v-else />
  </div>
  <!-- <MainPage /> -->
  <Mypage />
</template>

<style scoped></style>
