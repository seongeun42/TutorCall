<script setup lang="ts">
import { useUserStore } from '@/store/userStore'
import { useVideoStore } from '@/store/videoStore'
const videoStore = useVideoStore()
const userStore = useUserStore()
</script>
<template>
  <div class="grid grid-cols-8 mx-3 my-2">
    <div class="col-span-1 justify-self-center">
      <div class="w-full h-full">
        <img src="@/img/pika.jpg" alt="" class="object-cover rounded-full border" />
      </div>
    </div>
    <div class="col-span-5 justify-self-start text-center">
      <p class="flex justify-center items-center h-full mx-2 font-semibold">
        {{ userStore.nickname }}
      </p>
    </div>
    <div class="col-span-1 flex items-center justify-center" v-if="!videoStore.micActive">
      <img src="@/img/nomike.jpg" alt="" />
    </div>
  </div>
</template>
<style scoped></style>
