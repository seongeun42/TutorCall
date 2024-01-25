<script setup lang="ts">
import { defineProps } from 'vue'

interface payInfo {
  [key: string]: string | number
}
const payInfoProps = defineProps<{ info: payInfo }>()
</script>
<template>
  <div v-for="(value, key) in payInfoProps.info" :key="key">
    <p class="font-semibold">{{ key }}</p>
    <p>{{ value }}</p>
  </div>
</template>
<style scoped></style>
