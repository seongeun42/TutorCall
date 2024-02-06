<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import { instance } from '@/axios/axiosConfig';
import CkEditor from '@/pages/board/editor/CkEditor.vue'
import router from '@/router';
import { useEditStore } from '@/store/editStore'
import { isAxiosError, type AxiosResponse } from 'axios';
import { type commonResponse, type errorResponse } from '@/interface/common/interface';

const title: Ref<string> = ref('')
const editorData: Ref<string> = ref('')
const buttonClicked: Ref<string> = ref('')
const selectedSubject: Ref<string> = ref('')
const selectedSchool: Ref<string> = ref('')
const selectedGrade: Ref<number> = ref(0)
const editStore = useEditStore();
const lectureId: number = Number(router.currentRoute.value.params['promotionNum'])

onMounted(()=>{
  if(editStore.needEdit){
    title.value = editStore.title;
    editorData.value = editStore.content;
  }
})

function selectSchool(school: string): void {
  if (selectedSchool.value === school) {
    selectedSchool.value = ''
  } else {
    selectedSchool.value = school
    console.log(selectedSchool.value)
  }
}
function selectGrade(grade: number): void {
  if (selectedGrade.value === grade) {
    selectedGrade.value = 0
  } else {
    selectedGrade.value = grade
    console.log(selectedGrade.value) // 선택된 학년 값 출력
  }
}
function selectSubject(subject: string): void {
  if (selectedSubject.value === subject) {
    selectedSubject.value = ''
  } else {
    selectedSubject.value = subject
    console.log(selectedSubject.value)
  }
}
function handleModelValueUpdate(newValue: string) {
  // 값 변경 추적 로직을 작성합니다.
  editorData.value = newValue
  console.log(editorData.value)
}

</script>
<template>
  <div class="mx-auto w-[1000px]">
    <div class="my-10">
      <div class="flex items-center">
        <input class="title" type="text" v-model="title" />
      </div>
      <div class="my-10 flex h-[35px]">
        <button
          @click="selectSchool('초')"
          :class="[
            'bg-red-300 text-white text-center text-xl font-bold hover:bg-red-700 mx-1 rounded-xl w-[80px]',
            { 'bg-red-700': selectedSchool === '초' }
          ]"
        >
          초
        </button>
        <button
          @click="selectSchool('중')"
          :class="[
            'bg-red-300 text-white text-center text-xl font-bold hover:bg-red-700 mx-1 rounded-xl w-[80px]',
            { 'bg-red-700': selectedSchool === '중' }
          ]"
        >
          중
        </button>
        <button
          @click="selectSchool('고')"
          :class="[
            'bg-red-300 text-white text-center text-xl font-bold hover:bg-red-700 mx-1 rounded-xl w-[80px]',
            { 'bg-red-700': selectedSchool === '고' }
          ]"
        >
          고
        </button>
      </div>
      <div class="my-10 flex h-[35px]">
        <button
          @click="selectGrade(1)"
          :class="[
            'bg-blue-300 text-white  text-center text-xl font-bold hover:bg-blue-700 mx-1 rounded-xl w-[80px]',
            { 'bg-blue-700 text-white': selectedGrade === 1 }
          ]"
        >
          1학년
        </button>
        <button
          @click="selectGrade(2)"
          :class="[
            'bg-blue-300 text-white text-center text-xl font-bold hover:bg-blue-700 mx-1 rounded-xl w-[80px]',
            { 'bg-blue-700': selectedGrade === 2 }
          ]"
        >
          2학년
        </button>
        <button
          @click="selectGrade(3)"
          :class="[
            'bg-blue-300 text-white text-center text-xl font-bold hover:bg-blue-700 mx-1 rounded-xl w-[80px]',
            { 'bg-blue-700': selectedGrade === 3 }
          ]"
        >
          3학년
        </button>
        <button
          @click="selectGrade(4)"
          :class="[
            'bg-blue-300 text-white text-center text-xl font-bold hover:bg-blue-700 mx-1 rounded-xl w-[80px]',
            { 'bg-blue-700': selectedGrade === 4 }
          ]"
        >
          4학년
        </button>
        <button
          @click="selectGrade(5)"
          :class="[
            'bg-blue-300 text-white text-center text-xl font-bold hover:bg-blue-700 mx-1 rounded-xl w-[80px]',
            { 'bg-blue-700': selectedGrade === 5 }
          ]"
        >
          5학년
        </button>
        <button
          @click="selectGrade(6)"
          :class="[
            'bg-blue-300 text-white text-center text-xl font-bold hover:bg-blue-700 mx-1 rounded-xl w-[80px]',
            { 'bg-blue-700': selectedGrade === 6 }
          ]"
        >
          6학년
        </button>
      </div>
      <div class="my-10 flex h-[35px]">
        <button
          :class="[
            'bg-green-300 text-white text-center text-xl font-bold hover:bg-green-700 mx-1 rounded-xl w-[80px]',
            { 'bg-green-700': selectedSubject === '국어' }
          ]"
          @click="selectSubject('국어')"
        >
          국어
        </button>
        <button
          :class="[
            'bg-green-300 text-white text-center text-xl font-bold hover:bg-green-500 mx-1 rounded-xl w-[80px]',
            { 'bg-green-700': selectedSubject === '영어' }
          ]"
          @click="selectSubject('영어')"
        >
          영어
        </button>
        <button
          :class="[
            'bg-green-300 text-white text-center text-xl font-bold hover:bg-green-800 mx-1 rounded-xl w-[80px]',
            { 'bg-green-700': selectedSubject === '수학' }
          ]"
          @click="selectSubject('수학')"
        >
          수학
        </button>
        <button
          :class="[
            'bg-green-300 text-white text-center text-xl font-bold hover:bg-green-800 mx-1 rounded-xl w-[80px]',
            { 'bg-green-700': selectedSubject === '과학' }
          ]"
          @click="selectSubject('과학')"
        >
          과학
        </button>
        <button
          :class="[
            'bg-green-300 text-white text-center text-xl font-bold hover:bg-green-800 mx-1 rounded-xl w-[80px]',
            { 'bg-green-700': selectedSubject === '사회' }
          ]"
          @click="selectSubject('사회')"
        >
          사회
        </button>
      </div>
    </div>
    <div class="my-10">
      <CkEditor @update:modelValue="handleModelValueUpdate"/>
    </div>

    <div class="my-20 flex justify-center items-center">
      <button
       
        type="button"
        class="py-2.5 px-5 me-2 mb-2 text-xl font-medium text-white focus:outline-none bg-blue-900 rounded-xl border border-gray-300 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        Q&A 게시판
      </button>
      <button
        type="button"
        class="py-2.5 px-5 me-2 mb-2 text-xl font-medium text-white bg-green-900 focus:outline-none rounded-xl border border-gray-300 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        튜터콜
      </button>
    </div>
  </div>
</template>

<style scoped>
.title {
  margin: 0 10px;
  border: 1px solid;
  border-color: rgb(192, 192, 192);
  border-radius: 8px;
  width: 100%;
  height: 40px;
}
</style>
