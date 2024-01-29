<script setup lang="ts">
// TODO: fade in out 적용 OR 사라지는 효과 넣어야 함
</script>

<template>
  <div>
    <span>매</span>
    <span>칭</span>
    <span>완</span>
    <span>료</span>
    <span>!</span>
  </div>
</template>

<style scoped>
div {
  -webkit-font-smoothing: antialiased;
  z-index: 999;
}

span {
  position: relative;
  top: 20px;
  display: inline-block;
  animation: bounce 0.3s ease infinite alternate;
  font-family: 'Titan One', cursive;
  font-size: 80px;
  color: #000;
  text-shadow:
    0 1px 0 #ccc,
    0 2px 0 #ccc,
    0 3px 0 #ccc,
    0 4px 0 #ccc,
    0 5px 0 #ccc,
    0 6px 0 transparent,
    0 7px 0 transparent,
    0 8px 0 transparent,
    0 9px 0 transparent,
    0 10px 10px rgba(0, 0, 0, 0.4);
}

div span:nth-child(2) {
  animation-delay: 0.1s;
}
div span:nth-child(3) {
  animation-delay: 0.2s;
}
div span:nth-child(4) {
  animation-delay: 0.3s;
}
div span:nth-child(5) {
  animation-delay: 0.4s;
}
div span:nth-child(6) {
  animation-delay: 0.5s;
}
div span:nth-child(7) {
  animation-delay: 0.6s;
}
div span:nth-child(8) {
  animation-delay: 0.7s;
}

@keyframes bounce {
  100% {
    top: -20px;
    text-shadow:
      0 1px 0 #ccc,
      0 2px 0 #ccc,
      0 3px 0 #ccc,
      0 4px 0 #ccc,
      0 5px 0 #ccc,
      0 6px 0 #ccc,
      0 7px 0 #ccc,
      0 8px 0 #ccc,
      0 9px 0 #ccc,
      0 50px 25px rgba(0, 0, 0, 0.2);
  }
}
</style>
