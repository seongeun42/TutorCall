<script setup lang="ts">
import { ref, type Ref } from 'vue'
import NavBar from '@/components/NavBar.vue'
import FooterBar from '@/components/FooterBar.vue'
import StudentNavBar from './components/StudentNavBar.vue'
import NotLoginNavBar from './components/NotLoginNavBar.vue'
import { RouterView } from 'vue-router'
import { useUserStore } from '@/store/userStore'

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
  </div>
  <FooterBar />
</template>

<style scoped></style>
