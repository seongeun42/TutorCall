<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import FooterBar from '@/components/FooterBar.vue'
import StudentNavBar from './components/StudentNavBar.vue'
import NotLoginNavBar from './components/NotLoginNavBar.vue'
import { RouterView } from 'vue-router'
import { useUserStore } from '@/store/userStore'
import editor from '@/pages/board/editor/CkEditor.vue'

const userStore = useUserStore()
import ChatIcon from './components/chatting/ChatIcon.vue'
const isTutor: Ref<boolean> = ref(false)
const isLogin: Ref<boolean> = ref(false)
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
    <!-- <RouterView id="main" /> -->
    <editor/>
  </div>
  <FooterBar />
  <ChatIcon />
</template>

<style scoped></style>
