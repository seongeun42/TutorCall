<script setup lang="ts">
import { ref } from 'vue';

const emits = defineEmits(["toggle-room-type"]);

const leftColor = ref("#3b82f6");
const rightColor = ref("#d1d5db");
const roomType = ref("PERSONAL");

const selectLeft = (e) => {
  leftColor.value = "#3b82f6";
  rightColor.value = "#d1d5db";
  roomType.value =  "PERSONAL";
  
  emits('toggle-room-type', roomType.value);
}

const selectRight = (e) => {
  leftColor.value = "#d1d5db";
  rightColor.value = "#3b82f6";
  roomType.value =  "GROUP";
  
  emits('toggle-room-type', roomType.value);
}
</script>

<template>
  <div class="h-20 w-72">
    <span
      class="-mb-px hover:cursor-pointer grid justify-items-stretch content-center border-b-[#d8dfe3] border-b-2 border-solid"
      @click="selectLeft"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="2"
        :stroke="leftColor"
        class="w-8 h-8 justify-self-center"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z"
        />
      </svg>
    </span>
    <span
      class="-mb-px hover:cursor-pointer grid justify-items-stretch content-center border-b-[#d8dfe3] border-b-2 border-solid"
      @click="selectRight"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="2"
        :stroke="rightColor"
        class="w-8 h-8 justify-self-center"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M15 19.128a9.38 9.38 0 0 0 2.625.372 9.337 9.337 0 0 0 4.121-.952 4.125 4.125 0 0 0-7.533-2.493M15 19.128v-.003c0-1.113-.285-2.16-.786-3.07M15 19.128v.106A12.318 12.318 0 0 1 8.624 21c-2.331 0-4.512-.645-6.374-1.766l-.001-.109a6.375 6.375 0 0 1 11.964-3.07M12 6.375a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0Zm8.25 2.25a2.625 2.625 0 1 1-5.25 0 2.625 2.625 0 0 1 5.25 0Z"
        />
      </svg>
    </span>
  </div>
</template>

<style scoped>
span {
  @apply float-left w-1/2 h-full hover:border-b-blue-500;
}
</style>
