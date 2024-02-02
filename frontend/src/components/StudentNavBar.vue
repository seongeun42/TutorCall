<script setup lang="ts">
import router from '@/router/index'
import { useUserStore } from '@/store/userStore'
import { ref, type Ref } from 'vue'
import CallNotification from '@/components/CallNotification.vue'

interface notifyDate {
  message: string
}

const dummyData: notifyDate = {
  message: '테스트 데이터 알림'
}

const modalShow: Ref<boolean> = ref(false)
function handleMode(show: boolean, mode: string) {
  modalShow.value = show
  //mode에 따라 수행할 기능이 다름.
}

const userStore = useUserStore()
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
                to="/notice"
                class="font-semibold inline-block text-black-500 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm"
                >공지사항</router-link
              >
            </div>
          </div>
        </div>
        <div
          class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0"
        >
          <div class="dropdown dropdown-end">
            <div
              tabindex="0"
              type="button"
              role="button"
              class="relative rounded-full bg-gray-800 p-1 text-gray-400 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
            >
              <span class="absolute -inset-1.5"></span>
              <span class="sr-only">View notifications</span>
              <svg
                class="h-6 w-6"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                aria-hidden="true"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0"
                />
              </svg>
              <ul
                tabindex="0"
                class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52"
              >
                <li><a>Item 1</a></li>
                <li><a>Item 2</a></li>
              </ul>
            </div>
          </div>
          <!-- Profile dropdown -->
          <div class="relative ml-3">
            <RouterLink to="/mypage/">
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

            <!--
            Dropdown menu, show/hide based on menu state.

            Entering: "transition ease-out duration-100"
              From: "transform opacity-0 scale-95"
              To: "transform opacity-100 scale-100"
            Leaving: "transition ease-in duration-75"
              From: "transform opacity-100 scale-100"
              To: "transform opacity-0 scale-95"
          -->
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
