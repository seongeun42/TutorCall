<script setup lang="ts">
import { defineProps } from 'vue'
import type { questionInfo } from '@/interface/qna/interface'

const props = defineProps<{ data: questionInfo }>()
</script>
<template>
  <div
    class="block rounded-lg bg-white shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700"
    style="width: 400px"
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
    <div class="p-6 text-center">
      <h5
        class="text-center mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50"
      >
        {{ props.data.title }}
      </h5>
      <p
        class="text-center mb-4 text-base text-neutral-600 dark:text-neutral-200"
        v-html="props.data.content"
      ></p>
    </div>
  </div>
</template>

<style></style>
