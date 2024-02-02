<template>
  <div class="bg-blue-50">
    <div class="flex p-20">
      <SideBar v-if="isTutor" />
      <StudentSideBar v-if="!isTutor" />
      <div class="p-4 pl-20">
        <p class="text-4xl font-bold mb-10">{{ title }}</p>
        <div class="container p-12 bg-white">
          <RouterView />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import SideBar from '@/components/SideBar.vue'
import StudentSideBar from '@/components/StudentSideBar.vue'
import router from '@/router'
import { ref, type Ref, onMounted } from 'vue'
// import StudentInformationUpdate from '@/pages/mypage/student/information/StudentInformationUpdate.vue'
// import InformationUpdate from '@/pages/mypage/tutor/InformationUpdate.vue'

const title: Ref<string> = ref(history.state.title)
const isTutor: Ref<boolean> = ref(true)

onMounted(() => {
  if (isTutor.value == true) {
    router.push({ name: 'tutorUpdate' })
  } else if (isTutor.value == false) {
    router.push({ name: 'userUpdate' })
  }
})
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
