<template>
  <div class="container mx-auto">
    <h2 class="font-bold text-[1.7rem] ml-20 mt-5">문제 Q&A</h2>
    <div class="flex justify-between items-center space-x-4 my-10">
      <div class="menulist">
        <a href="" class="mr-5">전체</a>
        <a href="" class="mr-5">해결</a>
        <a href="" class="mr-5">미해결</a>
      </div>

      <div class="flex items-center">
        <select
          class="p-2 border border-gray-300 rounded-md mr-1 appearance-none"
          v-model="selectedSubject"
        >
          <option value="">과목 선택</option>
          <option value="math">수학</option>
          <option value="science">과학</option>
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

        <button type="button" class="px-4 py-2 bg-blue-700 hover:bg-blue-800 rounded-md text-white">
          글쓰기
        </button>
      </div>
    </div>

    <ProblemCard />
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

<script setup lang="ts">
import ProblemCard from './ProblemCard.vue'
let selectedSubject: string = ''
let currentPage: number = 1
const totalPages: number = 10 // 전체 페이지 수 (원하는 값으로 변경)

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
</script>

<style scoped>
.menulist {
  margin-left: 60px;
  color: black;
  font-size: 15px;
  font-family: Inter;
  font-weight: 600;
  word-wrap: break-word;
}

/* 요소들을 감싸고 있는 div에 패딩 추가 */
.container {
  padding: 10px 100px;
}
</style>
