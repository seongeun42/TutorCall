<script setup lang="ts">
import ChatFriend from '@/components/chatting/ChatFriend.vue'
import ChatSearchVue from './ChatSearch.vue'
import ChatTopMenu from './ChatTopMenu.vue'
</script>

<template>
  <div id="chatbox">
    <div id="friendslist">
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

<style scoped></style>
