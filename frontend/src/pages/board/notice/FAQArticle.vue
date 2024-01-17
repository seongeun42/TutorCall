<template>
  <div class="border border-gray-200 py-10 my-10 p-20">
    <div class="flex">
      <div class="">
        <p class="font-bold text-xl mr-20">12</p>
      </div>
      <div class="flex-1">
        <div class="flex">
          <p class="w-20 rounded-lg bg-blue-800 text-white text-center">Q</p>
          <a class="font-bold text-xl cursor-pointer ml-10" @click="toggleAnswer">
            회원 가입 시, 오류가 발생합니다.
          </a>
        </div>
        <div v-if="showAnswer" class="flex my-10">
          <p class="w-20 rounded-lg bg-red-600 text-white text-center">A</p>
          <p class="font-bold text-xl ml-10">
            알아서 잘 딱 깔끔하고 센스있게 하시는 방법이 있습니다.
          </p>
        </div>
      </div>
      <div>12.12.12</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const showAnswer = ref(false)

const toggleAnswer = () => {
  showAnswer.value = !showAnswer.value
}
</script>

<style scoped></style>
