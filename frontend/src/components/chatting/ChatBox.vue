<script setup lang="ts">
import ChatFriend from '@/components/chatting/ChatFriend.vue'
import ChatSearchVue from './ChatSearch.vue'
import ChatTopMenu from './ChatTopMenu.vue'
</script>

<template>
  <div
    id="chatbox"
    class="w-[290px] overflow-hidden h-[484px] absolute ml-[-155px] rounded-md left-2/4 top-[100px]; background: #fff;"
  >
    <div id="friendslist" class="absolute w-[290px] h-[484px] left-0 top-0;">
      <ChatTopMenu />
      <div id="friends">
        <ChatFriend />
        <ChatFriend />
        <ChatFriend />
        <ChatFriend />
        <ChatSearchVue />
      </div>
    </div>
  </div>
</template>

<style scoped>
* {
  @apply m-0 p-0;
}
</style>
