<script setup lang="ts">
import { useUserStore } from '@/store/userStore'
import { useVideoStore } from '@/store/videoStore'
import { computed, ref, type Ref, watch } from 'vue'
const userStore = useUserStore()
interface chatForm {
  userName: string
  message: string
}
const videoStore = useVideoStore()
const chatMessageList: Ref<chatForm[]> = ref([])
const chatMessage = computed(() => {
  return chatMessageList.value[chatMessageList.value.length - 1]
})
watch(videoStore.messages, (newMessages) => {
  chatMessageList.value = newMessages.map((message) => ({
    userName: message.userName, // userName이 필요한 경우 주석 해제
    message: message.message
  }))
})
</script>
<template>
  <div v-if="chatMessage">
    <div v-if="userStore.nickname === chatMessage.userName">
      <div class="chat chat-start mx-2 my-1">
        <div class="chat-image avatar">
          <div class="w-10 rounded-full">
            <img
              alt="Tailwind CSS chat bubble component"
              src="https://daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg"
            />
          </div>
        </div>
        <div class="chat-header">{{ chatMessage.userName }}</div>
        <div class="chat-bubble">{{ chatMessage.message }}</div>
      </div>
    </div>
    <div v-if="userStore.nickname !== chatMessage.userName">
      <div class="chat chat-end mx-2 my-1">
        <div class="chat-image avatar">
          <div class="w-10 rounded-full">
            <img
              alt="Tailwind CSS chat bubble component"
              src="https://daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg"
            />
          </div>
        </div>
        <div class="chat-header">{{ chatMessage.userName }}</div>
        <div class="chat-bubble">{{ chatMessage.message }}</div>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
