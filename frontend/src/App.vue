<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import OnlineLecture from '@/pages/onlinelecture/OnlineLecture.vue'
import ChatRoom from '@/components/chatting/ChatRoom.vue'
import MyPage from './pages/mypage/MyPage.vue'
import SignUp from './pages/account/SignUp.vue'
import FooterBar from './components/FooterBar.vue'
</script>

<template>
  <div id="app">
    <NavBar />
    <div class="content">
      <MyPage />
    </div>

    <FooterBar class="footer" />
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
.footer {
}
</style>
