<script setup lang="ts">
import type { detailTutor, registResponse } from '@/interface/lectureBoard/interface';
import router from '@/router/index'
import {type Ref, ref, onMounted} from 'vue';
import * as api from '@/api/lectureBoard/lectureBoard'
import { isAxiosError, type AxiosResponse } from 'axios';
import type { commonResponse, errorResponse } from '@/interface/common/interface';


const props = defineProps<{"data":detailTutor}>();

const promotionId:number = Number(router.currentRoute.value.params.promotionNum);

const means:string = (((props.data?.communicationRate ?? 0) + (props.data?.mannerRate ?? 0) + (props.data?.reliablity ?? 0)) / 3).toFixed(3);

const state:Ref<boolean> = ref(false);

async function registLecture(event: Event)
:Promise<void>{
  event.preventDefault();

  await api.registLecture(promotionId)
  .then((response: AxiosResponse<registResponse>)=>{
    alert(response.data.message);
    state.value = true;
  })
  .catch((error:unknown)=>{
    if(isAxiosError<errorResponse>(error)){
      alert(error.response?.data.message);
    }
  })
}

async function cancleRegistLecture(event: Event)
:Promise<void>{
  event.preventDefault();

  await api.cancleRegistLecture(promotionId)
  .then((response: AxiosResponse<commonResponse>)=>{
    alert(response.data.message);
  })
  .catch((error:unknown)=>{
    if(isAxiosError<errorResponse>(error)){
      alert(error.response?.data.message);
    }
  })
}
</script>
<template>
  <div class="container mx-auto bg-blue-100 pb-10 rounded-xl">
    <div class="ml-10 pt-5">
      <p>선생님 정보</p>
      <div class="flex">
        <img
          :src="props.data?.profile"
          alt=""
          class="mx-10 rounded-full"
          style="width: 300px; height: 300px"
        />
        <div class="container max-w-52 mt-10 mx-20">
          <h5 class="text-2xl font-bold mb-3">{{ props.data?.nickname}}</h5>
          <div class="flex justify-between">
            <p>평점 평균</p>
            <div class="flex ml-5">
              <p>{{  means }}</p>
            </div>
          </div>
          <div class="pt-6">
            <p class="text-sm">항목별 평점</p>
            <hr />
            <div class="flex justify-between">
              <p>전문성</p>
              <p>{{ props.data?.professionalismRate }}</p>
            </div>
            <div class="flex justify-between">
              <p>강의 매너</p>
              <p>{{ props.data?.mannerRate }}</p>
            </div>
            <div class="flex justify-between">
              <p>내용 전달력</p>
              <p>{{ props.data?.communicationRate }}</p>
            </div>
          </div>
        </div>
        <div class="container">
          <div class="mt-10 mx-10">
            <p class="text-2xl font-bold">소개글</p>
            <h5 class="mt-12 font-bold text-4xl">{{ props.data?.introduction }}</h5>
          </div>
        </div>
      </div>
    </div>
    <div class="flex justify-end">
      <button v-if ="state" class="mr-10 text-white bg-blue-900 w-32 h-10 rounded-xl font-bold"
      @click="cancleRegistLecture">취소하기</button>
      <button v-else class="mr-10 text-white bg-blue-900 w-32 h-10 rounded-xl font-bold"
      @click="registLecture">신청하기</button>
    </div>
  </div>
</template>
<style scoped></style>
