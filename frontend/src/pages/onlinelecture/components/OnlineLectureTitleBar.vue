<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount, type Ref } from 'vue'
const currentTime: Ref<string> = ref('00:00:00') // 초기 시간 설정
let timer: NodeJS.Timeout | null = null // 타이머 변수
onMounted(() => {
  // 컴포넌트가 활성화된 후 타이머 시작
  startTimer()
})

onBeforeUnmount(() => {
  // 컴포넌트가 제거되기 전 타이머 중지
  if (timer) {
    clearInterval(timer)
  }
})
function startTimer(): void {
  timer = setInterval(() => {
    const timeArray = currentTime.value.split(':') // 시간을 ":"로 분할하여 배열로 저장
    let hours: number = parseInt(timeArray[0])
    let minutes: number = parseInt(timeArray[1])
    let seconds: number = parseInt(timeArray[2])

    // 1초씩 증가
    seconds++

    if (seconds === 60) {
      seconds = 0
      minutes++

      if (minutes === 60) {
        minutes = 0
        hours++
      }
    }

    // 시간을 다시 문자열로 변환하여 업데이트
    currentTime.value = padZero(hours) + ':' + padZero(minutes) + ':' + padZero(seconds)
  }, 1000)
}

function padZero(value: number): string {
  // 숫자가 한 자리일 경우 앞에 0을 추가하여 두 자리로 만듦
  return value.toString().padStart(2, '0')
}
</script>
<template>
  <div class="grid justify-items-stretch grid-cols-10 mt-8 mb-2">
    <div class="justify-self-start col-start-1 col-span-1">
      <button class="btn btn-square">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="w-6 h-6"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15m-3 0-3-3m0 0 3-3m-3 3H15"
          />
        </svg>
      </button>
    </div>
    <div class="justify-self-center col-start-3 col-span-6 font-semibold">
      투블럭의 여집합님의 강의실
    </div>
    <div class="justify-self-end col-start-9 col-span-2">
      <button
        type="button"
        class="py-2 px-4 bg-green-800 hover:bg-blue-700 focus:ring-pink-500 focus:ring-offset-blue-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-full"
      >
        {{ currentTime }}
      </button>
    </div>
  </div>
</template>
<style scoped></style>
