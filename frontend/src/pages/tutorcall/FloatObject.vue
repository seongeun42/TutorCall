<script setup lang="ts">
import type { Ref } from 'vue';
import { ref } from 'vue';
import SpeechBubble from '@/pages/tutorcall/SpeechBubble.vue';
import floatingObject from '@/util/animation/floatingObject'
import { onMounted } from 'vue';
import type { acceptTutor } from '@/interface/tutorcall/interface'

const props = defineProps<{
  accept: acceptTutor,
}>()

const show:Ref<boolean> = ref(false);

function click():void{
  show.value = !show.value
}

onMounted(()=>{
  floatingObject('.obj'+props.accept.id, props.accept.delay, props.accept.size);
})
</script>
<template>
  <div
  @click="click"
    class="obj"
    :style="{
      width: props.accept.size + 'px',
      height: props.accept.size + 'px',
      top: props.accept.positionX + 'px',
      left: props.accept.positionY + 'px',
      transform: 'translate(-50%, -50%)',
      backgroundImage: 'url('+props.accept.tutor.profile+')',
      animation: 'ani' + Math.floor(Math.random() * 3) + 1
    }"
  >
    <div v-if="show">
      <SpeechBubble :accept=props.accept />
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
