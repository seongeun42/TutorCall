<template>
  <div class="bg-blue-50">
    <div class="flex p-20">
      <SideBar />
      <div class="p-4 pl-20">
        <p class="text-4xl font-bold mb-10">{{ title }}</p>
        <div class="container p-12 bg-white">
          <!-- <MyLectureList /> -->
          <StudentMyLecture />
          <!-- <InformationUpdate /> -->
          <!-- <MyPaymentInfo /> -->
          <!-- <NewPaymentAdd /> -->
          <!-- <ReviewCheck /> -->
          <!-- <WithdrawlPage /> -->
          <!-- <ProfitCheck /> -->
          <!-- <StudentInformationUpdate /> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import SideBar from '@/components/SideBar.vue'
import MyPaymentInfo from '@/pages/mypage/payment/MyPaymentInfo.vue'
import NewPaymentAdd from '@/pages/mypage/payment/NewPaymentAdd.vue'
import { ref, type Ref } from 'vue'
import StudentInformationUpdate from './student/information/StudentInformationUpdate.vue'
import MyLectureList from './tutor/MyLectureList.vue'
import InformationUpdate from './tutor/InformationUpdate.vue'
import ReviewCheck from './tutor/ReviewCheck.vue'
import WithdrawlPage from './tutor/WithdrawlPage.vue'
import ProfitCheck from './tutor/ProfitCheck.vue'
import StudentMyLecture from './student/information/StudentMyLecture.vue'
const title: Ref<String> = ref('개인정보 수정')
</script>

<style scoped>
.container {
  margin: 0 auto;
  border-radius: 20px;
  position: relative;
  min-width: 1000px;
  min-height: 1000px;
}
</style>
