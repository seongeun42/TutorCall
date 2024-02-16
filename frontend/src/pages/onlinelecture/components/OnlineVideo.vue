<script setup lang="ts">
import { ref, watch } from 'vue'

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
  <div class="video">
    <video ref="videoRef" autoplay style="width: 100%"></video>
  </div>
</template>

<style scoped>
.video {
  border-radius: 10px;
  overflow: hidden;
}
</style>
