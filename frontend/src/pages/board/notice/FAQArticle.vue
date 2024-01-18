<template>
  <div class="border border-gray-200 py-10 my-10 p-20">
    <div class="flex">
      <div class="">
        <p class="font-bold text-xl mr-20">12</p>
      </div>
      <div class="flex-1">
        <div class="flex">
          <p
            class="rounded-lg bg-blue-800 text-white text-center white-space: nowrap;"
            style="width: 100px; height: 30px"
          >
            Q
          </p>
          <p class="font-bold text-xl cursor-pointer ml-10 flex-1" @click="toggleAnswer">
            회원 가입 시, 오류가 발생합니다.
          </p>
        </div>
        <div v-if="showAnswer" class="flex my-10">
          <p
            class="rounded-lg bg-red-600 text-white text-center"
            style="width: 100px; height: 30px"
          >
            A
          </p>
          <p class="font-bold text-xl ml-10 flex-1">
            알아서 잘 딱 깔끔하고 센스있게 하시는 방법이 있습니다.
          </p>
        </div>
      </div>
      <div class="ml-5">12.12.12</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import type { Ref } from 'vue'

const showAnswer: Ref<boolean> = ref(false)

const toggleAnswer = (): void => {
  showAnswer.value = !showAnswer.value
}
</script>

<style scoped></style>
