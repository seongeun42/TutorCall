<script setup lang="ts">
import { useUserStore } from '@/store/userStore'
import { useVideoStore } from '@/store/videoStore'
const userStore = useUserStore()

const videoStore = useVideoStore()
</script>
<template>
  <div v-for="(message, index) in videoStore.messages" :key="index">
    <div v-if="userStore.nickname === message.userName">
      <div class="chat chat-start mx-2 my-1">
        <div class="chat-image avatar">
          <div class="w-10 rounded-full">
            <img :src="userStore.profile" />
          </div>
        </div>
        <div class="chat-header">{{ message.userName }}</div>
        <div class="chat-bubble">{{ message.message }}</div>
      </div>
    </div>
    <div v-if="userStore.nickname !== message.userName">
      <div class="chat chat-end mx-2 my-1">
        <div class="chat-image avatar">
          <div class="w-10 rounded-full">
            <img src="https://daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg" />
          </div>
        </div>
        <div class="chat-header">{{ message.userName }}</div>
        <div class="chat-bubble">{{ message.message }}</div>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
