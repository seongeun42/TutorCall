<script setup lang="ts">
import type { tutorCallHistory } from '@/interface/mypage/interface';
import ReviewHistory from '@/pages/mypage/tutor/ReviewHistory.vue'
import type { Ref } from 'vue';
import { ref } from 'vue';
import TutorcallReview from '@/components/Review.vue'
import type { review } from '@/interface/common/interface'


const props = defineProps<{data:tutorCallHistory}>();
const myreview:Ref<review|null> = ref(props.data.review);
const open:Ref<boolean> = ref(false);

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
  myreview.value = value;
}

</script>
<template>
  <div class="mx-12">
    <p class="font-bold text-2xl mt-8">튜터콜 정보</p>
    <div class="flex my-5">
      <img :src="props.data.tutor.profile" alt="" class="w-24 h-24 rounded-full" />
      <div class="mx-5">
        <p>{{ props.data.tutor.nickname }}</p>
        <div class="flex mt-3">
          <div class="flex items-center">
          </div>
        </div>
      </div>
    </div>
    <div class="flex">
      <p class="font-bold text-lg mr-8">과외 기간</p>
      <p class="text-xl">{{ data.createAt }}</p>
    </div>
    <div class="flex">
      <p class="font-bold text-lg mr-8">회당 가격</p>
      <p class="text-xl">{{ data.price }} point</p>
    </div>
    <div class="mt-10 font-semibold text-xl mb-5">
      <p>문제</p>
    </div>
    <p>{{ data.problem }}</p>

    <div class="mt-10 font-semibold text-xl mb-5">
      <p>과외 리뷰</p>
    </div>
    <div class="review-box rounded-xl shadow-md">
      <div v-if="myreview">
        <ReviewHistory :data="myreview"/>
      </div>
        <div v-else>
            <p @click="openmodal($event)">아직 리뷰가 없네요! 작성해보러 갈까요?</p>
            <div v-if="open" class="modal-box fixed top-[10%] left-[35%] min-h-[30rem] z-10">
                <div class="flex justify-end" @click="handlemodal">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                  </svg>
                </div>
                <TutorcallReview @change="closemodal" @update="updateReview" mode="tutorcallList" :id="props.data.tutoringId"/>
            </div>
            <div v-if="open" class="modal-overlay  z-5"></div>
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
