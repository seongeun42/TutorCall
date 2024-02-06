<script setup lang="ts">
import MyPaymentData from '@/pages/mypage/payment/component/MyPaymentData.vue'

interface payInfo {
  [key: string]: string
}

const payInfoProp = defineProps<{ Info: payInfo[] }>()
</script>
<template>
  <!--4개를 한번에 정렬-->
  <div class="flex">
    <div class="flex-grow m-3" v-for="(data, index) in payInfoProp.Info" :key="index">
      <MyPaymentData :info="data" />
    </div>
  </div>
</template>
<style scoped></style>
