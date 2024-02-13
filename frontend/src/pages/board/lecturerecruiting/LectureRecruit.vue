<script setup lang="ts">
import TutorCard from './TutorCard.vue'
import { onMounted, ref, watch, type Ref } from 'vue';
import * as api from '@/api/lectureBoard/lectureBoard'
import type { lecture, lectureResponse } from '@/interface/lectureBoard/interface';
import { isAxiosError, type AxiosResponse } from 'axios';
import type { errorResponse } from '@/interface/common/interface';
import router from '@/router';

interface selectform {
  value: number
  name: string
}

const schoolSelected: Ref<selectform | string> = ref('')
const gradeSelected: Ref<selectform | string> = ref('')
const subjectSelected: Ref<selectform | string> = ref('')
const gradeDisabled: Ref<boolean> = ref(true)
const subjectDisabled: Ref<boolean> = ref(true)
let tag: number = 0;
let currentPage: number = 1
const size = 10;
const totalPages: number = 10 // 전체 페이지 수 (원하는 값으로 변경)
const searchKeyword = ref("");
const lectureData:Ref<lecture[]> = ref([]); 
const keyword: Ref<string> = ref('')

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

function gowrite():void{
  router.push({"name":"writelecture"});
}

onMounted(()=>{
  init();
})

const school: selectform[] = [
  { value: 1, name: '초등학교' },
  { value: 31, name: '중학교' },
  { value: 46, name: '고등학교' }
]

let grade: selectform[] = []

watch(
  () => schoolSelected.value,
  (oldValue) => {
    if (Number(oldValue) == 1) {
      grade = []
      for (let i = 0; i < 6; i++) grade.push({ value: i * 5, name: `${i + 1}학년` })
      gradeDisabled.value = false
      gradeSelected.value = ''
      subjectDisabled.value = true
      subjectSelected.value = ''
    } else if (Number(oldValue) == 31 || Number(oldValue) == 46) {
      grade = []
      for (let i = 0; i < 3; i++) grade.push({ value: i * 5, name: `${i + 1}학년` })
      gradeDisabled.value = false
      gradeSelected.value = ''
      subjectDisabled.value = true
      subjectSelected.value = ''
    }
  }
)

watch(
  () => gradeSelected.value,
  (newValue, oldValue) => {
    if (Number(newValue) >= 0) {
      subjectDisabled.value = false
    }
  }
)

async function keywordSearch(event: Event): Promise<void> {
  event.preventDefault()
  if (!subjectSelected.value) {
    alert('검색 조건을 다시 설정해주세요!')
    return
  }

  if(typeof(schoolSelected.value)==="number" && typeof(gradeSelected.value)==="number" && 
  typeof(subjectSelected.value)==="string"){
    tag = schoolSelected.value+gradeSelected.value+Number(subjectSelected.value);
  }

  init()
}

</script>
<template>
  <div class="container mx-auto">
    <h2 class="font-bold text-[1.7rem] mt-5 ml-20">과외 모집</h2>
    <div class="flex justify-end items-center space-x-4 my-10">
      <div class="flex items-center">
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="schoolSelected"
        >
          <!--tag 부분 수정 필요-->
          <option value="" disabled selected>학교 선택</option>
          <option v-for="(s, index) in school" :key="index" v-bind:value="s.value">{{ s.name }}</option>

          <!-- 다른 과목들도 추가할 수 있습니다. -->
        </select>
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="gradeSelected"
          :disabled="gradeDisabled"
        >
          <option value="" disabled selected>학년 선택</option>
          <option v-for="(g, index) in grade" :key="index" v-bind:value="g.value">{{ g.name }}</option>
        </select>
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="subjectSelected"
          :disabled="subjectDisabled"
        >
          <option value="" disabled selected>과목 선택</option>
          <option value=0>국어</option>
          <option value=1>수학</option>
          <option value=2>사회</option>
          <option value=3>과학</option>
          <option value=4>영어</option>

        </select>
        <input
          type="text"
          placeholder="search for keywords"
          class="w-60 p-2 border border-gray-300 rounded-md mr-1"
          v-model="keyword"
        />
        <button
          type="button"
          class="px-4 py-2 bg-gray-400 hover:bg-gray-500 rounded-md text-white mr-3"
          @click="keywordSearch"
        >
          검색
        </button>

        <button type="button"
        class="px-4 py-2 bg-blue-700 hover:bg-blue-800 rounded-md text-white"
        @click="gowrite">
          글쓰기
        </button>
      </div>
    </div>
    <TutorCard v-for="(lecture, index) in lectureData" :key="index" :data="lecture"/>
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
