<script setup lang="ts">
import FloatObject from '@/pages/tutorcall/FloatObject.vue'
import MatchText from '@/pages/tutorcall/MatchText.vue'
import router from '@/router'
import { defineProps } from 'vue'
import { useNotificationStore } from '@/store/notificationStore'
interface data {
  id: number
  delay: number
  size: number
  objectsize: number
  positionX: number
  positionY: number
  data: any
}
const notificationStore = useNotificationStore()
const props = defineProps<{
  pushedData: data
}>()

const mainContent = document.querySelector('#mainComponent')
const mainWidth = mainContent?.clientWidth ?? 1960
const mainHeight = mainContent?.clientHeight ?? 1000

const input = {
  id: 1,
  delay: 0,
  size: 300,
  objectsize: 300,
  positionX: mainHeight / 2,
  positionY: mainWidth / 2,
  data: null
}

const goLecture = () => {
  console.log(notificationStore.roomSessionId)
  const sessionId = notificationStore.roomSessionId?.replace('tutorCall', '')
  router.push(`/onlinelecture/${sessionId}`)
}
// const props= defineProps<{'userId':number}>();
</script>

<template>
  <div class="container">
    <div class="content">
      <div class="image-container">
        <img src="https://via.placeholder.com/300x300" alt="Test Image" />
      </div>
      <div>
        <MatchText />
      </div>
      <button class="go-lecture" @click="goLecture">강의실 이동</button>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 100vh;
}

.content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.image-container {
  width: 300px;
  height: 300px;
  border-radius: 50%;
  overflow: hidden;
  margin-top: 20px;
}

.go-lecture {
  background-color: #023e53;
  color: white;
  border-radius: 5px;
  width: 100px;
  height: 40px;
}
</style>
