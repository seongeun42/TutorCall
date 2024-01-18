<script setup lang="ts">
import { onMounted, watch } from 'vue'
import gsap from 'gsap'
import floatingObject from '@/util/animation/floatingObject'
import FloatObject from '@/pages/tutorcall/FloatObject.vue'
const data: Array<any> = []
;(() => {
  for (let i = 1; i <= 10; i++) {
    data.push({
      id: i,
      delay: Math.floor(Math.random() * 100) + 1,
      size: Math.floor(Math.random() * 100) + 1
    })
  }
})()

onMounted(() => {
  console.log('data ' + data)
  data.forEach((d) => {
    console.log('foreach d ', d)
    floatingObject('.obj' + d.id, d.delay, d.size)
  })
})
</script>

<template>
  <div class="relative">
    <div class="wave"></div>
    <div class="wave -one"></div>
    <div class="wave -two"></div>
    <div class="wave -three"></div>
    <FloatObject
      v-for="(d, idx) in data"
      :class="'obj' + d.id"
      :key="idx"
      :position-y="Math.floor(Math.random() * 100) + 1"
      :size="Math.floor(Math.random() * 100) + 100"
      :position-x="Math.floor(Math.random() * 100) + 1"
    />
  </div>
</template>
<style scoped>
.obj1 {
  position: absolute;
  width: 300px;
  height: 300px;
  border-radius: 50%;
  background-image: url('https://via.placeholder.com/300x300');
}

.wave {
  overflow: hidden;
  opacity: 0.4;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  border: 0.5px solid #3781aa;
  width: 30px;
  height: 30px;

  -webkit-transform-origin: 50% 48%;
  transform-origin: 50% 48%;
  border-radius: 43%;
  -webkit-animation: drift 3000ms infinite linear;
  animation: drift 3000ms infinite linear;
}

.wave.-one {
  -webkit-animation: drift 7000ms infinite linear;
  animation: drift 7000ms infinite linear;
  opacity: 0.1;
  border: 0.5px solid #4eabc1;
}
.wave.-two {
  -webkit-animation: drift 7000ms infinite linear;
  animation: drift 7000ms infinite linear;
  opacity: 0.1;
  border: 0.5px solid #4eabc1;
}
.wave.-three {
  -webkit-animation: drift 5000ms infinite linear;
  animation: drift 5000ms infinite linear;
  opacity: 0.1;
  border: 1px solid #4eabc1;
}

@-webkit-keyframes drift {
  from {
    -webkit-transform: rotate(0deg) scale(0, 0);
    transform: rotate(0deg) scale(0, 0);
    opacity: 1;
  }
  to {
    -webkit-transform: rotate(360deg) scale(10, 10);
    transform: rotate(360deg) scale(1000, 1000);
    opacity: 0;
  }
}

@keyframes drift {
  from {
    -webkit-transform: rotate(0deg) scale(0, 0);
    transform: rotate(0deg) scale(0, 0);
    opacity: 1;
  }
  to {
    -webkit-transform: rotate(360deg) scale(5, 5);
    transform: rotate(360deg) scale(100, 100);
    opacity: 0;
  }
}
</style>
