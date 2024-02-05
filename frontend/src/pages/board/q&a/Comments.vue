<script setup lang="ts">
<<<<<<< HEAD
import { type errorResponse, type answerInfo, type commonResponse } from '@/interface/qna/interface'
=======
import {type answerInfo } from '@/interface/qna/interface';
import {type errorResponse, type commonResponse} from '@/interface/common/interface'
>>>>>>> cbf34da (Feat: 수정, 작성 빼고 연결)
import * as api from '@/api/qna/qna'
import { isAxiosError, type AxiosResponse } from 'axios'
import router from '@/router'

const props = defineProps<{ answer: answerInfo }>()
const questionId: number = Number(router.currentRoute.value.params['qnaNum'])

async function deleteAnswer(event: Event): Promise<void> {
  event.preventDefault()

  await api
    .deleteAnswer(props.answer.id)
    .then((response: AxiosResponse<commonResponse>) => {
      console.log(response)
      alert(response.data.message)
      router.go(0)
    })
    .catch((error: unknown) => {
      if (isAxiosError<errorResponse>(error)) {
        alert(error.response?.data.message)
      }
    })
}
</script>
<template>
  <div class="bg-gray-100 mt-10 mb-5 mx-10 pt-5">
    <div class="mx-5">
      <div class="flex ml-5 justify-between items-center">
        <div class="flex">
          <img src="@/img/teacher.png" alt="" class="w-10 h-10 rounded-full" />
          <div>
            <p class="text-sm">{{ props.answer.tutor.nickname }}</p>
            <p class="text-xs">{{ props.answer.createAt }}</p>
          </div>
        </div>
        <div>
          <a href="" class="text-sm mr-10" @click="deleteAnswer">댓글 삭제</a>
          <a href="" class="text-sm mr-5">수정</a>
        </div>
      </div>
      <hr class="mt-5 border-2 border-solid" />
      <p class="my-10 mx-10">
        {{ props.answer.content }}
      </p>
      <div class="pb-10"></div>
    </div>
  </div>
</template>

<style scoped></style>
