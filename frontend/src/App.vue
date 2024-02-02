<script setup lang="ts">
import { ref, type Ref } from 'vue'
import NavBar from '@/components/NavBar.vue'
import MainPage from '@/pages/mainpage/MainPage.vue'
import Mypage from '@/pages/mypage/MyPage.vue'
import StudentNavBar from './components/StudentNavBar.vue'
import NotLoginNavBar from './components/NotLoginNavBar.vue'
import { RouterView } from "vue-router";
import {useUserStore} from "@/store/userStore";

const userStore = useUserStore();

</script>

<template>
  <div v-if="!userStore.isLogin">
    <NotLoginNavBar />
  </div>
  <div v-else>
    <NavBar v-if="userStore.isTutor" />
    <StudentNavBar v-else />
  </div>
  <Router-View id="main" />
  <!-- <Mypage /> -->
</template>

<style scoped></style>
