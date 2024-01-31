<template>
  <div class="flex flex-wrap scroll-mx-2">
    <div class="w-1/2 p-2 flex items-center">
      <div class="w-full">
        <div class="mb-4">
          <div class="block text-2xl font-black text-left truncate">
            <p class="font-black text-sm text-[#001D6C]">ASK</p>
            <p class="bottom-2.5">풀리지 않는 문제를 질문해보세요</p>
            <br />
            <div class="text-sm font-normal bottom-2.5">
              <p>등록하는 문제에 대해 실시간 화상 문제 풀이 요청이 가능합니다.</p>
              <p>원하는 선생님을 선택할 수 있어요.</p>
            </div>
            <br />
            <div class="flex">
              <button
                type="button"
                class="inline-block rounded bg-[#001D6C] px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-[#006FA6] hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
                style="margin-right: 0.5rem; margin-left: 2rem"
              >
                실시간 강의 신청하기
              </button>
              <button
                type="button"
                class="inline-block rounded border-2 border-[#001D6C] px-6 pb-[6px] pt-2 text-xs font-medium uppercase leading-normal text-primary transition duration-150 ease-in-out hover:border-primary-600 hover:bg-neutral-500 hover:bg-opacity-10 hover:text-primary-600 focus:border-primary-600 focus:text-primary-600 focus:outline-none focus:ring-0 active:border-primary-700 active:text-primary-700 dark:hover:bg-neutral-100 dark:hover:bg-opacity-10"
                style="margin-left: 0.5rem"
                data-te-ripple-init
              >
                질문 게시판에 올리기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="w-1/2 block mb-8 bg-white">
      <img src="@\img\main2.png" class="w-full h-auto opacity-75" />
      <div style="text-align: center; background-color: white; margin-top: 10px">
        <div class="flex justify-center align-middle">
          <button
            type="button"
            class="inline-block rounded border-2 border-[#535354] px-6 pb-[6px] pt-2 text-xs font-medium uppercase leading-normal text-black transition duration-150 ease-in-out hover:border-primary-600 hover:bg-neutral-500 hover:bg-opacity-10 hover:text-primary-600 focus:border-primary-600 focus:text-primary-600 focus:outline-none focus:ring-0 active:border-primary-700 active:text-primary-700 dark:hover:bg-neutral-100 dark:hover:bg-opacity-10"
            style="margin-right: 1rem"
            data-te-ripple-init
          >
            문제 업로드
          </button>
          <button
            type="button"
            class="inline-block rounded bg-[#024E27] px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-[#1e844f] hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
            style="margin-left: 1rem"
          >
            저장
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped></style>
