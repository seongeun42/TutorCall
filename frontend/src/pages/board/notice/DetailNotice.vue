<template>
  <div v-if="noticeDetailData">
    <div class="mx-20 py-20">
      <div class="flex justify-between">
        <h5 class="font-bold text-3xl ml-10">{{ noticeDetailData?.title }}</h5>
        <div class="flex">
          <p class="text-xl mr-10">관리자</p>
          <p class="text-xl">{{ noticeDetailData?.createdAt.slice(0, 10) }}</p>
        </div>
      </div>
      <p class="border border-b-1 border-gray-300 my-5"></p>
      <div class="mx-10 my-20">
        <p class="text-xl" v-html="noticeDetailData?.content"></p>
      </div>
      <p class="border border-b-1 border-gray-300 my-5"></p>
      <div class="flex flex-col items-center">
        <button
          type="button"
          class="rounded bg-primary mt-4 px-6 py-3 pb-2 pt-2 text-2xl font-bold uppercaseleading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
          style="width: calc(3 * 3rem); height: 3rem"
          @click="goNotice"
        >
          목록
        </button>
      </div>
    </div>
  </div>
  <div v-else>
    <p>Loading...</p>
  </div>
</template>

<script setup lang="ts">
import * as api from '@/api/notice/notice'
import { ref, type Ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { type AxiosResponse } from 'axios'
import type { noticeInfo } from '@/interface/notice/interface'

const router = useRouter()

const noticeNum: number = Number(router.currentRoute.value.params['noticeNum'])

const noticeDetailData: Ref<noticeInfo | undefined> = ref(undefined)

const loaded: Ref<boolean> = ref(false)

function init(): void {
  const param: number = noticeNum

  api.getOneNoticeData(param).then((response: AxiosResponse<{ notice: noticeInfo }>) => {
    if (response.status == 200) {
      noticeDetailData.value = response.data
    }
  })
}

onMounted((): void => {
  init()
})

function goNotice(): void {
  router.push({ name: 'noticeArticles' })
}
</script>

<style scoped></style>
