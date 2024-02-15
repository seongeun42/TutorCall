<script setup lang="ts">
import {type answerInfo, type editAnswer } from '@/interface/qna/interface';
import {type errorResponse, type commonResponse} from '@/interface/common/interface'
import * as api from '@/api/qna/qna'
import { isAxiosError, type AxiosResponse } from 'axios'
import type { Ref } from 'vue';
import { ref } from 'vue';
import { useUserStore } from '@/store/userStore';

const props = defineProps<{ answer: answerInfo }>()
const userStore = useUserStore();
const editMode:Ref<boolean> = ref(false);
const editData:Ref<string> = ref(props.answer.content);
const date:string = props.answer.modifiedAt.split(".")[0].replace("T", " ");

const emit = defineEmits(['update', 'change']);

function modeToEdit(event:Event){
  event.preventDefault();
  if(props.answer.tutor.id != userStore.$state.id){
    alert("수정 권한이 없습니다!");
    return;
  }
  editMode.value = !editMode.value;
}

async function deleteAnswer(event: Event): Promise<void> {
  event.preventDefault()

  await api
    .deleteAnswer(props.answer.id)
    .then((response: AxiosResponse<commonResponse>) => {
      alert(response.data.message)
      emit("update", props.answer.id);
    })
    .catch((error: unknown) => {
      if (isAxiosError<errorResponse>(error)) {
        alert(error.response?.data.message)
      }
    })
}

async function editanswer(event: Event):Promise<void>{

  event.preventDefault();

  const param:editAnswer = { answerContent: editData.value };

  await api.editAnswer(param, props.answer.id)
  .then((response: AxiosResponse<commonResponse>)=>{
    alert(response.data.message);
    const updateAnswer:answerInfo ={
      chosen: props.answer.chosen,
      content: editData.value,
      createAt: props.answer.createAt,
      delete: props.answer.delete,
      id: props.answer.id,
      modifiedAt: props.answer.modifiedAt,
      tutor: props.answer.tutor
    }
    emit('change', updateAnswer);
  })
  .catch((error:unknown)=>{
    if(isAxiosError<errorResponse>(error)) {
      alert(error.response?.data.message);
    }
  })
  editMode.value = !editMode.value;
}

async function selectAnswer(event: Event):Promise<void>{
  event.preventDefault();
  await api.selectAnswer(props.answer.id)
  .then((response: AxiosResponse<commonResponse>)=>{
    alert(response.data.message);
    emit("update", props.answer.id);
  }).catch((error:unknown)=>{
    if(isAxiosError<errorResponse>(error)) alert(error.response?.data.message);
  })
}

</script>
<template>
  <div class="bg-gray-100 mt-10 mb-5 mx-10 pt-5">
    <div class="mx-5">
      <div class="flex ml-5 justify-between items-center">
        <div class="flex">
          <img :src="props.answer.tutor.profile" alt="" class="w-10 h-10 rounded-full" />
          <div>
            <p class="text-sm">{{ props.answer.tutor.nickname }}</p>
            <p class="text-xs">{{ date }}</p>
          </div>
        </div>
        <div>
          <a href="" class="text-sm mr-10" @click="deleteAnswer">댓글 삭제</a>
          <a href="" class="text-sm mr-5" @click="modeToEdit">수정</a>
          <a href="" class="text-sm mr-10" @click="selectAnswer">댓글 채택</a>
        </div>
      </div>
      <hr class="mt-5 border-2 border-solid" />
      <p v-if="!editMode" class="my-10 mx-10">
        {{ props.answer.content }}
      </p>
      <div v-else class="mt-20 flex justify-center">
        <input
          type="text"
          class="border-2 border-gray-300 mr-10 rounded-md"
          style="width: 800px; height: 80px"
          v-model="editData"
        />
        <button
          type="button"
          class="inline-block rounded bg-blue-800 mt-4 px-6 py-3 pb-2 pt-2.5 text-md uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
          style="width: calc(2 * 3rem); height: 3rem"
          @click="editanswer($event)"
        >
          수정
        </button>
      </div>
      <div class="pb-10"></div>
    </div>
  </div>
</template>

<style scoped></style>
