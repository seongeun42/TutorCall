<script setup lang="ts">
import ChatProfile from '@/components/chatting/ChatProfile.vue'
import SendMessage from '@/components/chatting/SendMessage.vue'
import ChattingMessage from '@/components/chatting/ChattingMessage.vue'
</script>

<template>
  <div id="chatbox">
    <div id="chatview" class="p1">
      <ChatProfile />
      <div id="chat-messages">
        <label>Thursday 02</label>
        <ChattingMessage />
      </div>

      <SendMessage />
    </div>
  </div>
</template>

<style scoped></style>
