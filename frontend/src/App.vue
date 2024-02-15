<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import FooterBar from '@/components/FooterBar.vue'
import StudentNavBar from './components/StudentNavBar.vue'
import NotLoginNavBar from './components/NotLoginNavBar.vue'
import { RouterView } from 'vue-router'
import { useUserStore } from '@/store/userStore'
import ChatIcon from './components/chatting/ChatIcon.vue'
import { useNotificationStore } from '@/store/notificationStore'
import { onMounted } from 'vue'
import Cookies from 'js-cookie'
import {instance} from '@/axios/axiosConfig'
import type { user } from './interface/common/interface'

const userStore = useUserStore()
const notificationStore = useNotificationStore()

onMounted(async () => {
  // 새로고침이나 페이지 이동 시 소켓, 구독 재연결
  if (userStore.id != -1) {
    notificationStore.socketReconnect(userStore.id, userStore.isTutor, userStore.isActiveCall)
  }else{
    if(typeof Cookies.get("JSESSIONID") === "string"){
      const url:string = import.meta.env.VITE_VUE_API_URL+'/mypage'
      await instance.get(url)
      .then((response)=>{
        const user:user ={
          id: response.data.userId,
          role: response.data.role,
          email: response.data.email,
          nickname: response.data.nickname,
          profile: response.data.profile
        }
        userStore.login(user);
      })
    }
  }
})

window.addEventListener('unload', function(){
  userStore.logout();
  for(let cookie in Cookies.get()) Cookies.remove(cookie);
});

</script>
<template>
  <div v-if="!userStore.isLogin">
    <NotLoginNavBar />
  </div>
  <div v-else>
    <NavBar v-if="userStore.isTutor" />
    <StudentNavBar v-else />
  </div>
  <div class="min-h-[1000px]" id="mainComponent">
    <RouterView id="main" />
  </div>
  <div v-if="userStore.isLogin">
    <ChatIcon />
  </div>
  <FooterBar />
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
