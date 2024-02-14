<template>
  <div v-for="data in noticeData" :data="data" @click="goNoticeDetail(data.noticeId)">
    <div class="border border-gray-200 py-10 my-10">
      <div class="flex ml-10 flex-row justify-between">
        <div class="flex ml-10">
          <p>{{ data.noticeId }}</p>
          <p
            class="mx-10 rounded-lg bg-blue-800 text-white text-center"
            style="width: 90px; height: 30px"
          >
            공지
          </p>
        </div>
        <p class="font-bold text-xl">{{ data.title }}</p>
        <p class="mx-10">{{ data.createdAt.slice(0, 10) }}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import * as api from '@/api/notice/notice'
import { ref, type Ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { type AxiosResponse } from 'axios'
import type { NoticeInfo, NoticeResponse } from '@/interface/notice/interface'

const noticeData: Ref<NoticeInfo[]> = ref([])
const router = useRouter()

async function init(): Promise<void> {
  await api.getNoticeData().then((response: AxiosResponse<NoticeResponse>) => {
    console.log(response)
    if (response.status == 200) {
      noticeData.value = response.data.notices
    }
  })
}

function goNoticeDetail(id: number): void {
  router.push({ name: 'noticeDetail', params: { noticeNum: id } })
}

onMounted(async (): Promise<void> => {
  await init()
})
</script>

<style scoped></style>
