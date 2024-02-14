<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import type { lectureHistory } from '@/interface/mypage/interface';
import * as api from '@/api/lectureBoard/lectureBoard'
import type { detailLecture } from '@/interface/lectureBoard/interface';
import { isAxiosError, type AxiosResponse } from 'axios';
import { type errorResponse, type review } from '@/interface/common/interface';
import TutorcallReview from '@/components/Review.vue'


const point: Ref<number> = ref(1000)
const isTutor: Ref<boolean> = ref(true)
const props = defineProps<{data: lectureHistory}>();
const lectureData: Ref<detailLecture|null> = ref(null);
const open:Ref<boolean> = ref(false);
const schoolname:Ref<string> = ref('');

function openmodal(event: Event):void{
  event.preventDefault();
  open.value = !open.value;
}
function handlemodal(event:Event):void{
  event.preventDefault();
  open.value = !open.value;
}

function closemodal():void{
  open.value = !open.value;
}

function updateReview(value:review):void{
  open.value =! open.value
}

onMounted(async()=>{
  console.log(props.data);
  await api.oneLecture(props.data.lectureId)
  .then((response: AxiosResponse<detailLecture>)=>{
    lectureData.value = response.data;
    switch(lectureData.value.tag.level){
      case "ELEMENTARY":
          schoolname.value = "초등학교";
          break;
        case "MIDDLE":
          schoolname.value = "중학교";
          break;
        case "HIGH":
          schoolname.value = "고등학교";
          break;
    }
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
        <div class="flex flex-row justify-around">
            <p class="flex items-center font-semibold">아직 리뷰가 없네요! 작성해보러 갈까요?</p>
            <div class="rounded-lg bg-teal-300 m-4 p-4" @click="openmodal($event)">button!</div>
          </div>
            <div v-if="open" class="modal-box fixed top-[10%] left-[35%] min-h-[30rem] z-10">
                <div class="flex justify-end" @click="handlemodal">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                  </svg>
                </div>
                <TutorcallReview @change="closemodal" @update="updateReview" mode="lecture" :id="props.data.lectureId"/>
            </div>
            <div v-if="open" class="modal-overlay  z-5"></div>
        </div>
      <div v-else>
        <div class="flex flex-row justify-around">
              <p class="flex items-center font-semibold p-4 m-4">리뷰를 작성한 강의입니다.</p>
            </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.review-box {
  background-color: #faf6ef;
  min-width: 480px;
}
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 배경을 투명한 검정색으로 설정 */
}

</style>
