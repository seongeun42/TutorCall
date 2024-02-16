<script setup lang="ts">
import router from '@/router'
import lectureReview from '@/components/Review.vue'
import { onMounted } from 'vue'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { useNotificationStore } from '@/store/notificationStore'

const mode: Ref<string> = ref('')
const id: Ref<number> = ref(0)

function afterReview(): void {
  router.push('/')
}

onMounted(() => {
  const sessionId = useNotificationStore().$state.roomSessionId

  if (sessionId?.includes('lecture')) {
    mode.value = 'onlinelecture'
    id.value = Number(sessionId.replace('lecture', ''))
  } else {
    mode.value = 'tutorcall'
    id.value = Number(sessionId?.replace('tutorCall', ''))
  }
})
</script>
<template>
  <div>
    <div class="review-box rounded-xl shadow-md">
      <div class="modal-box fixed top-[10%] left-[35%] min-h-[30rem] z-10">
        <lectureReview :mode="mode" @update="afterReview" @change="afterReview" :id="id" />
      </div>
      <div class="modal-overlay z-5"></div>
    </div>
  </div>
</template>
<style scoped>
.review-box {
  background-color: #faf6ef;
  min-width: 480px;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 배경을 투명한 검정색으로 설정 */
}
</style>
