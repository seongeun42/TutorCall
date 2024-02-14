<script setup lang="ts">

import { onMounted } from 'vue';
import ReviewHistory from './ReviewHistory.vue'
import * as api from '@/api/mypage/mypage'
import { ref } from 'vue';
import type { GetTutorReviewResponse, TutorReview } from '@/interface/mypage/interface';
import { useUserStore } from '@/store/userStore';
import type { Ref } from 'vue';
import { isAxiosError, type AxiosResponse } from 'axios';
import type{ errorResponse } from '@/interface/common/interface';

const userStore = useUserStore();

const reviewData:Ref<TutorReview[]|null> = ref(null);
const pageNo:Ref<number> = ref(1);
const size:Ref<number> = ref(10);
const totalPages:Ref<number> = ref(10);

const prevPage = (): void => {
  if (pageNo.value > 1) {
    pageNo.value = pageNo.value-1;
    getReviews();
  }
}
const nextPage = (): void => {
  if (pageNo.value < totalPages.value) {
    pageNo.value = pageNo.value+1;
    getReviews();
  }
}

async function getReviews():Promise<void>{
  const param:string = `${userStore.$state.id}?page=${pageNo.value-1}&size=${size.value}`;
  
  await api.getTutorReview(param)
  .then((response: AxiosResponse<GetTutorReviewResponse>)=>{
    reviewData.value = response.data.content;
    totalPages.value = response.data.totalPages;
  })
  .catch((error:unknown)=>{
    if(isAxiosError<errorResponse>(error)) alert(error.response?.data.message);
  })
}

onMounted(async():Promise<void>=>{
  getReviews();
})

</script>
<template>
  <p class="font-bold text-2xl">학생 리뷰</p>
  <p class="border-2 my-10"></p>
  <div v-for="(review, index) in reviewData" :key="index">
    <ReviewHistory :data="review" mode="reviewCheck"/>
  </div>
  <div class="flex justify-center mt-8">
      <button
        type="button"
        class="px-4 py-2 bg-gray-400 hover:bg-gray-500 rounded-md text-white mr-2"
        @click="prevPage"
        :disabled="pageNo === 1"
      >
        이전
      </button>
      <span class="text-lg">{{ pageNo }}</span>
      <button
        type="button"
        class="px-4 py-2 bg-gray-400 hover:bg-gray-500 rounded-md text-white ml-2"
        @click="nextPage"
        :disabled="pageNo === totalPages"
      >
        다음
      </button>
    </div>
</template>
<style scoped>
.star {
  width: 30px;
  height: 30px;
}
</style>
