<script setup lang="ts">
import { ref, type Ref } from 'vue'
import CkEditor from './CkEditor.vue'
import router from '@/router'
const title: Ref<string> = ref('')
const editorData: Ref<string> = ref('')

function handleModelValueUpdate(newValue: string): void {
  editorData.value = newValue
  console.log(editorData.value)
}

function cancelWrite(): void {
  if (window.confirm('글 작성을 취소하시겠습니까?')) {
    router.push('/')
  }
}
</script>
<template>
  <div class="mx-auto w-[1000px]">
    <div class="my-10 font-bold text-2xl">관리자 문의</div>
    <div class="my-10">
      <div class="flex items-center">
        <input class="title" type="text" v-model="title" />
      </div>
    </div>
    <CkEditor @update:modelValue="handleModelValueUpdate" />
    <div class="my-10 flex justify-center items-center">
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
</style>
