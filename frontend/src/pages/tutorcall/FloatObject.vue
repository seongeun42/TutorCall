<template>
  <div
    class="obj"
    :style="{
      width: props.size + 'px',
      height: props.size + 'px',
      top: props.positionX + '%',
      left: props.positionY + '%',
      backgroundImage: 'url(https://via.placeholder.com/' + props.size + 'x' + props.size + ')',
      animation: 'ani' + Math.floor(Math.random() * 3) + 1
    }"
  ></div>
</template>

<script setup lang="ts">
const props = defineProps<{
  positionX: number
  positionY: number
  size: number
}>()
</script>

<style scoped>
.obj {
  position: absolute;

  border-radius: 50%;

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
