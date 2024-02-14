<script setup lang="ts">
import { ref, type Ref, onMounted, watch } from 'vue'
import { instance } from '@/axios/axiosConfig'
import CkEditor from '@/pages/board/editor/CkEditor.vue'
import router from '@/router'
import * as api from '@/api/lectureBoard/lectureBoard'
import { useEditStore } from '@/store/editStore'
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
const editStore = useEditStore()
const lectureId: number = Number(router.currentRoute.value.params['promotionNum'])
const maxPeople: Ref<number> = ref(0)
const lectureFee: Ref<number> = ref(0)
const deadline: Ref<string> = ref('')
const schoolSelected: Ref<selectform | string> = ref('')
const gradeSelected: Ref<selectform | string> = ref('')
const subjectSelected: Ref<selectform | string> = ref('')
const gradeDisabled: Ref<boolean> = ref(true)
const subjectDisabled: Ref<boolean> = ref(true)
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
  editorData.value = newValue
}

async function submitPost(event: Event): Promise<void> {
  event.preventDefault()
  const promotionDue = new Date(deadline.value)
  const url: string = import.meta.env.VITE_VUE_API_URL

  const param = {
    promotionTitle: title.value,
    promotionContent: editorData.value,
    maxParticipant: maxPeople.value,
    promotionDue: promotionDue,
    price: lectureFee.value,
    tagId: tag
  }

  const endpoint: string = 'lecture/promotion'

  if (editStore.needEdit) {
    await api
      .editPromotion(param, lectureId)
      .then((response: AxiosResponse<commonResponse>) => {
        alert(response.data.message)
        router.push({ name: 'lectureDetail', params: { promotionNum: lectureId } })
      })
      .catch((error: unknown) => {
        if (isAxiosError<errorResponse>(error)) alert(error.response?.data.message)
      })
    return
  }

  instance
    .post(url + endpoint, param)
    .then((response: any) => {
      window.alert('문제 등록이 완료되었습니다. 튜터콜 대기실로 이동합니다.')
    })
    .catch((error: any) => {
      console.log(error)
    })
}
</script>
<template>
  <div class="my-10 mx-auto w-[1000px]">
    <div class="text-3xl font-bold">과외 모집 홍보</div>
    <div class="my-10">
      <div class="flex items-center">
        <input class="title" type="text" v-model="title" />
      </div>
    </div>
    <div class="flex">
      <div class="flex items-center">
        <select
          class="p-2 border border-gray-300 rounded-md mr-3 appearance-none"
          v-model="schoolSelected"
        >
          <!--tag 부분 수정 필요-->
          <option value="" disabled>학교 선택</option>
          <option v-for="s in school" v-bind:value="s.value" :key="s.value">{{ s.name }}</option>

          <!-- 다른 과목들도 추가할 수 있습니다. -->
        </select>
        <select
          class="p-2 border border-gray-300 rounded-md mr-3 appearance-none"
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
    <div class="flex mt-10">
      <div class="flex items-center mr-10">
        <div>최대 모집 인원</div>
        <input class="info-input" type="text" v-model="maxPeople" />
        <div>명</div>
      </div>
      <div class="flex items-center mr-10">
        <div>회당 가격</div>
        <input class="info-input" type="text" v-model="lectureFee" />
        <div>원</div>
      </div>
      <div class="flex items-center">
        <div>모집 마감 날짜</div>
        <input class="deadline" type="date" v-model="deadline" />
      </div>
    </div>
    <div class="my-10">
      <CkEditor @update:modelValue="handleModelValueUpdate" />
    </div>

    <div class="my-20 flex justify-center items-center">
      <button
        @click="cancelWrite"
        type="button"
        class="mr-6 py-2.5 px-5 me-2 mb-2 text-md font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        취소
      </button>
      <button
        @click="submitPost($event)"
        type="button"
        class="py-2.5 px-5 me-2 mb-2 text-md font-medium text-white focus:outline-none bg-blue-600 rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        등록
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

.info-input {
  margin: 0 10px;
  border: 1px solid;
  border-color: rgb(192, 192, 192);
  border-radius: 5px;
  width: 100px;
  height: 25px;
  text-align: center;
}

.deadline {
  margin: 0 10px;
  border: 1px solid;
  border-color: rgb(192, 192, 192);
  border-radius: 5px;
  width: 150px;
  height: 25px;
  text-align: center;
}
</style>
