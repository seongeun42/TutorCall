<template>
  <div class="bg-blue-50">
    <div class="flex p-20">
      <SideBar v-if="isTutor" />
      <StudentSideBar v-if="!isTutor" />
      <div class="p-4 pl-20">
        <p class="text-4xl font-bold mb-10">동적 제목으로 추가 예정</p>
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
// import router from '@/router'
import { ref, type Ref, onMounted, defineProps } from 'vue'
import { useRoute, useRouter } from 'vue-router'
// import StudentInformationUpdate from '@/pages/mypage/student/information/StudentInformationUpdate.vue'
// import InformationUpdate from '@/pages/mypage/tutor/InformationUpdate.vue'

// const route = useRoute();
const router = useRouter()
// const props = defineProps(['title'])
// const title: Ref<string> = ref(props.title)
const isTutor: Ref<boolean> = ref(true)

onMounted(async () => {
  await router.isReady()
  if (isTutor.value == true) {
    router.push({ name: 'tutorUpdate', state: { title: '개인정보 수정' } })
    console.log(history.state.title)
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
