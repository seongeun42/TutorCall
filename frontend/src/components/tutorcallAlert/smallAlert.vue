<script setup lang="ts">
import type { Ref } from 'vue'
import { ref } from 'vue'
import { type alertForm } from '@/interface/tutorcall/interface'
import { useUserStore } from '@/store/userStore'
import { useNotificationStore } from '@/store/notificationStore'
import router from '@/router'

const userStore = useUserStore()
const notificationStore = useNotificationStore()

const openDetail: Ref<boolean> = ref(false)
function handleDetail(): void {
  openDetail.value = !openDetail.value
}

const props = defineProps<{ data: alertForm }>()
const emit = defineEmits<{
  change: [hide: boolean]
}>()

function hideAlert(): void {
  emit('change', !props.data.hide)
}

function tutorcallAccept() {
  if (notificationStore.waitingMatching) {
    return
  }
  const uuid = crypto.randomUUID()
  // 매칭 알림 받을 sub 구독
  notificationStore.answerSubscribe(uuid, props.data.id)
  // 대기 상태로 변경
  for (let i = 0; i < notificationStore.problems.length; i++) {
    if (notificationStore.problems[i].id === props.data.id) {
      notificationStore.problems[i].matched = 1
      break
    }
  }
  const message = {
    id: uuid,
    tutorId: userStore.id
  }
  // 요청 수락 메시지 보내기
  notificationStore.sendMessage(`tutorcall/${props.data.id}`, message)
}

const goLecture = () => {
  const sessionId = notificationStore.roomSessionId?.replace('tutorCall', '')
  router.push(`/onlinelecture/${sessionId}`)
}
</script>
<template>
  <div class="w-full grid justify-items-stretch">
    <div
      class="rounded-full justify-self-end bg-black w-4 h-4 mr-2 relative top-[8px] text-white flex justify-center items-center"
      @click="hideAlert"
    >
      <p class="text-center text-white relativ top-[-8px]">x</p>
    </div>
    <div class="bg-sky-200 mx-4 mb-4 min-h-28 rounded-lg">
      <div class="flex justify-between mt-2 mr-2">
        <div class="flex flex-row ml-2">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="yellow"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M14.857 17.082a23.848 23.848 0 0 0 5.454-1.31A8.967 8.967 0 0 1 18 9.75V9A6 6 0 0 0 6 9v.75a8.967 8.967 0 0 1-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 0 1-5.714 0m5.714 0a3 3 0 1 1-5.714 0"
            />
          </svg>
          <p class="text-center text-sm font-semibold">
            {{ props.data.user.nickname }}님이 문제 풀이 요청을 보냈습니다.
          </p>
        </div>
        <div>
          <p class="text-center text-xs">1시간 전</p>
        </div>
      </div>
      <div class="flex flex-row justify-between mt-4">
        <div class="flex flex-col ml-5">
          <p class="font-semibold">{{ props.data.tag.subject }}</p>
          <p>{{ props.data.title }}</p>
        </div>
        <div class="mr-5 pt-2">
          <p class="text-white font-semibold rounded-lg bg-black p-2" @click="handleDetail">
            자세히
          </p>
          <div v-if="openDetail" class="modal-box fixed top-[20%] left-[30%] min-h-[30rem] z-10">
            <div class="flex justify-end cursor-pointer" @click="handleDetail">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="w-6 h-6"
              >
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
              </svg>
            </div>

            <div v-if="props.data.matched != 2">
              <div class="min-h-[2rem] bg-orange-100 m-2 flex items-center justify-center">
                <p class="text-center font-semibold">제목: {{ props.data.title }}</p>
              </div>
              <div class="min-h-2 max-h-[4rem] mr-2 ml-2 mb-2 mt-4 flex flex-row">
                <div class="bg-green-400 rounded-full p-2 mr-3">
                  <p class="text-white font-semibold">{{ props.data.tag.subject }}</p>
                </div>
                <div class="bg-green-400 rounded-full p-2 mr-3">
                  <p class="text-white font-semibold">
                    {{ props.data.tag.level }} {{ props.data.tag.grade }}학년
                  </p>
                </div>
              </div>
              <div
                class="min-h-[15rem] bg-orange-50 mr-2 ml-2 mb-2 mt-4 flex items-center justify-center"
              >
                <p class="text-center font-semibold" v-html="props.data.content"></p>
              </div>
            </div>
            <div v-if="props.data.matched == 2">
              <p>매칭 완료!</p>
            </div>

            <div
              class="flex justify-end mr-2 cursor-pointer"
              v-on:click="tutorcallAccept"
              v-if="props.data.matched == 0"
            >
              <div class="bg-blue-900 rounded-lg px-[2.5rem] py-[0.5rem]">
                <p class="text-white font-semibold">수락</p>
              </div>
            </div>
            <div class="flex justify-end mr-2 cursor-pointer" v-if="props.data.matched == 1">
              <div class="bg-blue-900 rounded-lg px-[2.5rem] py-[0.5rem]">
                <p class="text-white font-semibold">대기중</p>
              </div>
            </div>
            <div class="flex justify-end mr-2 cursor-pointer" v-if="props.data.matched == 2">
              <div class="bg-blue-900 rounded-lg px-[2.5rem] py-[0.5rem]">
                <p @click="goLecture" class="text-white font-semibold">입장하기</p>
              </div>
            </div>
            <div class="flex justify-end mr-2 cursor-pointer" v-if="props.data.matched == 3">
              <div class="bg-blue-900 rounded-lg px-[2.5rem] py-[0.5rem]">
                <p class="text-white font-semibold">거절됨</p>
              </div>
            </div>
          </div>
          <div v-if="openDetail" class="modal-overlay z-5"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.cursor-pointer {
  cursor: pointer;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 배경을 투명한 검정색으로 설정 */
}
</style>
