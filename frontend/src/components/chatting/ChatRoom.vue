<script setup lang="ts">
import ChatProfile from '@/components/chatting/ChatProfile.vue'
import SendMessage from '@/components/chatting/SendMessage.vue'
import ChattingMessage from '@/components/chatting/ChattingMessage.vue'
</script>

<template>
  <div
    id="chatbox"
    class="w-[290px] overflow-hidden h-[484px] absolute ml-[-155px] rounded-md left-2/4 top-[100px]"
  >
    <div
      id="chatview"
      class="p1 w-[290px] h-[484px] absolute left-0 top-0 overflow-hidden rounded-md"
    >
      <ChatProfile />
      <div id="chat-messages">
        <label
          class="text-[#aab8c2] font-semibold text-xs text-center w-[290px] block mx-0 my-[15px]"
          >Thursday 02</label
        >
        <ChattingMessage />
      </div>

      <SendMessage />
    </div>
  </div>
</template>

<style scoped>
#chat-messages {
  @apply opacity-0 w-[290px] h-[270px] overflow-y-scroll overflow-x-hidden transition-all duration-200 ease-[cubic-bezier(0.000,0.995,0.990,1.000)] mt-[30px] pr-5;
  -webkit-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -moz-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -ms-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -o-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
}
#chat-messages.animate {
  @apply opacity-100 mt-0;
}
</style>
