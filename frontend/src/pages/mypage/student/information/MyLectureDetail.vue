<template>
  <div class="mx-12">
    <p class="font-bold text-2xl mt-8">과외 정보</p>
    <div class="flex my-5">
      <img :src="reviews.Infos.img" alt="" class="w-24 h-24 rounded-full" />
      <div class="mx-5">
        <p>투블럭의 여집합</p>
        <p class="font-bold text-xl">기하와 벡터 속성 강의</p>
        <div class="flex mt-3">
          <div class="flex items-center">
            <p class="bg-blue-500 mr-2 rounded-3xl w-16 text-white text-center">수학</p>
            <p class="bg-green-500 w-16 text-white rounded-3xl text-center">고3</p>
          </div>
          <button class="ml-32 bg-blue-700 rounded-xl w-28 h-10 text-white">과외룸 입장</button>
        </div>
      </div>
    </div>
    <div class="flex ml-8">
      <p class="font-bold text-lg mr-8">과외 기간</p>
      <p class="text-xl">{{ startDate }} ~ {{ endDate }}</p>
    </div>
    <div class="flex ml-8">
      <p class="font-bold text-lg mr-8">회당 가격</p>
      <p class="text-xl">{{ point }} point</p>
    </div>

    <div class="mt-10 font-semibold text-xl mb-5">
      <p>과외 리뷰</p>
    </div>
    <div class="review-box rounded-xl shadow-md">
      <div v-for="index in 4" :key="index" class="flex items-center mb-8">
        <img :src="reviews.Infos.img" alt="" class="w-12 h-12 rounded-full" />
        <p class="mx-2">{{ reviews.Infos.name }}</p>
        <p class="flex mx-4">평점</p>
        <StarScore :score="reviews.Infos.rates" />
        <p class="ml-6">{{ reviews.Infos.content }}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, type Ref } from 'vue'
import StarScore from '../../tutor/StarScore.vue'
const startDate: Ref<string> = ref('2024.01.10')
const endDate: Ref<string> = ref('2024.01.14')
const point: Ref<number> = ref(1000)

const reviews = defineProps({
  Infos: {
    type: Object,
    default: () => {
      return {}
    }
  }
})

// interface Review {
//   name: string
//   img: string
//   rates: number
//   content: string
// }

// const reviews: Review = {
//   name: '사브레26',
//   img: '/src/img/student.jpg',
//   rates: 4,
//   content: '좋습니다. 좋구여'
// }
</script>

<style scoped>
.review-box {
  background-color: #faf6ef;
}
</style>
