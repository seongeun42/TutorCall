<script setup lang="ts">
import { ref, type Ref, onMounted, watch } from 'vue'
import { instance } from '@/axios/axiosConfig'
import CkEditor from '@/pages/board/editor/CkEditor.vue'
import router from '@/router'
import { useEditStore } from '@/store/editStore'
import { useUserStore } from '@/store/userStore'
import { useNotificationStore } from '@/store/notificationStore'
import * as api from '@/api/qna/qna'
import { isAxiosError, type AxiosResponse } from 'axios'
import { type commonResponse, type errorResponse } from '@/interface/common/interface'

interface selectform {
  value: number
  name: string
}
let tag: number = 0
const school: selectform[] = [
  { value: 1, name: '초등학교' },
  { value: 31, name: '중학교' },
  { value: 46, name: '고등학교' }
]
let grade: selectform[] = []

const title: Ref<string> = ref('')
const editorData: Ref<string> = ref('')
const buttonClicked: Ref<string> = ref('')
const schoolSelected: Ref<selectform | string> = ref('')
const gradeSelected: Ref<selectform | string> = ref('')
const subjectSelected: Ref<selectform | string> = ref('')
const gradeDisabled: Ref<boolean> = ref(true)
const subjectDisabled: Ref<boolean> = ref(true)
const editStore = useEditStore()
const userStore = useUserStore()
const notificationStore = useNotificationStore()

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

watch(
  () => subjectSelected.value,
  () => {
    tag = Number(schoolSelected.value) + Number(gradeSelected.value) + Number(subjectSelected.value)
  }
)

const questionId: number = Number(router.currentRoute.value.params['qnaNum'])

onMounted(() => {
  if (editStore.needEdit) {
    title.value = editStore.title
    editorData.value = editStore.content
  }
})

function cancelWrite(): void {
  if (window.confirm('글 작성을 취소하시겠습니까?')) {
    router.push('/')
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
  const url: string = import.meta.env.VITE_VUE_API_URL

  const param = {
    questionTitle: title.value,
    questionContent: editorData.value,
    tagId: tag
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
      window.alert('문제 등록이 완료되었습니다.')
      router.push({ name: 'qnalist' })
    })
    .catch((error: any) => {
      console.log(error)
    })
}

function tutorcallRequest() {
  if (notificationStore.requestUuid != null) {
    console.log("한 번에 한 번의 요청만 가능")
    return
  }
  const uuid = crypto.randomUUID();
  // 문제 수락 응답 받을 sub 구독
  notificationStore.callSubscribe(uuid)
  const message = {
    id: uuid,
    title: title.value,
    content: editorData.value,
    tagId: tag,
    userId: userStore.id
  }
  // 문제 요청 보내기
  notificationStore.sendMessage(`tag/${message.tagId}`, message)
  window.alert('문제 등록이 완료되었습니다. 튜터콜 대기실로 이동합니다.')
  router.push({ name: 'waitingRoom', params: { userId: userStore.id } });
}
</script>
<template>
  <div class="my-10 mx-auto w-[1000px]">
    <div class="text-3xl font-bold">질문 작성</div>
    <div class="my-10">
      <div class="flex items-center">
        <input class="title" type="text" v-model="title" />
      </div>
      <div class="mt-10 mb-5 flex h-[35px]">
        <div class="flex items-center">
          <select
            class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
            v-model="schoolSelected"
          >
            <!--tag 부분 수정 필요-->
            <option value="" disabled>학교 선택</option>
            <option v-for="s in school" v-bind:value="s.value" :key="s.value">{{ s.name }}</option>

            <!-- 다른 과목들도 추가할 수 있습니다. -->
          </select>
          <select
            class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
            v-model="gradeSelected"
            :disabled="gradeDisabled"
          >
            <option value="" disabled>학년 선택</option>
            <option v-for="g in grade" v-bind:value="g.value" :key="g.value">{{ g.name }}</option>
          </select>
          <select
            class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
            v-model="subjectSelected"
            :disabled="subjectDisabled"
          >
            <option value="" disabled>과목 선택</option>
            <option value="0">국어</option>
            <option value="1">수학</option>
            <option value="2">사회</option>
            <option value="3">과학</option>
            <option value="4">영어</option>
          </select>
        </div>
      </div>
    </div>
    <div class="my-10">
      <CkEditor @update:modelValue="handleModelValueUpdate" />
    </div>

    <div class="my-20 flex justify-center items-center">
      <button
        @click="cancelWrite"
        type="button"
        class="mr-6 py-2.5 px-5 me-2 mb-2 text-xl font-medium text-gray-900 focus:outline-none bg-white rounded-xl border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        취소
      </button>
      <button
        @click="submitPost('qna', $event)"
        type="button"
        class="py-2.5 px-5 me-2 mb-2 text-xl font-medium text-white focus:outline-none bg-blue-900 rounded-xl border border-gray-300 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        Q&A 게시판
      </button>
      <button
        @click="tutorcallRequest"
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
