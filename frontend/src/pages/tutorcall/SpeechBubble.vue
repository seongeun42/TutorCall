<script setup lang="ts">
import MatchText from '@/pages/tutorcall/MatchText.vue'
import router from '@/router'
import { onMounted } from 'vue'
import type { acceptTutor } from '@/interface/tutorcall/interface'
import { useNotificationStore } from '@/store/notificationStore'

const notificationStore = useNotificationStore()

function matching():void{
  router.push({name:'matchcall'});
}

const props = defineProps<{
  accept: acceptTutor,
}>()

function matchAccept() {
  notificationStore.answerSubscribe(props.accept.data.resId, props.accept.data.reqId)
  const message = {
    reqId: props.accept.data.reqId,
    tutor: props.accept.data.tutor.id
  }
  notificationStore.sendMessage(`tutorcall/answer/${props.accept.data.resId}`, message)
}

function matchReject() {
  notificationStore.sendMessage(`tutorcall/answer/${props.accept.data.resId}/rejection`, null)
}

// onMounted(()=> console.log(props.pushedData))
</script>
<template>
  <div class="speech-bubble">
    <div class="absolute top-3 right-5">x</div>
    <div class="mx-auto my-10 w-10/12">
      <div class="font-bold">{{ accept.data.tutor.nickname }}님</div>
      <div>
        <span class="font-bold text-xl">4.0</span>
        <span>
          <span>★</span>
          <span>★</span>
          <span>★</span>
          <span>★</span>
          <span>☆</span>
        </span>
      </div>
      <div class="mt-1.5">
        <div class="font-bold text-gray-400 text-xs">항목별 평점</div>
        <div class="flex">
          <div class="pr-4 font-bold">{{ accept.data.tutor.professionalismRate }}</div>
          <div class="flex-1">전문성</div>
        </div>
        <div class="flex">
          <div class="pr-4 font-bold">{{ accept.data.tutor.mannerRate }}</div>
          <div class="flex-1">강의 매너</div>
        </div>
        <div class="flex">
          <div class="pr-4 font-bold">{{ accept.data.tutor.communicationRate }}</div>
          <div class="flex-1">내용 전달력</div>
        </div>
      </div>
      <div class="text-center mt-1.5">
        <RouterLink :to="{ name: 'matchcall'}" class="mr-3 bg-blue-600 text-center inline-block text-white rounded p-1.5"
        :accept="props.accept" v-on:click="matchAccept">
            수락
      </RouterLink>
        <button class="mr-3 bg-red-600 text-center inline-block text-white rounded p-1.5" v-on:click="matchReject">
          거절
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.speech-bubble {
  position: relative;
  background: #fff;
  border-radius: 2.5em;
  border: 5px solid #000;
  margin: 0 auto;
  width: 250px;
  z-index: 999;
}

.speech-bubble:after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 50%;
  width: 0;
  height: 0;
  border: 20px solid transparent;
  border-top-color: #000;
  border-bottom: 0;
  margin-left: -20px;
  margin-bottom: -20px;
}
</style>
