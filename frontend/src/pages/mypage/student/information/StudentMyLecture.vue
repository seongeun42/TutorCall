<template>
  <div class="h-full w-full bg-blue-50">
    <div class="flex my-5 justify-center">
      <img src="@/img/teacher.png" alt="" class="w-24 h-24 rounded-full" />
      <div class="pt-2 mx-5">
        <p>{{ lectureInfo.tutorname }}</p>
        <p class="mt-2 font-bold text-xl">{{ lectureInfo.title }}</p>
        <div class="flex mt-2">
          <p class="bg-blue-500 mr-2 rounded-3xl w-16 text-white text-center">
            {{ lectureInfo.subject }}
          </p>
          <p class="bg-green-500 w-16 text-white rounded-3xl text-center">
            {{ lectureInfo.target }}
          </p>
        </div>
      </div>
    </div>
    <div
      @click="clickShow"
      class="flex justify-center cursor-pointer items-center mx-5 text-xl font-bold"
    >
      ▼
    </div>
    <div v-if="showDetail">
      <MyLectureDetail :Infos="lectureInfo.review" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, type Ref } from 'vue'
import MyLectureDetail from './MyLectureDetail.vue'

const showDetail: Ref<boolean> = ref(false)
const clickShow = function (): void {
  showDetail.value = !showDetail.value
}
interface Review {
  name: string
  img: string
  rates: number
  content: string
}
interface LectureInfo {
  title: string
  tutorname: string
  subject: string
  target: string
  img: string
  review: object
}
const reviews: Review = {
  name: '사브레26',
  img: '/src/img/student.jpg',
  rates: 4,
  content: '좋습니다. 좋구여'
}

const lectureInfo: LectureInfo = {
  title: '기하와 벡터 속성 강의',
  tutorname: '투블럭의 여집합',
  subject: '수학',
  target: '고3',
  img: 'src/img/teacher.png',
  review: reviews
}
</script>

<style scoped></style>
