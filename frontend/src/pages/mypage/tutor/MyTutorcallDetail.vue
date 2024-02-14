<script setup lang="ts">
import type { tutorCallHistory } from '@/interface/mypage/interface';
import ReviewHistory from '@/pages/mypage/tutor/ReviewHistory.vue'
import type { Ref } from 'vue';
import { ref } from 'vue';
import type { review } from '@/interface/common/interface'


const props = defineProps<{data:tutorCallHistory}>();
const myreview:Ref<review|null> = ref(props.data.review);


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
      <p class="text-xl">{{ data.createAt.split("T")[0] }}</p>
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
      <p>나의 과외 리뷰</p>
    </div>
    <div class="review-box rounded-xl shadow-md">
      <div v-if="myreview">
        <ReviewHistory :data="myreview"/>
      </div>
        <div v-else>
          <div class="flex flex-row justify-around">
            <p class="flex items-center font-semibold">아직 리뷰가 없습니다.</p>
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
reviewreview