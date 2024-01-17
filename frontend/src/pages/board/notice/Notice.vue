<template>
  <div class="container mx-auto my-10">
    <div class="mx-10 my-5">
      <div class="flex justify-center mt-8">
        <button
          type="button"
          class="rounded bg-blue-100 mr-6 px-6 py-3 pb-2 pt-2 text-2xl font-bold uppercase leading-normal text-black shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
        >
          공지사항
        </button>
        <button
          type="button"
          class="rounded bg-green-100 px-6 py-3 pb-2 pt-2 text-2xl font-bold uppercase leading-normal text-black shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
        >
          FAQ
        </button>
      </div>
      <div class="flex justify-end items-center mt-10">
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="selectedSubject"
        >
          <option value="">정렬</option>
          <option value="math">오름차순</option>
          <option value="science">내림차순</option>
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
      </div>
      <!-- <NoticeArticle /> -->
      <FAQArticle />
    </div>
  </div>
</template>

<script setup lang="ts">
import NoticeArticle from './NoticeArticle.vue'
import FAQArticle from './FAQArticle.vue'
</script>

<style scoped></style>
