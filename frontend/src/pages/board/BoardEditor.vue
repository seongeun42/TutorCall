<template>
  <div class="mx-auto w-[1000px]">
    <div class="my-10">
      <div class="flex items-center">
        <input class="title" type="text" v-model="title" />
      </div>
      <div class="my-10 flex h-[35px]">
        <button
          class="bg-red-300 text-white text-center text-xl font-bold hover:bg-red-700 mx-1 rounded-xl w-[80px]"
        >
          국어
        </button>
        <button
          class="bg-yellow-300 text-white text-center text-xl font-bold hover:bg-yellow-500 mx-1 rounded-xl w-[80px]"
        >
          영어
        </button>
        <button
          class="bg-blue-300 text-white text-center text-xl font-bold hover:bg-blue-800 mx-1 rounded-xl w-[80px]"
        >
          수학
        </button>
        <button
          class="bg-purple-300 text-white text-center text-xl font-bold hover:bg-purple-800 mx-1 rounded-xl w-[80px]"
        >
          과학
        </button>
        <button
          class="bg-gray-300 text-white text-center text-xl font-bold hover:bg-gray-800 mx-1 rounded-xl w-[80px]"
        >
          사회
        </button>
      </div>
    </div>
    <div class="my-10">
      <!-- <CkEditor /> -->
      <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
    </div>

    <div class="my-20 flex justify-center items-center">
      <button
        @click="submitPost('qna')"
        type="button"
        class="py-2.5 px-5 me-2 mb-2 text-xl font-medium text-white focus:outline-none bg-blue-900 rounded-xl border border-gray-300 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        Q&A 게시판
      </button>
      <button
        @click="submitPost('tutorcall')"
        type="button"
        class="py-2.5 px-5 me-2 mb-2 text-xl font-medium text-white bg-green-900 focus:outline-none rounded-xl border border-gray-300 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
      >
        튜터콜
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import axios from 'axios'
import CkEditor from '@/pages/board/CkEditor.vue'
const title: Ref<string> = ref('')
const editor = ClassicEditor
const editorData: Ref<string> = ref('')
const editorConfig: Ref<any> = ref({})
const buttonClicked: Ref<string> = ref('')
const selctedSubject: Ref<string> = ref('')

function submitPost(buttonName: string): void {
  buttonClicked.value = buttonName
  const url: string = 'http://localhost:8080/'
  const endpoint: string = buttonClicked.value === 'qna' ? 'qna/' : 'tutorcall/'
  axios
    .post(url + endpoint, {
      title: title.value,
      editorData: editorData.value
    })
    .then((response: any) => {
      if (buttonName === 'tutorcall') {
        // 대기실로 이동하는 라우터 구현해야 됨
        window.alert('문제 등록이 완료되었습니다. 튜터콜 대기실로 이동합니다.')
      } else {
        window.alert('문제 등록이 완료되었습니다.')
      }
    })
    .catch((error: any) => {
      console.log(error)
    })
}
</script>

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

<style>
.ck-toolbar__items {
  align-items: center;
  justify-content: center;
}

.ck-editor__editable {
  min-height: 400px;
  max-height: 400px;
}
</style>
