<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import { instance } from '@/axios/axiosConfig'
import CkEditor from '@/pages/board/editor/CkEditor.vue'
import router from '@/router'
import { useEditStore } from '@/store/editStore'
import * as api from '@/api/qna/qna'
import { isAxiosError, type AxiosResponse } from 'axios'
import { type commonResponse, type errorResponse } from '@/interface/common/interface'

const subjects: string[] = ['국어', '영어', '수학', '사회', '과학']
const grades: number[] = [1, 2, 3, 4, 5, 6]
const schools: string[] = ['초', '중', '고']
const title: Ref<string> = ref('')
const editorData: Ref<string> = ref('')
const buttonClicked: Ref<string> = ref('')
const selectedSubject: Ref<string> = ref('')
const selectedSchool: Ref<string> = ref('')
const selectedGrade: Ref<number> = ref(0)
const editStore = useEditStore()
const questionId: number = Number(router.currentRoute.value.params['qnaNum'])
onMounted(() => {
  if (editStore.needEdit) {
    title.value = editStore.title
    editorData.value = editStore.content
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
async function submitPost(buttonName: string, event: Event): Promise<void> {
  event.preventDefault()

  buttonClicked.value = buttonName
  const url: string = 'http://localhost:8080/'

  const param = {
    questionTitle: title.value,
    questionContent: editorData.value,
    tagId: 1
  }

  const endpoint: string = buttonClicked.value === 'qna' ? 'qna/question' : 'tutorcall/'

  if (editStore.needEdit) {
    await api
      .editQuestion(param, questionId)
      .then((response: AxiosResponse<commonResponse>) => {
        alert(response.data.message)
        router.push({ name: 'qnaDetail', params: { qnaNum: questionId } })
      })
      .catch((error: unknown) => {
        if (isAxiosError<errorResponse>(error)) alert(error.response?.data.message)
      })
    return
  }

  instance
    .post(url + endpoint, param)
    .then((response: any) => {
      if (buttonName === 'tutorcall') {
        // 대기실로 이동하는 라우터 구현해야 됨
        window.alert('문제 등록이 완료되었습니다. 튜터콜 대기실로 이동합니다.')
      } else {
        window.alert('문제 등록이 완료되었습니다.')
        router.push({ name: 'qnaList' })
      }
    })
    .catch((error: any) => {
      console.log(error)
    })
}
</script>
<template>
  <div class="mx-auto w-[1000px]">
    <div class="my-10">
      <div class="flex items-center">
        <input class="title" type="text" v-model="title" />
      </div>
      <div class="mt-10 mb-5 flex h-[35px]">
        <div v-for="school in schools" :key="school">
          <button
            @click="selectSchool(school)"
            :class="[
              'bg-red-300 text-white text-center text-xl font-bold hover:bg-red-700 mx-1 rounded-xl w-[80px]',
              { 'bg-red-700': selectedSchool === school }
            ]"
          >
            {{ school }}
          </button>
        </div>
      </div>
      <div class="flex h-[35px] mb-5">
        <div v-for="grade in grades" :key="grade">
          <button
            @click="selectGrade(grade)"
            :class="[
              'bg-blue-300 text-white  text-center text-xl font-bold hover:bg-blue-700 mx-1 rounded-xl w-[80px]',
              { 'bg-blue-700 text-white': selectedGrade === grade }
            ]"
          >
            {{ grade }}학년
          </button>
        </div>
      </div>
      <div class="flex h-[35px]">
        <div v-for="subject in subjects" :key="subject">
          <button
            :class="[
              'bg-green-300 text-white text-center text-xl font-bold hover:bg-green-700 mx-1 rounded-xl w-[80px]',
              { 'bg-green-700': selectedSubject === subject }
            ]"
            @click="selectSubject(subject)"
          >
            {{ subject }}
          </button>
        </div>
      </div>
    </div>
    <div class="my-10">
      <CkEditor @update:modelValue="handleModelValueUpdate" />
    </div>

    <div class="my-20 flex justify-center items-center">
      <button
        @click="submitPost('qna', $event)"
        type="button"
        class="py-2.5 px-5 me-2 mb-2 text-xl font-medium text-white focus:outline-none bg-blue-900 rounded-xl border border-gray-300 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        Q&A 게시판
      </button>
      <button
        @click="submitPost('tutorcall', $event)"
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
  text-align: center;
}
</style>
