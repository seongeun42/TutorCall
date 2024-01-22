<template>
  <div class="flex">
    <SideBar />
    <div class="flex-1 bg-blue-100 p-4">
      <p class="text-4xl font-bold my-10 mx-32">{{ title }}</p>
      <div class="container p-40 bg-white">
        <!-- <MyLectureList /> -->
        <MyPaymentInfo/>
        <NewPaymentAdd/>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import SideBar from '@/components/SideBar.vue'
import MyLectureList from './MyLectureList.vue'
import MyPaymentInfo from '@/pages/mypage/payment/MyPaymentInfo.vue'
import NewPaymentAdd from '@/pages/mypage/payment/NewPaymentAdd.vue'
import type { Ref } from 'vue'
import { ref } from 'vue'

const title: Ref<String> = ref('결제 정보')
</script>

<style scoped>
.container {
  margin: 0 auto;
}
</style>
