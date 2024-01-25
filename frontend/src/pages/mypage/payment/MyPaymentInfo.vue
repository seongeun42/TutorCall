<script setup lang="ts">
import MyPayment from '@/pages/mypage/payment/component/MyPayment.vue'
import PaymentModal from '@/pages/mypage/payment/component/PaymentModal.vue'

interface payInfo {
  [key: string]: string
}

const datas: payInfo[] = []

const cardData: payInfo = {
  '카드 종류': 'mastercard',
  '카드 번호': '1234 5678 9123 4567',
  Month: '11',
  Year: '2024'
}

datas.push(cardData)
</script>
<template>
  <div class="border">
    <div class="flex justify-between bg-slate-100 items-center h-full py-2 px-2">
      <p class="font-bold">기본 결제 정보</p>
      <PaymentModal :datas="datas" mode="편집" btnName="수정" />
    </div>
    <MyPayment :Info="datas" />
  </div>
</template>
<style scoped></style>
