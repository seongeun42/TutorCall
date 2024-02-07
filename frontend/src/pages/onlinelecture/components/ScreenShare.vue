<script setup lang="ts">
import { ref, type Ref, onMounted, watch } from 'vue'

const props = defineProps({
  streamManager: Object
})
const videoRef: Ref<any> = ref(null)

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
