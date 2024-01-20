<template>
  <div class="bg-gray-100 w-full h-32 rounded-xl flex items-center justify-between">
    <div class="flex justify-center items-center">
      <p class="mx-20">나의 튜터콜 포인트</p>
      <p class="font-bold text-3xl">130,000P</p>
    </div>
    <div class="border border-gray-300 h-full"></div>
    <div class="flex justify-center items-center">
      <p>출금 가능 금액</p>
      <p class="mx-20 font-bold text-3xl">50,000원</p>
    </div>
  </div>

  <WithdrawlModal />
</template>

<script setup lang="ts">
import WithdrawlModal from './WithdrawlModal.vue'
</script>

<style scoped></style>
