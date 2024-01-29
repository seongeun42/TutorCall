<template>
  <div class="container mx-auto my-10">
    <div class="mx-20 my-10">
      <div class="flex justify-between items-center">
        <div class="flex items-center justify-center rounded-full w-20 h-8 bg-green-200">
          <p>과목</p>
        </div>
        <div class="flex">
          <a href="" class="mr-3">글 삭제</a>
          <a href="">글 수정</a>
        </div>
      </div>
      <h5 class="font-bold text-[1.5rem] my-3">수능수학 30번 질문합니다.</h5>
      <div class="flex justify-between items-center">
        <div class="flex items-center mb-5">
          <img src="@/img/student.jpg" alt="" class="w-10 h-10 rounded-full" />
          <p class="ml-1">김잼민</p>
          <p class="ml-3">3시간 전</p>
        </div>
      </div>
      <hr />
      <DetailProblem />
      <div class="bg-gray-200 pb-10 rounded-xl">
        <p class="mx-10 my-10 pt-5 font-bold text-xl">댓글</p>
        <Comments />
      </div>
      <div class="mt-20 flex justify-center">
        <img src="@/img/teacher.png" alt="" class="w-20 h-20 rounded-full mr-10" />
        <input
          type="text"
          placeholder="풀이를 입력해주세요"
          class="border-2 border-gray-300 mr-10 rounded-md"
          style="width: 800px; height: 80px"
        />
        <button
          type="button"
          class="inline-block rounded bg-blue-800 mt-4 px-6 py-3 pb-2 pt-2.5 text-md uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
          style="width: calc(2 * 3rem); height: 3rem"
        >
          작성
        </button>
      </div>
    </div>
    <div class="mt-10 mx-40 flex justify-center items-center">
      <button
        type="button"
        class="items-center rounded bg-primary mt-4 px-6 py-3 pb-2 pt-2.5 text-2xl font-bold uppercase leading-normal text-black shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
        style="width: calc(3 * 3rem); height: 3rem"
      >
        목록
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import Comments from './Comments.vue'
import DetailProblem from './DetailProblem.vue'
</script>

<style scoped></style>
