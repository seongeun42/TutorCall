<template>
  <div v-if="faqData">
    <div v-for="data in faqData" :key="data.faqId" class="border border-gray-200 py-10 my-10 p-20">
      <div class="flex">
        <div class="">
          <p class="font-bold text-xl mr-20">{{ data.faqId }}</p>
        </div>
        <div class="flex-1">
          <div class="flex">
            <p
              class="rounded-lg bg-blue-800 text-white text-center white-space: nowrap;"
              style="width: 100px; height: 30px"
            >
              Q
            </p>
            <p class="font-bold text-xl cursor-pointer ml-10 flex-1" @click="toggleAnswer(data)">
              {{ data.question }}
            </p>
          </div>
          <div v-if="data.showAnswer" class="flex my-10">
            <p
              class="rounded-lg bg-red-600 text-white text-center"
              style="width: 100px; height: 30px"
            >
              A
            </p>
            <p class="font-bold text-xl ml-10 flex-1">
              {{ data.answer }}
            </p>
          </div>
        </div>
        <!-- <div class="ml-5">12.12.12</div> -->
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import * as api from '@/api/notice/notice'
import { ref, type Ref, onMounted } from 'vue'
import { type AxiosResponse } from 'axios'
import type { faqInfo, faqResponse, faqData } from '@/interface/notice/interface'

const toggleAnswer = (data: faqData): void => {
  // console.log(data)
  data.showAnswer = !data.showAnswer
  // console.log(data)
}

const faqData: Ref<faqData[]> = ref([])

function init(): void {
  api.getFaqData().then((response: AxiosResponse<faqResponse>) => {
    if (response.status == 200) {
      response.data.faqs.forEach((faq: faqInfo) => {
        faqData.value.push({ ...faq, showAnswer: false })
      })
      // console.log(faqData.value)
      // console.log(response.data)
      // faqData.value = response.data.faqs
      // faqData.value.showAnswer = false
      // console.log(faqData.value)
    }
  })
}

onMounted((): void => {
  init()
})
</script>

<style scoped></style>
