<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import StarScore from './tutor/StarScore.vue'
import type { lectureHistory } from '@/interface/mypage/interface';
import * as api from '@/api/lectureBoard/lectureBoard'
import type { detailLecture } from '@/interface/lectureBoard/interface';
import { isAxiosError, type AxiosResponse } from 'axios';
import { type errorResponse } from '@/interface/common/interface';
const point: Ref<number> = ref(1000)
const isTutor: Ref<boolean> = ref(true)
const props = defineProps<{data: lectureHistory}>();
const lectureData: Ref<detailLecture|null> = ref(null);

onMounted(async()=>{
  console.log(props.data);
  await api.oneLecture(props.data.lectureId)
  .then((response: AxiosResponse<detailLecture>)=>{
    lectureData.value = response.data;
  })
  .catch((error:unknown)=>{
    if(isAxiosError<errorResponse>(error)) alert(error.response?.data.message);
  })
})

</script>
<template>
  <div class="mx-12">
    <p class="font-bold text-2xl mt-8">과외 정보</p>
    <div class="flex my-5">
      <img :src="props.data.tutor.profile" alt="" class="w-24 h-24 rounded-full" />
      <div class="mx-5">
        <p>{{ props.data.tutor.nickname }}</p>
        <p class="font-bold text-xl">{{ props.data.promotionTitle }}</p>
        <div class="flex mt-3">
          <div class="flex items-center">
            <p class="bg-blue-500 mr-2 rounded-3xl w-16 text-white text-center">{{ props.data.tag.subject }}</p>
            <p class="bg-green-500 w-16 text-white rounded-3xl text-center">{{ props.data.tag.level }}</p>
          </div>
          <div v-if="isTutor">
            <button class="ml-32 bg-red-700 rounded-xl w-28 h-10 text-white">과외 시작하기</button>
          </div>
          <div v-else>
            <button class="ml-32 bg-blue-700 rounded-xl w-28 h-10 text-white">과외룸 입장</button>
          </div>
        </div>
      </div>
    </div>
    <div class="flex ml-8">
      <p class="font-bold text-lg mr-8">과외 기간</p>
      <p class="text-xl">{{ lectureData?.lectureStartAt }} ~ {{ lectureData?.lectureEndAt }}</p>
    </div>
    <div class="flex ml-8">
      <p class="font-bold text-lg mr-8">회당 가격</p>
      <p class="text-xl">{{ lectureData?.price }} point</p>
    </div>

    <div class="mt-10 font-semibold text-xl mb-5">
      <p>과외 리뷰</p>
    </div>
    <div class="review-box rounded-xl shadow-md">
      <div v-if="props.data.review" class="flex items-center mb-8">
        <!-- <img :src="reviews.Infos.img" alt="" class="w-12 h-12 rounded-full" />
        <p class="mx-2">{{ reviews.Infos.name }}</p>
        <p class="flex mx-4">평점</p>
        <StarScore :score="reviews.Infos.rates" />
        <p class="ml-6">{{ reviews.Infos.content }}</p> -->
      </div>
    </div>
  </div>
</template>
<style scoped>
.review-box {
  background-color: #faf6ef;
  min-width: 480px;
}
</style>
