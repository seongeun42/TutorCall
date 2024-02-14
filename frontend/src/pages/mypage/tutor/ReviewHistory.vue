<script setup lang="ts">
import type { Review } from '@/interface/common/interface';
import StarScore from './StarScore.vue'
import { useUserStore } from '@/store/userStore';
import { onMounted } from 'vue';

const props = defineProps<{data: Review}>();
const score:number = Math.round((props.data.communicationRate+props.data.mannerRate+props.data.professionalismRate)/3)

</script>
<template>
  <div class="flex items-center justify-between pt-2">
    <div class="flex items-center">
      <img :src="useUserStore().profile" class="w-14 h-14 rounded-full ml-4" alt="" />
      <p class="ml-5">{{ useUserStore().nickname }}</p>
      <div class="flex ml-40">
        <p class="mr-5">평점</p>
        <StarScore :score="score" />
      </div>
    </div>
    <div>{{ data.createAt }}</div>
  </div>
  <div class="flex pt-5 pb-5 pl-10">
    <div class="flex justify-center h-full">
      <p class="text-lg">
        {{ data.content }}
      </p>
    </div>
  </div>
  <!-- </div> -->
</template>
<style scoped>
</style>
