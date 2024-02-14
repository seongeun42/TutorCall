<script setup lang="ts">
import type { DetailLecture } from '@/interface/lectureBoard/interface';


const props = defineProps<{"data":DetailLecture|null}>();

</script>
<template>
  <div class="container mx-auto bg-gray-100 pb-10 rounded-xl">
    <div class="mx-10 pt-5">
      <p class="text-2xl mt-5">모집 내용</p>
      <p class="my-20 text-xl">
        {{ props.data?.promotionContent }}
      </p>
    </div>
  </div>
</template>
<style scoped></style>
