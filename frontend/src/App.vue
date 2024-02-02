<script setup lang="ts">
import { ref, type Ref } from 'vue'
import NavBar from '@/components/NavBar.vue'
import MainPage from '@/pages/mainpage/MainPage.vue'
import Mypage from '@/pages/mypage/MyPage.vue'
import StudentNavBar from '@/components/StudentNavBar.vue'
import NotLoginNavBar from '@/components/NotLoginNavBar.vue'
import FooterBar from '@/components/FooterBar.vue'
import BoardEditor from './pages/board/editor/StudentBoardEditor.vue'
import OnlineLecture from './pages/onlinelecture/OnlineLecture.vue'
const isTutor: Ref<boolean> = ref(false)
const isLogin: Ref<boolean> = ref(false)
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
    <div class="content">
      <BoardEditor />
      <!-- <OnlineLecture /> -->
    </div>
    <!-- <FooterBar /> -->
  </div>
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
