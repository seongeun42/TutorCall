<script setup lang="ts">
import type { review } from '@/interface/common/interface';
import StarScore from './StarScore.vue'
import { useUserStore } from '@/store/userStore';
import { onMounted } from 'vue';
import type { TutorReview } from '@/interface/mypage/interface';
import { ref, type Ref} from 'vue';

const userStore = useUserStore();
const props = defineProps<{data: any}>();
const score:number = Math.round((props.data.communicationRate+props.data.mannerRate+props.data.professionalismRate)/3)
function isReviewType(data: any): data is review{
  return data.review !== undefined;
}

</script>
<template>
  <div v-if="isReviewType(props.data)">
    <div class="flex items-center justify-between pt-2">
      <div class="flex items-center">
        <img :src="userStore.$state.profile" class="w-14 h-14 rounded-full ml-4" alt="" />
        <p class="ml-5">{{ userStore.$state.nickname }}</p>
        <div class="flex ml-40">
          <p class="mr-5">평점</p>
          <StarScore :score="score" />
        </div>
      </div>
      <div>{{ props.data.createAt }}</div>
    </div>
    <div class="flex pt-5 pb-5 pl-10">
      <div class="flex justify-center h-full">
        <p class="text-lg">
          {{ props.data.content }}
        </p>
      </div>
    </div>
  </div>
  <div v-else class="bg-orange-100 rounded-lg p-4 m-4">
    <div class="flex items-center justify-between pt-2">
      <div class="flex items-center">
        <img :src="props.data.reviewer.profile" class="w-14 h-14 rounded-full ml-4" alt="" />
        <p class="ml-5">{{ props.data.reviewer.nickname }}</p>
        <div class="flex ml-40">
          <p class="mr-5">평점</p>
          <StarScore :score="score" />
        </div>
      </div>
      <div>{{ props.data.createdAt.split(".")[0].replace("T", " ") }}</div>
    </div>
    <div class="flex pt-5 pb-5 pl-10">
      <div class="flex justify-center h-full">
        <p class="text-lg">
          {{ props.data.content }}
        </p>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>
<style scoped>
</style>
