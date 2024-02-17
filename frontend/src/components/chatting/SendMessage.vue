<script setup lang="ts">
import { defineProps } from 'vue';
import { useChattingStore } from '@/store/chatStore';

const props = defineProps<{
  roomId: String,
  senderId: number,
}>()

const chattingStore = useChattingStore();

function sendMessage(event: Event) {
  const message = event.target.value;
  event.target.value = '';
  chattingStore.sendMessage("chat/new/" + props.roomId, {
    "senderId": props.senderId,
    "message": message,
  });
}
</script>

<template>
  <div
    id="sendmessage"
    class="absolute w-full border-t-[#e7ebee] border-t border-solid right-0 bottom-0 bg-[#ffffff] pb-50px"
  >
    <input
      type="text"
      class="text-sm font-[400px] text-[#aab8c2] ml-[21px] mr-0 mt-[21px] mb-0 p-0 border-[none] bg-[#ffffff] font-sans focus:outline-0"
      placeholder="Send message..."
      @keyup.enter="sendMessage"
    />
    <button
      id="send"
      class="w-[30px] h-[30px] absolute border-[none] right-[15px] top-[23px] hover:cursor-pointer hover:bg-[0_0] bg-[#ffffff] bg-[url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/send.png')] bg-no-repeat focus:outline-0"
    ></button>
  </div>
</template>

<style scoped></style>
