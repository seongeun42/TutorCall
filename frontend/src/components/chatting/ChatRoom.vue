<script setup lang="ts">
import ChatProfile from '@/components/chatting/ChatProfile.vue'
import SendMessage from '@/components/chatting/SendMessage.vue'
import ChattingMessage from '@/components/chatting/ChattingMessage.vue'
</script>

<template>
  <div
    id="chatview"
    class="w-full h-full absolute left-0 top-0 overflow-hidden rounded-md shadow-lg"
  >
    <ChatProfile class="h-20" />
    <div
      id="chat-messages"
      class="w-full h-[348px] font-sans bg-white overflow-scroll no-scrollbar overflow-x-hidden pr-5"
    >
      <ChattingMessage />
      <ChattingMessage />
      <label class="text-[#aab8c2] font-semibold text-xs text-center w-full block mx-0 mb-5"
        >Thursday 02</label
      >
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
      <ChattingMessage />
    </div>
    <SendMessage class="h-14" />
  </div>
</template>

<style scoped>
#chat-messages {
  /* @apply overflow-scroll no-scrollbar overflow-x-hidden pr-5; */
}
</style>
