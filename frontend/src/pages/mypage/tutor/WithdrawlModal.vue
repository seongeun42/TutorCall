<template>
  <div class="flex justify-end my-10">
    <button
      @click="clickModal"
      class="bg-blue-900 rounded-2xl w-32 h-12 text-gray-100 text-xl font-bold"
    >
      전환하기
    </button>
  </div>

  <div v-if="modalOn" class="modal-overlay">
    <div class="container bg-gray-200 rounded-xl">
      <div class="py-5 mx-5 flex justify-end">
        <svg
          @click="clickModal"
          class="w-4 h-4 cursor-pointer"
          aria-hidden="true"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 14 14"
        >
          <path
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
          />
        </svg>
      </div>
      <div class="p-10">
        <div class="mb-10">
          <div class="text-3xl font-bold text-blue-900 mb-10">계좌번호</div>
          <input
            type="text"
            placeholder="계좌번호를 입력하세요"
            class="bg-white w-full h-20 rounded-lg"
          />
        </div>
        <div class="my-10">
          <div class="text-3xl font-bold text-blue-900 mb-10">은행명</div>
          <select class="w-full h-20 rounded-lg text-2xl">
            <option value="국민은행">국민은행</option>
            <option value="하나은행">하나은행</option>
            <option value="신한은행">신한은행</option>
            <option value="농협은행">농협은행</option>
            <option value="우리은행">우리은행</option>
          </select>
        </div>
        <div class="my-10">
          <div class="text-3xl font-bold text-blue-900 mb-10">출금할 금액</div>
          <input
            type="text"
            placeholder="계좌번호를 입력하세요"
            class="bg-white w-full h-20 rounded-lg"
          />
        </div>
        <div class="mt-10">
          <div class="text-3xl font-bold text-blue-900 mb-10">예금주명</div>
          <input
            type="text"
            placeholder="계좌번호를 입력하세요"
            class="bg-white w-full h-20 rounded-lg"
          />
        </div>
        <div class="my-10 flex justify-center items-center">
          <button class="w-32 h-12 bg-blue-900 text-white rounded-xl text-lg font-bold">
            출금하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { type Ref, ref } from 'vue'
const modalOn: Ref<boolean> = ref(false)
const clickModal = (): void => {
  modalOn.value = !modalOn.value
}
</script>

<style scoped>
input::placeholder {
  text-align: center;
  font-size: 1.2em;
  font-style: initial;
  font-weight: bold;
}

option {
  text-align: center;
  font-size: 1.2em;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  width: 40%;
  height: 70%;
  overflow: hidden;
}

.container > div {
  max-height: 100%;
  overflow-y: auto;
}
</style>
