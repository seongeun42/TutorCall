<template>
  <div class="bg-blue-50">
    <div class="flex p-20">
      <SideBar v-if="isTutor" />
      <StudentSideBar v-if="!isTutor" />
      <div class="p-4 pl-20">
        <p class="text-4xl font-bold mb-10">{{ title }}</p>
        <div class="container p-12 bg-white">
          <StudentInformationUpdate v-if="!isTutor" />
          <InformationUpdate v-if="isTutor" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import SideBar from '@/components/SideBar.vue'
import StudentSideBar from '@/components/StudentSideBar.vue'
import { ref, type Ref } from 'vue'
import StudentInformationUpdate from '@/pages/mypage/student/information/StudentInformationUpdate.vue'
import InformationUpdate from '@/pages/mypage/tutor/InformationUpdate.vue'

const title: Ref<String> = ref('개인정보 수정')
const isTutor: Ref<boolean> = ref(true)
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
