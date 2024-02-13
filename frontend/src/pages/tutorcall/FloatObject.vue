<script setup lang="ts">
import type { Ref } from 'vue';
import { ref } from 'vue';
import SpeechBubble from '@/pages/tutorcall/SpeechBubble.vue';
import floatingObject from '@/util/animation/floatingObject'
import { onMounted } from 'vue';

interface pushdata{
    id: number,
    delay: number,
    size: number,
    objectsize: number,
    positionX: number,
    positionY: number,
    data: any,
  }

  const props = defineProps<{
  pushedData: pushdata,
}>()

const show:Ref<boolean> = ref(false);

function click():void{
  show.value = !show.value
}

onMounted(()=>{
  floatingObject('.obj'+props.pushedData.id, props.pushedData.delay, props.pushedData.size);
})
</script>
<template>
  <div
  @click="click"
    class="obj"
    :style="{
      width: props.pushedData.size + 'px',
      height: props.pushedData.size + 'px',
      top: props.pushedData.positionX + 'px',
      left: props.pushedData.positionY + 'px',
      transform: 'translate(-50%, -50%)',
      backgroundImage: 'url(https://via.placeholder.com/' + props.pushedData.size + 'x' + props.pushedData.size + ')',
      animation: 'ani' + Math.floor(Math.random() * 3) + 1
    }"
  >
  <div v-if="show">
    <SpeechBubble :pushedData=props.pushedData />
  </div>
  </div>
</template>
<style scoped>
.obj {
  position: absolute;

  border-radius: 50%;
}

.obj-inner {
  animation: ani 1s infinite alternate;
}

@keyframes ani1 {
  0% {
    transform: translate(0, 0);
  }
  100% {
    transform: translate(0, 50px);
  }
}
@keyframes ani2 {
  0% {
    transform: translate(0, 0);
  }
  100% {
    transform: translate(0, 50px);
  }
}
@keyframes ani3 {
  0% {
    transform: translate(0, 0);
  }
  100% {
    transform: translate(0, 50px);
  }
}
</style>
