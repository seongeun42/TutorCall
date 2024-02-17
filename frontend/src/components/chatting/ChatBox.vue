<script setup lang="ts">
import ChatFriend from '@/components/chatting/ChatFriend.vue'
import ChatSearchVue from './ChatSearch.vue'
import ChatTopMenu from './ChatTopMenu.vue'
import { useChattingStore } from '@/store/chatStore';
import { useUserStore } from '@/store/userStore'
import ChatRoom from './ChatRoom.vue'
import { ref } from 'vue'
import type {Ref} from 'vue'

const chattingStore = useChattingStore();
const userStore = useUserStore();

chattingStore.sendMessage("chatroom/" + userStore.id + "/" + chattingStore.roomType, {}, null)

const show: Ref<boolean> = ref(false);
// 클릭한 대화방의 방 정보
const selectedRoomInfo: Ref<Object> = ref(Object);

const onClick = (r: Object) => {
  show.value = !show.value;
  selectedRoomInfo.value = r;
}

const toggleRoomType = (m: String) => {
  chattingStore.roomType = m;
  chattingStore.sendMessage("chatroom/" + userStore.id + "/" + chattingStore.roomType, {}, null)
}
</script>

<template>
  <div v-if="show">
    <ChatRoom :roomInfo="selectedRoomInfo"/>
  </div>
  <div
    id="chatbox"
    class="w-full h-full overflow-hidden absolute rounded-md shadow-lg bg-white font-sans"
    v-else
  >
    <div id="friendslist" class="absolute w-full h-full left-0 top-0">
      <ChatTopMenu @toggle-room-type="toggleRoomType" class="h-20" />
      <div id="friends" class="h-[354px] overflow-scroll no-scrollbar"> 
        <div v-for="r in chattingStore.chatroomList">
          <ChatFriend :roomInfo="r" @click="onClick(r)"/>
        </div>
      </div>
      <ChatSearchVue class="h-14" />
    </div>
  </div>
</template>

<style scoped></style>
