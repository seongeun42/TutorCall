<template>
  <div v-for="index in props.score" :key="index">
    <img src="@/img/star.png" alt="" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, defineProps, type Ref } from 'vue'

const props = defineProps<{ score: number }>()
onMounted(() => {
  // Your onMounted logic goes here
})
</script>

<style scoped>
img {
  width: 25px;
  height: 25px;
}
</style>
