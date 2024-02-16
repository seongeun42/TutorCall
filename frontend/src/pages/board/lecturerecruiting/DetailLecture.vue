<script setup lang="ts">
import Information from './details/Information.vue'
import Content from './details/Content.vue'
import router from '@/router/index'
import { onMounted, type Ref, ref } from 'vue'
import * as api from '@/api/lectureBoard/lectureBoard'
import type { deleteResponse, detailLecture } from '@/interface/lectureBoard/interface'
import { isAxiosError, type AxiosResponse } from 'axios'
import type { errorResponse } from '@/interface/common/interface'
import { useEditStore } from '@/store/editStore'
import { useUserStore } from '@/store/userStore'

const lectureData: Ref<detailLecture | null> = ref(null)
const promotionId: number = Number(router.currentRoute.value.params.promotionNum)
const startDate: Ref<string> = ref('')
const endDate: Ref<string> = ref('')
const schoolname: Ref<string> = ref('')
const editStore = useEditStore()

async function deletePromotion(event: Event): Promise<void> {
  event.preventDefault()
  await api
    .deletePromotion(promotionId)
    .then((response: AxiosResponse<deleteResponse>) => {
      alert(response.data.message)
      router.push({ name: 'lectureList' })
    })
    .catch((error: unknown) => {
      if (isAxiosError<errorResponse>(error)) {
        alert(error.response?.data.message)
      }
    })
}

onMounted(async () => {
  await api
    .oneLecture(promotionId)
    .then((response: AxiosResponse<detailLecture>) => {
      lectureData.value = response.data
      switch (lectureData.value.tag.level) {
        case 'ELEMENTARY':
          schoolname.value = '초등학교'
          break
        case 'MIDDLE':
          schoolname.value = '중학교'
          break
        case 'HIGH':
          schoolname.value = '고등학교'
          break
      }
      startDate.value = lectureData.value.promotionCreatedAt.split('.')[0].replace('T', ' ')
      endDate.value = lectureData.value.promotionDue.split('.')[0].replace('T', ' ')
    })
    .catch((error: unknown) => {
      if (isAxiosError<errorResponse>(error)) {
        alert(error.response?.data.message)
      }
    })
})

function editLecture(event: Event): void {
  event.preventDefault()
  if (lectureData.value) {
    editStore.init()
    if (lectureData.value.tutor.id != Number(useUserStore().$state.id)) {
      alert('수정 권한이 없습니다!')
      return
    }

    let level: number = 0
    switch (lectureData.value.tag.level) {
      case 'ELEMENTARY':
        level = 1
        break
      case 'MIDDLE':
        level = 31
        break
      case 'HIGH':
        level = 46
        break
    }

    let tagsubject: number = 0
    switch (lectureData.value.tag.subject) {
      case '국어':
        tagsubject = 0
        break
      case '수학':
        tagsubject = 1
        break
      case '사회':
        tagsubject = 2
        break
      case '과학':
        tagsubject = 3
        break
      case '영어':
        tagsubject = 4
        break
    }

    const grade: number = (lectureData.value.tag.grade - 1) * 5

    editStore.save(
      level,
      grade,
      tagsubject,
      lectureData.value.promotionTitle,
      lectureData.value.promotionContent ?? '',
      true
    )
    router.push({ name: 'editlecture', params: { promotionNum: lectureData.value.id } })
  }
}
</script>
<template>
  <div class="container mx-auto my-10">
    <div class="mx-10">
      <div class="flex mb-5">
        <div class="bg-blue-400 p-2 rounded-xl w-20 h-10 mr-4 text-cenetr">
          <p class="text-white font-bold">{{ schoolname }}</p>
        </div>
        <div class="bg-green-400 p-2 rounded-xl w-20 h-10 mr-4 text-center">
          <p class="text-white font-bold">{{ lectureData?.tag.grade }}학년</p>
        </div>
        <div class="bg-yellow-300 p-2 rounded-xl w-20 h-10 mr-4 text-center">
          <p class="text-white font-bold">{{ lectureData?.tag.subject }}</p>
        </div>
      </div>
      <h5 class="font-bold text-3xl mt-8 mb-6">{{ lectureData?.promotionTitle }}</h5>
      <div class="flex justify-between">
        <div class="flex justify-between items-center">
          <div class="flex items-center mb-5">
            <img :src="lectureData?.tutor.profile" alt="" class="w-5 h-5 rounded-full" />
            <p class="ml-1">{{ lectureData?.tutor.nickname }}</p>
            <p class="ml-1">{{ startDate }} ~ {{ endDate }}</p>
          </div>
        </div>
        <div class="flex">
          <p class="mr-5" @click="deletePromotion">삭제</p>
          <p @click="editLecture">수정</p>
        </div>
      </div>
      <div class="flex flex-col items-center justify-center">
        <Information
          v-if="lectureData"
          :data="lectureData?.tutor"
          :isParticipated="lectureData.participated"
        />
        <Content :data="lectureData" class="my-20" />
        <button
          type="button"
          class="rounded bg-primary mt-4 px-6 py-3 pb-2 pt-2 text-2xl font-bold uppercase leading-normal text-black shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
          style="width: calc(3 * 3rem); height: 3rem"
        >
          목록
        </button>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
