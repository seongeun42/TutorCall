<script setup lang="ts"></script>
<template>
  <div class="grid justify-items-stretch grid-cols-10 mt-8 mb-2">
    <div class="justify-self-start col-start-1 col-span-1">
      <button class="btn btn-square">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="w-6 h-6"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15m-3 0-3-3m0 0 3-3m-3 3H15"
          />
        </svg>
      </button>
    </div>
    <div class="justify-self-center col-start-3 col-span-6 font-semibold">
      투블럭의 여집합님의 강의실
    </div>
    <div class="justify-self-end col-start-9 col-span-2">
      <button
        type="button"
        class="py-2 px-4 bg-blue-600 hover:bg-blue-700 focus:ring-pink-500 focus:ring-offset-blue-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-full"
      >
        02:03:04
      </button>
    </div>
  </div>
</template>
<style scoped></style>
