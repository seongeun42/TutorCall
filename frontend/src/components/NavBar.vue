<script setup lang="ts">
import { ref, type Ref, reactive } from 'vue'
import CallNotification from '@/components/CallNotification.vue'
import { useUserStore } from '@/store/userStore';
import { useNotificationStore } from '@/store/notificationStore'
import smallAlert from '@/components/tutorcallAlert/smallAlert.vue'
import { type alertForm } from '@/interface/tutorcall/interface'
import router from '@/router'
import Cookies from 'js-cookie';

const userStore = useUserStore();
const notificationStore = useNotificationStore()

function changeStatus() {
    if (!userStore.isActiveCall) {
    notificationStore.tagSubscribe()
  } else {
    for (let i = 0; i < notificationStore.tagSubs.length; i++) {
      notificationStore.unsubscribe(notificationStore.tagSubs[i])
    }
    notificationStore.clearTagSubs()
  }
  userStore.isActiveCall = !userStore.isActiveCall
}

const modalShow: Ref<boolean> = ref(false)
function handleMode(show: boolean, mode: string) {
  modalShow.value = show
  //mode에 따라 수행할 기능이 다름.
}

const viewAlertModal:Ref<boolean> = ref(false);
function handleViewAlert():void{
  viewAlertModal.value = !viewAlertModal.value;
}

function logout():void{
  userStore.logout();
  notificationStore.socketDisconnect();
  notificationStore.clear();
  sessionStorage.clear();
  for(let cookie in Cookies.get()) Cookies.remove(cookie);
  router.push("/");
}

/* * * * * * * * * * * * * * * 
*
* 테스트용 더미 데이터
*
* * * * * * * * * * * * * * */

const data:alertForm[] = reactive([
  { nickname: "싸뚜기",
    title: "ㅇㅇㅇ",
    content: "ㅇㅇㅇ",
    tag: {
      id: 1,
      subject: "수학",
      level: "고등학교",
      grade: 3
    },
    hide:false},
    { nickname: "싸뚜기2",
      title: "ㅇㅇㅇ",
    content: "ㅇㅇㅇ",
    tag: {
      id: 2,
      subject: "과학",
      level: "고등학교",
      grade: 3
    },
    hide:false}
]);

function hideAlert(item:{hide:boolean}):void{
  item.hide = !item.hide;
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
              <router-link
                to="/lecturespromotion"
                class="font-semibold inline-block text-black-500 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm"
                aria-current="page"
                >과외 모집</router-link
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
              <div class="flex items-center">
                <p class="ms-3 text-sm font-semibold text-black-500 dark:text-gray-300">
                  화상과외 활성화
                </p>

                <label class="ml-3 relative inline-flex items-center cursor-pointer">
                  <input type="checkbox" v-model="userStore.isActiveCall" class="sr-only peer" @click="changeStatus()"/>
                  <div
                    class="w-11 h-6 bg-gray-200 rounded-full peer peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"
                  ></div>
                </label>
              </div>
            </div>
          </div>
        </div>
        <div
          class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0"
        >
        
          <!-- Profile dropdown -->
          <div class="relative ml-3">
            <RouterLink :to="{name:'tutorUpdate'}">
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
                  src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                  alt=""
                />
              </button>
            </RouterLink>
            </div>
            <div
              tabindex="0"
              type="button"
              role="button"
              class="relative rounded-full border-none bg-green-200 p-1 text-gray-400 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800 ml-8"
              @click="handleViewAlert"
            >
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="black" class="w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 6.75c0 8.284 6.716 15 15 15h2.25a2.25 2.25 0 0 0 2.25-2.25v-1.372c0-.516-.351-.966-.852-1.091l-4.423-1.106c-.44-.11-.902.055-1.173.417l-.97 1.293c-.282.376-.769.542-1.21.38a12.035 12.035 0 0 1-7.143-7.143c-.162-.441.004-.928.38-1.21l1.293-.97c.363-.271.527-.734.417-1.173L6.963 3.102a1.125 1.125 0 0 0-1.091-.852H4.5A2.25 2.25 0 0 0 2.25 4.5v2.25Z" />
              </svg>
            </div>
            <div v-if="viewAlertModal" id="alretbox" class="fixed right-0
            top-24 min-w-[24rem] min-h-[36rem] 
            bg-yellow-50 z-50 rounded-l-lg shadow-xl">
            <div id="bar" class="w-full max-h-20 flex justify-between items-center justify-center">
              <div class="flex justify-center items-center h-full ml-4">
                <div class="chat chat-end">
                  <div class="chat-bubble bg-green-200">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="black" class="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 6.75c0 8.284 6.716 15 15 15h2.25a2.25 2.25 0 0 0 2.25-2.25v-1.372c0-.516-.351-.966-.852-1.091l-4.423-1.106c-.44-.11-.902.055-1.173.417l-.97 1.293c-.282.376-.769.542-1.21.38a12.035 12.035 0 0 1-7.143-7.143c-.162-.441.004-.928.38-1.21l1.293-.97c.363-.271.527-.734.417-1.173L6.963 3.102a1.125 1.125 0 0 0-1.091-.852H4.5A2.25 2.25 0 0 0 2.25 4.5v2.25Z" />
                    </svg>
                  </div>
                </div>
                <p class="text-center font-semibold">튜터콜 요청</p>
              </div>
              <button class="btn border-none bg-yellow-50 mr-2" @click="handleViewAlert">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="m5.25 4.5 7.5 7.5-7.5 7.5m6-15 7.5 7.5-7.5 7.5" />
                </svg>
              </button>
            </div>
            <div v-for="(item, index) in notificationStore.problems" :key="index">
              <smallAlert v-if="!item.hide" :data="item"
              @change="hideAlert(item)"/>
            </div>
          </div>
          <div class="ml-3 p-2 bg-sky-900 rounded-lg"
          @click="logout">
            <p class="text-white font-semibold">로그아웃</p>
          </div>
          </div>
        </div>
      </div>
  </nav>
  <CallNotification
    v-if="modalShow"
    :data="dummyData"
    :show="modalShow"
    @update:clicked="handleMode"
  />
</template>

<style scoped></style>
