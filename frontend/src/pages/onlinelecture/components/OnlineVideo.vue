<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'

const props = defineProps({
  streamManager: Object
})
const videoRef = ref(null)

watch(props, () => {
  if (props.streamManager) {
    props.streamManager.addVideoElement(videoRef.value)
  }
})
</script>

<template>
  <video ref="videoRef" autoplay></video>
</template>

<style scoped></style>
