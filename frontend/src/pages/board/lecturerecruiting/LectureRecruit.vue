<script setup lang="ts">
import TutorCard from './TutorCard.vue'
import { onMounted, ref, type Ref } from 'vue';
import * as api from '@/api/lectureBoard/lectureBoard'
import type { lecture, lectureResponse } from '@/interface/lectureBoard/interface';
import { isAxiosError, type AxiosResponse } from 'axios';
import type { errorResponse } from '@/interface/common/interface';

let selectedSubject: string = ''
let currentPage: number = 1
let tag:string = "";
const size = 10;
const totalPages: number = 10 // 전체 페이지 수 (원하는 값으로 변경)
const searchKeyword = ref("");
const lectureData:Ref<lecture[]> = ref([]); 

const prevPage = (): void => {
  if (currentPage > 1) {
    currentPage--
  }
}

const nextPage = (): void => {
  if (currentPage < totalPages) {
    currentPage++
  }
}

async function init():Promise<void>{

  const param:string = `page=${currentPage-1}&tag=${tag}&keyword=${searchKeyword.value}&state=true&size=${size}`

  await api.lectureList(param)
  .then((response: AxiosResponse<lectureResponse>)=>{
    lectureData.value = response.data.content;
  })
  .catch((error: unknown)=>{
    if(isAxiosError<errorResponse>(error)){
      alert(error.response?.data.message);
    }
  })
  
}

onMounted(()=>{
  init();
})
</script>
<template>
  <div class="container mx-auto">
    <h2 class="font-bold text-[1.7rem] mt-5 ml-20">과외 모집</h2>
    <div class="flex justify-end items-center space-x-4 my-10">
      <div class="flex items-center">
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="selectedSubject"
        >
          <option value="">분야</option>
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
    <TutorCard v-for="lecture in lectureData" :data="lecture"/>
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
/* 요소들을 감싸고 있는 div에 패딩 추가 */
.container {
  padding: 10px 100px;
}
</style>
