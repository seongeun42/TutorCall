<script setup lang="ts">
import { defineProps } from 'vue';

const props = defineProps<{
  profile: String,
  roomName: String,
  otherName: String,
}>()
</script>

<template>
  <div
    id="profile"
    class="overflow-hidden -mb-px mx-0 text-center text-black bg-white border-b-[#d8dfe3] border-b-2 border-solid flex flex-wrap"
  >
    <img class="avatar" :src="props.profile" />
    <div>
      <p class="font-semibold text-[15px]">{{ props.roomName }}</p>
      <span class="font-normal text-[11px]">{{ props.otherName }}</span>
    </div>
  </div>
</template>

<style scoped>
#profile .avatar {
  @apply w-[68px] h-[68px] mb-0 mx-0 rounded-[50%] border-[3px] border-solid border-white;
}
</style>
