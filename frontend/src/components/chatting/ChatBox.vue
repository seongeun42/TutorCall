<script setup lang="ts">
import ChatFriend from '@/components/chatting/ChatFriend.vue'
import ChatSearchVue from './ChatSearch.vue'
import ChatTopMenu from './ChatTopMenu.vue'
import ChatRoom from './ChatRoom.vue'
</script>

<template>
  <div
    id="chatbox"
    class="w-full h-full overflow-hidden absolute rounded-md shadow-lg bg-white font-sans"
  >
    <div id="friendslist" class="absolute w-full h-full left-0 top-0">
      <ChatTopMenu class="h-20" />
      <div id="friends" class="h-[384px] overflow-scroll no-scrollbar">
        <ChatFriend />
      </div>
      <ChatSearchVue class="h-14" />
    </div>
  </div>
</template>

<style scoped></style>
