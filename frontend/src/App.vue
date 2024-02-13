<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import FooterBar from '@/components/FooterBar.vue'
import StudentNavBar from './components/StudentNavBar.vue'
import NotLoginNavBar from './components/NotLoginNavBar.vue'
import { RouterView } from 'vue-router'
import { useUserStore } from '@/store/userStore'
import ChatIcon from './components/chatting/ChatIcon.vue'
import TutorCallPage from '@/pages/tutorcall/TutorCallPage.vue'

const userStore = useUserStore()
</script>

<template>
  <div>test</div>

</template>

<style scoped></style>
<style>
@font-face {
  font-family: 'Pretendard-Regular';
  src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff')
    format('woff');
  font-weight: 300;
  font-style: normal;
}
div {
  font-family: 'Pretendard-Regular';
}
</style>
