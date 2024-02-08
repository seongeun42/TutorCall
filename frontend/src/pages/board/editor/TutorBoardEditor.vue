<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import { instance } from '@/axios/axiosConfig'
import CkEditor from '@/pages/board/editor/CkEditor.vue'
import router from '@/router'
import { useEditStore } from '@/store/editStore'
import { isAxiosError, type AxiosResponse } from 'axios'
import { type commonResponse, type errorResponse } from '@/interface/common/interface'

const title: Ref<string> = ref('')
const editorData: Ref<string> = ref('')
const buttonClicked: Ref<string> = ref('')
const editStore = useEditStore()
const lectureId: number = Number(router.currentRoute.value.params['promotionNum'])
const maxPeople: Ref<number | null> = ref(null)
const lectureFee: Ref<number | null> = ref(null)
const deadline: Ref<string | null> = ref(null)
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
</script>
<template>
  <div class="mx-auto w-[1000px]">
    <div class="text-3xl font-bold">과외 모집</div>
    <div class="my-10">
      <div class="flex items-center">
        <input class="title" type="text" v-model="title" />
      </div>
    </div>
    <div class="flex">
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
        class="mr-6 py-2.5 px-5 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        취소
      </button>
      <button
        type="button"
        class="py-2.5 px-5 me-2 mb-2 text-sm font-medium text-white focus:outline-none bg-blue-600 rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
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
