<template>
  <!-- TW Elements is free under AGPL, with commercial license required for specific uses. See more details: https://tw-elements.com/license/ and contact us for queries at tailwind@mdbootstrap.com -->
  <div class="flex flex-wrap justify-around">
    <div v-for="index in 9" :key="index" class="mb-4">
      <div
        class="w-250 h-200 block rounded-lg bg-white shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700"
      >
        <div
          class="relative overflow-hidden bg-cover bg-no-repeat"
          data-te-ripple-init
          data-te-ripple-color="light"
        >
          <a href="#!">
            <div
              class="absolute bottom-0 left-0 right-0 top-0 h-full w-full overflow-hidden bg-[hsla(0,0%,98%,0.15)] bg-fixed opacity-0 transition duration-300 ease-in-out hover:opacity-100"
            ></div>
          </a>
        </div>
        <div class="p-6 mb-10">
          <div class="flex mb-5">
            <div class="bg-blue-300 p-2 rounded-full aspect-w-2 aspect-h-1 mr-2">
              <p class="text-white">수학</p>
            </div>
            <div class="bg-green-300 p-2 rounded-full aspect-w-2 aspect-h-1">
              <p class="text-white">2/7</p>
            </div>
          </div>
          <h5 class="mb-2 text-xl font-bold leading-tight text-neutral-800 dark:text-neutral-50">
            제자 모집
          </h5>
          <p class="mb-4 text-base text-neutral-600 dark:text-neutral-200">
            수능 수학은 발로도 풉니다. 저만 믿고 따라오세요!
          </p>
          <div class="flex justify-between items-center">
            <div class="flex items-center mb-5">
              <img
                src="https://starteacher-v5-prod.s3.ap-northeast-2.amazonaws.com/tutor/profilebb06b015c44930414511bebd24f825af68f7d189947e79a5c9b1be0b27d1bb86"
                alt=""
                class="w-10 h-10 rounded-full"
              />
              <p>한석원</p>
              <p class="ml-3 text-xs text-gray-500">3시간전</p>
            </div>
            <button
              type="button"
              class="text-white mb-3 inline-block rounded bg-blue-900 px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
              data-te-ripple-init
              data-te-ripple-color="light"
            >
              과외 신청
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style></style>
