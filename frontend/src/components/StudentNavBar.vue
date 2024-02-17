<script setup lang="ts">
import router from '@/router/index'
import { useUserStore } from '@/store/userStore'
import { useNotificationStore } from '@/store/notificationStore'
import { ref, type Ref } from 'vue'
import CallNotification from '@/components/CallNotification.vue'
import Cookies from 'js-cookie'

const modalShow: Ref<boolean> = ref(false)

function handleMode(show: boolean, mode: string) {
  modalShow.value = show
  //mode에 따라 수행할 기능이 다름.
}

const userStore = useUserStore()
const notificationStore = useNotificationStore()

function goQnABoard():void{
  router.push({"name":"qna"})
}

function goLectureBoard():void{
  router.push({"name":"lecturesPromo"})
}


function logout():void{
  userStore.logout();
  notificationStore.socketDisconnect();
  notificationStore.clear();
  sessionStorage.clear();
  for(let cookie in Cookies.get()) Cookies.remove(cookie);
  router.push("/");
}
</script>

<template>
  <nav class="bg-white">
    <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
      <div class="relative flex h-16 items-center justify-between">
        <div class="absolute inset-y-0 left-0 flex items-center sm:hidden"></div>
        <div class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
          <RouterLink to="/" class="flex flex-shrink-0 items-center">
            <img class="h-8 w-auto" src="@/img/logo.png" alt="Your Company" />
          </RouterLink>
          <div class="hidden sm:ml-6 sm:block flex-1 justify-evenly">
            <div class="flex space-x-4 justify-evenly">
              <!-- Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white" -->
              <a
                to="/lecturespromotion"
                class="font-semibold inline-block text-black-500 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm"
                aria-current="page"
                @click="goLectureBoard"
                >과외 모집</a
              >
              <router-link
                to="/qna"
                class="font-semibold inline-block text-black-500 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm"
                >문제 Q&A</router-link
              >
              <router-link
                to="/articles"
                class="font-semibold inline-block text-black-500 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm"
                >공지사항</router-link
              >
            </div>
          </div>
        </div>
        <div
          class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0"
        >
          <!-- Profile dropdown -->
          <div class="relative ml-3">
            <RouterLink :to="{name:'userUpdate'}">
              <button
                type="button"
                class="relative flex rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
                id="user-menu-button"
                aria-expanded="false"
                aria-haspopup="true"
              >
                <span class="absolute -inset-1.5"></span>
                <span class="sr-only">Open user menu</span>
                <img
                  class="h-8 w-8 rounded-full"
                  :src="userStore.profile"
                  alt=""
                />
              </button>
            </RouterLink>
          </div>
          <div class="ml-3 p-2 bg-sky-900 rounded-lg"
          @click="logout">
            <p class="text-white font-semibold">로그아웃</p>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
