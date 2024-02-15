<script setup lang="ts">
import { defineProps, ref, computed } from 'vue';
import { useChattingStore } from '@/store/chatStore';
import { useUserStore } from '@/store/userStore';

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

const chattingStore = useChattingStore();
const userStore = useUserStore();

// 채팅방 참여자들의 id 값을 가져옴
chattingStore.getParticipants(props.roomInfo.id, participants);
chattingStore.sendMessage("chatroom/users/" + props.roomInfo.id, {}, null);
</script>

<template v-if="participants[0]">
  <div
    class="h-[70px] relative border-b-[#e7ebee] border-b border-solid hover:cursor-pointer hover:bg-[#f1f4f6]"
  >
    <div>
      <img
        :src='representer.profile'
        class="w-10 float-left m-[15px] rounded-[50%]"
      />
    </div>
    <div>
      <div class="float-left w-[220px] pt-[15px] pb-0 px-0">
        <div>
          <strong class="font-semibold text-[15px] text-[#597a96]">{{ props.roomInfo.name }}</strong>
        </div>
        <div class="text-[13px] font-normal text-[#aab8c2]">
          <div v-if="props.roomInfo.chatroomType == 'GROUP'">
            {{representer.nickname}} 외 {{participants.length - 1}} 명
          </div>
          <div v-else>
            {{representer.nickname}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>
