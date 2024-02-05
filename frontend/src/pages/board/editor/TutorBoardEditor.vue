<template>
  <div class="mx-auto w-[1000px]">
    <div class="my-10">
      <div class="flex items-center">
        <input class="title" type="text" v-model="title" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, type Ref } from 'vue'
const title: Ref<string> = ref('')
</script>

<style scoped></style>
