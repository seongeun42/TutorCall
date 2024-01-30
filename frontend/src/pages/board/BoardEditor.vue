<template>
  <div class="px-80 py-20">
    <form class="border-2 px-6" @submit.prevent="submitPost">
      <div class="my-10">
        <div class="flex items-center">
          <h5 class="w-20">제목</h5>
          <input class="title" type="text" v-model="title" />
        </div>
        <div class="lines my-10"></div>
      </div>
      <div class="my-10">
        <div class="flex items-center">
          <h5 class="w-20">내용</h5>
          <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
        </div>
        <div class="lines my-10"></div>
      </div>
      <div class="flex items-center my-10">
        <h5 class="w-20">파일첨부</h5>

        <input
          class="block text-sm text-gray-900 border border-gray-300 rounded-md cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
          id="default_size"
          type="file"
        />
      </div>
      <div class="my-10 flex justify-center">
        <button
          type="button"
          class="py-2.5 px-5 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-xl border border-gray-300 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
        >
          취소
        </button>
        <button
          type="button"
          class="py-2.5 px-5 me-2 mb-2 text-sm font-medium text-white bg-green-700 focus:outline-none rounded-xl border border-gray-300 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
        >
          등록
        </button>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
const title: Ref<string> = ref('')

function submitPost(): void {
  // 등록된 글과 파일을 서버로 전송하는 로직을 작성하세요.
  // 서버로 전송 후에는 필요한 처리를 수행하고, 필요에 따라 화면을 업데이트하세요.
}
const editor = ClassicEditor
const editorData: Ref<string> = ref('')
const editorConfig: Ref<any> = ref({ language: 'ko' })
</script>

<style scoped>
.lines {
  border: solid 1px;
  border-color: rgb(231, 231, 231);
}
.title {
  margin: 0 10px;
  border: 1px solid;
  border-color: rgb(209, 209, 209);
  width: 80%;
}
</style>
