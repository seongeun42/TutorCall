<script setup lang="ts">
import { ref, type Ref } from 'vue'
import NavBar from '@/components/NavBar.vue'
import FooterBar from '@/components/FooterBar.vue'
import StudentNavBar from './components/StudentNavBar.vue'
import NotLoginNavBar from './components/NotLoginNavBar.vue'
const isTutor: Ref<boolean> = ref(false)
const isLogin: Ref<boolean> = ref(true)
</script>

<template>
  <div id="app">
    <div v-if="!isLogin">
      <NotLoginNavBar />
    </div>
    <div v-else>
      <NavBar v-if="isTutor" />
      <StudentNavBar v-else />
    </div>
    <RouterView />
    <FooterBar />
  </div>
</template>

<style scoped></style>
