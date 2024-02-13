<script setup lang="ts">
import FloatObject from '@/pages/tutorcall/FloatObject.vue'
import MatchText from '@/pages/tutorcall/MatchText.vue'
import { defineProps } from 'vue';

interface data{
  id: number,
  delay: number,
  size: number,
  objectsize: number,
  positionX: number,
  positionY: number,
  data:any,
}

const props = defineProps<{
  pushedData: data
}>()

const mainContent = document.querySelector('#mainComponent');
const mainWidth = mainContent?.clientWidth ?? 1960;
const mainHeight = mainContent?.clientHeight ?? 1000;

const input = {
  id: 1,
  delay: 0,
  size: 300,
  objectsize: 300,
  positionX: mainHeight/2,
  positionY: mainWidth/2,
  data: null
}


// const props= defineProps<{'userId':number}>();
</script>

<template>
  <div class="container">
    <div>
      <MatchText/>
    </div>
    <div class="image-container">
      <img src="https://via.placeholder.com/300x300" alt="Test Image">
    </div>
  </div>
</template>

<style scoped>
.container {
  justify-content: center;
  align-items: center; 
  width: 100vw;
  height: 100vh;
}

.image-container {  
  top: 50%;
  left: 50%;
  width: 300px;
  height: 300px;
  border-radius: 50%;
  overflow: hidden;
}
</style>
