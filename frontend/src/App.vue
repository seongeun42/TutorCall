<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import FooterBar from '@/components/FooterBar.vue'
import StudentNavBar from './components/StudentNavBar.vue'
import NotLoginNavBar from './components/NotLoginNavBar.vue'
import OnlineLecture from './pages/onlinelecture/OnlineLecture.vue'
import { RouterView } from 'vue-router'
import { useUserStore } from '@/store/userStore'
import ChatIcon from './components/chatting/ChatIcon.vue'

const userStore = useUserStore()
</script>

<template>
  <div v-if="!userStore.isLogin">
    <NotLoginNavBar />
  </div>
  <div v-else>
    <NavBar v-if="userStore.isTutor" />
    <StudentNavBar v-else />
  </div>
  <div class="min-h-[1000px]">
    <RouterView id="main" />
    <!-- <OnlineLecture /> -->
  </div>
  <ChatIcon />
  <FooterBar />
</template>

<style scoped></style>
