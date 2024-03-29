<script setup lang="ts">
import Comments from './Comments.vue'
import DetailProblem from './DetailProblem.vue'
import router from '@/router/index'
import { onMounted, ref } from 'vue'
import type { Ref } from 'vue'
import * as api from '@/api/qna/qna'
import{ type AxiosResponse, isAxiosError } from 'axios';
import type{ questionInfo, answerForm, answerResponse, answerInfo } from '@/interface/qna/interface'
import type { errorResponse, commonResponse } from '@/interface/common/interface'
import { useEditStore } from '@/store/editStore'
import { reactive } from 'vue'
import { useUserStore } from '@/store/userStore'

const questionData:Ref<questionInfo|null>= ref(null);
const questionId: number = Number(router.currentRoute.value.params['qnaNum'])
const editStore = useEditStore();
let answerData:answerInfo[] = reactive([]);
const selectedAnswer:Ref<answerInfo|null> = ref(null);
const answerInput: Ref<string> = ref('')
const schoolname:Ref<string> = ref('');
const date:Ref<string> = ref('');
const userStore = useUserStore();

async function getQuestion():Promise<void>{
  await api
    .getOneQuestionData(questionId)
    .then((response: AxiosResponse<{ question: questionInfo }>) => {
      questionData.value=response.data.question
      answerData = response.data.question.answerList
      selectedAnswer.value = answerData.filter(item => item.chosen === true)[0] || null;
      switch(questionData.value?.tag.level){
        case "ELEMENTARY":
          schoolname.value = "초등학교";
          break;
        case "MIDDLE":
          schoolname.value = "중학교";
          break;
        case "HIGH":
          schoolname.value = "고등학교";
          break;
      }
      date.value = questionData.value.createdAt.split(".")[0].replace("T", " ");

    })
    .catch((error: unknown) => {
      if (isAxiosError<errorResponse>(error)) {
        alert(error.response?.data.message)
      }
    })
}
onMounted(async (): Promise<void> => {
  getQuestion();
})

function editQuestion(event:Event):void{

  event.preventDefault();

  if(questionData.value){
    editStore.init();

    if(questionData.value.writer.id != Number(userStore.$state.id)){
      alert("수정 권한이 없습니다!");
      return;
    }

    let level:number =0 ;
    switch(questionData.value.tag.level){
      case "ELEMENTARY":
        level = 1;
        break;
      case "MIDDLE":
        level = 31;
        break;
      case "HIGH":
        level = 46;
        break;
    }

    let tagsubject:number=0;
    switch(questionData.value.tag.subject){
      case "국어":
        tagsubject= 0;
        break;
      case "수학":
        tagsubject = 1;
        break;
      case "사회":
        tagsubject = 2;
        break;
      case "과학":
        tagsubject = 3;
        break;
      case "영어":
        tagsubject = 4;
        break;
    }

    const grade:number = (questionData.value.tag.grade-1)*5;

    editStore.save(level,grade,tagsubject,questionData.value.title ,questionData.value.content ?? "", true);
    router.push({"name":"editqna",params: { qnaNum: questionData.value.questionId }})
  }

}

async function deleteQuestion(event: Event): Promise<void> {
  event.preventDefault()
  await api
    .deleteQuestion(questionId)
    .then((response: AxiosResponse<commonResponse>) => {
      alert(response.data.message);
      router.push({ name: 'qnaList' })
    })
    .catch((error: unknown) => {
      if (isAxiosError<errorResponse>(error)) {
        alert(error.response?.data.message)
      }
    })
}

async function registAnswer(event: Event): Promise<void> {
  event.preventDefault()

  if (answerInput.value.length == 0) {
    alert('내용을 입력해 주세요!')
    return
  }

  const param: answerForm = {
    questionId: questionId,
    answerContent: answerInput.value
  }

  await api
    .registAnswer(param)
    .then((response: AxiosResponse<answerResponse>) => {
      alert(response.data.message)
      answerInput.value = '';
      getQuestion();
    })
    .catch((error: unknown) => {
      if (isAxiosError<errorResponse>(error)) {
        alert(error.response?.data.message)
      }
    })
}

function removeAnswer(idx:number){
  getQuestion();
}

function updateEditAnswer(updated: answerInfo):void{
  answerData.forEach((item: answerInfo) => {
  if (item.id === updated.id) {
    item.content = updated.content;
  }
});
}

function goList(): void {
  router.push({ name: 'qna' })
}
</script>
<template>
  <div class="container mx-auto my-10">
    <div class="mx-20 my-10">
      <div class="flex justify-between items-center">
        <div class="flex flex-row gap-4">
          <div class="flex items-center justify-center rounded-full w-20 h-8 bg-sky-300	">
            <p>{{ schoolname }}</p>
          </div>
          <div class="flex items-center justify-center rounded-full w-20 h-8 bg-orange-300">
            <p>{{ questionData?.tag.grade }}학년</p>
          </div>
          <div class="flex items-center justify-center rounded-full w-20 h-8 bg-green-200">
            <p>{{ questionData?.tag.subject }}</p>
          </div>
        </div>
        <div class="flex">
          <a href="" class="mr-3" @click="deleteQuestion($event)">글 삭제</a>
          <a href="" @click="editQuestion">글 수정</a>
        </div>
      </div>
      <h5 class="font-bold text-[1.5rem] my-3">{{ questionData?.title }}</h5>
      <div class="flex justify-between items-center">
        <div class="flex items-center mb-5">
          <img :src="questionData?.writer.profile" alt="" class="w-10 h-10 rounded-full" />
          <p class="ml-1">{{ questionData?.writer.nickname }}</p>
          <p class="ml-3">
            {{ date }}
          </p>
        </div>
      </div>
      <hr />
      <DetailProblem :content="questionData?.content" />
      <div class="bg-gray-200 pb-10 rounded-xl">
        <p class="mx-10 my-10 pt-5 font-bold text-xl">채택된 답변</p>
        <div v-if="selectedAnswer">
          <Comments :answer="selectedAnswer" @update="removeAnswer" @change="updateEditAnswer"/>
        </div>
      </div>
      <div class="bg-gray-200 pb-10 rounded-xl">
        <p class="mx-10 my-10 pt-5 font-bold text-xl">댓글</p>
        <Comments v-for="(answer, index) in answerData" :key="index" :answer="answer" @update="removeAnswer"
        @change="updateEditAnswer"/>
      </div>
      <div class="mt-20 flex justify-center">
        <img :src="userStore.profile" alt="" class="w-20 h-20 rounded-full mr-10" />
        <input
          type="text"
          placeholder="풀이를 입력해주세요"
          class="border-2 border-gray-300 mr-10 rounded-md"
          style="width: 800px; height: 80px"
          v-model="answerInput"
        />
        <button
          type="button"
          class="inline-block rounded bg-blue-800 mt-4 px-6 py-3 pb-2 pt-2.5 text-md uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
          style="width: calc(2 * 3rem); height: 3rem"
          @click="registAnswer($event)"
        >
          작성
        </button>
      </div>
    </div>
    <div class="mt-10 mx-40 flex justify-center items-center">
      <button
        type="button"
        class="bg-sky-100 text-xl rounded-lg shadow-xl font-semibold"
        style="width: calc(3 * 3rem); height: 3rem"
        @click="goList"
      >
        목록
      </button>
    </div>
  </div>
</template>

<style scoped></style>
