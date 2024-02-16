<script setup lang="ts">
import { defineProps, ref, computed } from 'vue';
import { useChattingStore } from '@/store/chatStore';
import { useUserStore } from '@/store/userStore';

import ChatProfile from '@/components/chatting/ChatProfile.vue'
import SendMessage from '@/components/chatting/SendMessage.vue'
import ChattingMessage from '@/components/chatting/ChattingMessage.vue'
import ChattingMessageRight from './ChattingMessageRight.vue';

const props = defineProps<{
  roomInfo?: Object
}>()

// 채팅방 참여자들의 정보
const participants = ref([] as Object[]);

// 대표 참여자 - 현재 로그인한 사용자 제외
const representer = computed(() => {
  if(participants.value[0]) {
    return participants.value[0].id == userStore.id? participants.value[1]: participants.value[0];
  }
  return Object
})

const otherName = computed(() => {
  if(participants.value[0]) {
    
    return other.nickname;
  }
  return other.nickname + " 외 " + participants.value.length + "명";
})

// 대화 상대 - 현재 로그인한 사용자 제외
const other = computed(() => {
  if(participants.value[0]) {
    return participants.value[0].id == userStore.id? participants.value[1]: participants.value[0];
  }
  return Object
})

// 자신
const currentUser = computed(() => {
  if(participants.value[0]) {
    for(const p in participants.value) {
      if(p.id == userStore.id) {
        return p;
      }
    }
  }
  return Object
})

// 상대 메시지의 프로필
const otherProfile = (chat) => {
  if(participants.value[0]) {
    for(const p in participants.value) {
      if(p.id == chat.senderId) {
        return p.profile;
      }
    }
  }

  return "@/img/default_profile.png"
}

const chattingStore = useChattingStore();
const userStore = useUserStore();

// 채팅방 참여자들의 id 값을 가져옴
chattingStore.getParticipants(props.roomInfo.id, participants);
chattingStore.sendMessage("chatroom/users/" + props.roomInfo.id, {}, null);

// 초기 메시지들의 목록을 가져와야 함.
chattingStore.getAllChatsInRoom(props.roomInfo.id); // 백엔드 손봐야
chattingStore.sendMessage("chat/" + props.roomInfo.id, {}, null);

// 새 메시지가 추가될 때 마다 받아와야 함
chattingStore.getNewMessage(props.roomInfo.id);
</script>

<template>
    <div
      id="chatview"
      class="w-full h-full absolute left-0 top-0 overflow-hidden rounded-md shadow-lg"
    >
      <ChatProfile :profile="representer.profile" :room-name="props.roomInfo.name" class="h-20" />
      <div id="chat-messages" class="w-full h-[350px] font-sans bg-white overflow-scroll no-scrollbar overflow-x-hidden pr-5">
        <div v-for="chat in chattingStore.chatMessages">
          <ChattingMessageRight v-if="chat.senderId == userStore.id" :profile="currentUser.profile" :message="chat.message"/>
          <ChattingMessage v-else :profile="otherProfile(chat)" :message="chat.message" />
        </div>
      </div>
      <SendMessage :room-id="props.roomInfo.id" :sender-id="userStore.id" class="h-14" />
    </div>
</template>

<style scoped>
</style>
