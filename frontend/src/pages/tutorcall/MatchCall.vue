<script setup lang="ts">
import FloatObject from '@/pages/tutorcall/FloatObject.vue'
import MatchText from '@/pages/tutorcall/MatchText.vue'
import type { DefineProps } from 'vue';

interface data{
  id: number,
  delay: number,
  size: number,
  objectsize: number,
  positionX: number,
  positionY: number,
  data:any,
}

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


const props= defineProps<{'userId':number}>();
</script>

<template>
  <div>
    <FloatObject :pushedData="input" />
  </div>
</template>

<style scoped></style>
