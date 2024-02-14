<script setup lang="ts">
import type { lecture } from '@/interface/lectureBoard/interface';
import router from '@/router/index';

const props = defineProps<{"data":lecture}>();

function showDetail(event:Event):void{
  event.preventDefault();
  router.push({"name":'lectureDetail', params:{'promotionNum':props.data.id}});
}
</script>
<template>
  <!-- TW Elements is free under AGPL, with commercial license required for specific uses. See more details: https://tw-elements.com/license/ and contact us for queries at tailwind@mdbootstrap.com -->
  <div class="flex flex-wrap justify-around">
    <div class="mb-4">
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
        <div class="p-6 mb-10" @click="showDetail($event)">
          <div class="flex mb-5 gap-2">
            <div class="bg-blue-300 p-2 rounded-full aspect-w-2 aspect-h-1">
              <p class="text-white">{{ props.data.tag.level }}</p>
            </div>
            <div class="bg-green-300 p-2 rounded-full aspect-w-2 aspect-h-1">
              <p class="text-white">{{ props.data.tag.grade }}학년</p>
            </div>
            <div class="bg-blue-300 p-2 rounded-full aspect-w-2 aspect-h-1">
              <p class="text-white">{{ props.data.tag.subject }}</p>
            </div>
          </div>
          <h5 class="mb-2 text-xl font-bold leading-tight text-neutral-800 dark:text-neutral-50">
            {{ props.data.title }}
          </h5>
          <p class="mb-4 text-base text-neutral-600 dark:text-neutral-200">
            {{ props.data.content }}
          </p>
          <div class="flex justify-between items-center">
            <div class="flex items-center mb-5 mr-5">
              <img :src="props.data.tutor.profile" alt="" class="w-10 h-10 rounded-full" />
              <p>{{ props.data.tutor.nickname }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style></style>
