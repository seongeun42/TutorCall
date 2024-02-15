<script setup lang="ts">
import { defineProps } from 'vue';

const props = defineProps<{
  profile: String,
  roomName: String,
}>()
</script>

<template>
  <div
    id="profile"
    class="overflow-hidden -mb-px mx-0 text-center text-black bg-white border-b-[#d8dfe3] border-b-2 border-solid flex flex-wrap"
  >
    <img class="avatar" :src="props.profile" />
    <div class="room-title">
      <p class="font-semibold text-[20px]">{{ props.roomName }}</p>
    </div>
  </div>
</template>

<style scoped>
#profile .avatar {
  @apply w-[68px] h-[68px] mb-0 mx-0 rounded-[50%] border-[3px] border-solid border-white;
}

.room-title {
  margin: auto;
}
</style>
