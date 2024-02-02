<script setup lang="ts">
import ProblemCard from './ProblemCard.vue'
import * as api from '@/api/qna/qna'
import {ref, onMounted } from 'vue';
import type {Ref} from 'vue';
import router from '@/router/index'
import{ type AxiosResponse, type AxiosError, isAxiosError } from 'axios';
import type{ questionInfo, questionResponse, errorResponse } from '@/interface/qna/interface'

let selectedSubject: string = ''
let currentPage: number = 1
const size = 10;
let totalPages: number = 10 // 전체 페이지 수 (원하는 값으로 변경)
let tag:string = "";
let keyword:string = "";
let status:string = "";

const questionData:Ref<questionInfo[]> = ref([]);
const originData:Ref<questionInfo[]>=ref([]);

const prevPage = (): void => {
  if (currentPage > 1) {
    currentPage--;
    init();
  }
}
const nextPage = (): void => {
  if (currentPage < totalPages) {
    currentPage++;
    init();
  }
}

async function init():Promise<void>{

  const param:string = `?page=${currentPage-1}&size=${size}&isEnd=${status}&keyword=${keyword}&tagId=${tag}`
  console.log(param);
  await api.getQnAData(param)
  .then((response: AxiosResponse<questionResponse>)=>{
    console.log(response);
    if(response.status == 200){
      totalPages = response.data.questions.totalPages;
      questionData.value = response.data.questions.content;
      originData.value = questionData.value;
    }
  })
  .catch((error: unknown) =>{
    if(isAxiosError<errorResponse>(error)){
      alert(error.response?.data.message);
    }
  })
}

function goQnADetail(id:number):void{
  router.push({"name":"qnaDetail", "params":{"qnaNum":id}});
}

onMounted(async():Promise<void> =>{
  await init();
})

function questionFilter(isEnd: boolean, event:Event):void{

    event.preventDefault();
    status = isEnd.toString();
    currentPage = 1;
    init();
  
}

function reset(event:Event):void{
  event.preventDefault();
  status = "";
  keyword = "";
  tag = "";
  currentPage = 1;
  init();
}




</script>

<template>
  <div class="container mx-auto">
    <h2 class="font-bold text-[1.7rem] ml-20 mt-5">문제 Q&A</h2>
    <div class="flex justify-between items-center space-x-4 my-10">
      <div class="menulist">
        <a href="" class="mr-5"
        @click="init">전체</a>
        <a href="" class="mr-5"
        @click="questionFilter(true, $event)">해결</a>
        <a href="" class="mr-5"
        @click="questionFilter(false, $event)">미해결</a>
      </div>

      <div class="flex items-center">
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="selectedSubject"
        >
        <!--tag 부분 수정 필요-->
          <option value="" disabled>학교 선택</option>
          <option value="math">초등학교</option>
          <option value="science">중학교</option>
          <option value="science">고등학교</option>

          <!-- 다른 과목들도 추가할 수 있습니다. -->
        </select>
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="selectedSubject"
        >
          <option value="" disabled>학년 선택</option>
          <option value="math">수학</option>
          <option value="science">과학</option>
          <!-- 다른 과목들도 추가할 수 있습니다. -->
        </select>
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="selectedSubject"
        >
          <option value="">과목 선택</option>
          <option value="math">수학</option>
          <option value="science">과학</option>
          <!-- 다른 과목들도 추가할 수 있습니다. -->
        </select>

        <input
          type="text"
          placeholder="search for keywords"
          class="w-60 p-2 border border-gray-300 rounded-md mr-1"
        />
        <button
          type="button"
          class="px-4 py-2 bg-gray-400 hover:bg-gray-500 rounded-md text-white mr-3"
        >
          검색
        </button>

        <button type="button" class="px-4 py-2 bg-blue-700 hover:bg-blue-800 rounded-md text-white">
          글쓰기
        </button>
      </div>
    </div>

    <div class="grid grid-cols-3 gap-3">
      <ProblemCard v-for="data in questionData" :data="data"
      class="mb-10"
      @click="goQnADetail(data.questionId)"
      /> 
    </div>
    <div class="flex justify-center mt-8">
      <button
        type="button"
        class="px-4 py-2 bg-gray-400 hover:bg-gray-500 rounded-md text-white mr-2"
        @click="prevPage"
        :disabled="currentPage === 1"
      >
        이전
      </button>
      <span class="text-lg">{{ currentPage }}</span>
      <button
        type="button"
        class="px-4 py-2 bg-gray-400 hover:bg-gray-500 rounded-md text-white ml-2"
        @click="nextPage"
        :disabled="currentPage === totalPages"
      >
        다음
      </button>
    </div>
  </div>
</template>

<style scoped>
.menulist {
  margin-left: 60px;
  color: black;
  font-size: 15px;
  font-family: Inter;
  font-weight: 600;
  word-wrap: break-word;
}

/* 요소들을 감싸고 있는 div에 패딩 추가 */
.container {
  padding: 10px 100px;
}
</style>
