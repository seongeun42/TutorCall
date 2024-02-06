<script setup lang="ts">
import router from '@/router/index'
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
            </div>
          </div>
        </div>
        <div class="flex">
          <RouterLink
            :to="{ name: 'signform' }"
            class="font-semibold inline-block text-black-500 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm"
          >
            로그인 / 회원가입
          </RouterLink>
        </div>
      </div>
    </div>
  </nav>
</template>
<style scoped></style>
