<template>
  <div class="container bg-gray-200 rounded-xl">
    <div class="p-20">
      <div class="my-10">
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
    </div>
    <div class="flex justify-center items-center">
      <button class="w-36 h-20 bg-blue-800 text-white rounded-2xl text-2xl font-bold">
        출금하기
      </button>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
input::placeholder {
  text-align: center;
  font-size: 1.2em;
  font-style: initial;
  font-weight: bold;
}

.container {
  width: 1000px;
  height: 1100px;
}
option {
  text-align: center;
  font-size: 1.2em;
}
</style>
