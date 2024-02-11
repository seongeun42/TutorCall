<script setup lang="ts">
import { onMounted, watch } from 'vue'
import FloatObject from '@/pages/tutorcall/FloatObject.vue'
import { reactive } from 'vue';

const mainContent = document.querySelector('#mainComponent');
const mainWidth = mainContent?.clientWidth ?? 1960;
const mainHeight = mainContent?.clientHeight ?? 1000;
let idcount = 0;

const arraydata:{
  id: number,
  delay: number,
  size: number,
  objectsize: number,
  positionX: number,
  positionY: number,
  data:any,
}[] = reactive([]);

/* * * * * * * * * * * * *
* 
*   data: 소켓에서 받은 선생님 데이터
*   objectsize
*   position x
*   position y
*
* * * * * * * * * * * * * */


/*
* 이 made by written 중복 회피 로직은 쓰레기다 고쳐야함
*/
function checkoverlapping(objectsize:number, positionX:number, positionY:number):boolean
{
  const length = arraydata.length;

  for(let i =0; i<length-1; i++){
    const r = Math.sqrt(Math.pow(Math.abs(arraydata[i].positionX-positionX),2)+Math.pow(Math.abs(arraydata[i].positionY-positionY),2))
    if(r<175) return true;
  }
  return false;
}

function pushNewData(data: any){
  const objectsize= 175;
  let positionX = Math.floor(Math.random() * (500 - objectsize + 1)) + 100;
  let positionY = Math.floor(Math.random() * ((mainWidth-objectsize)-(200)+1)) + 200

  while(checkoverlapping(objectsize, positionX, positionY)){
    positionX = Math.floor(Math.random() * (500 - objectsize + 1)) + 100;
    positionY = Math.floor(Math.random() * ((mainWidth-objectsize)-(200)+1)) + 200
  }

  const pushdata = {
    id: idcount++,
    delay: Math.floor(Math.random() * 100) + 1,
    size: objectsize,
    objectsize: objectsize,
    positionX: positionX,
    positionY: positionY,
    data: data
  }

  arraydata.push(pushdata);

  setTimeout(() => {
    // 30초 후에 해당 데이터를 삭제하는 로직
    const index = arraydata.findIndex((item) => item.id === pushdata.id);
    if (index !== -1) {
      arraydata.splice(index, 1);
    }
  }, 10000); // 30초를 밀리초로 변환하여 설정합니다.
}

function testBtn():void{
  console.log("ddd");
  pushNewData("testDatainput");
}

</script>

<template>
  <div class="relative">
    <div class="wave"></div>
    <div class="wave -one"></div>
    <div class="wave -two"></div>
    <div class="wave -three"></div>
    <FloatObject
      v-for="(d, idx) in arraydata"
      :pushedData = d
      :class="'obj' + d.id"
      :key="idx"
      :position-y=d.positionY
      :size= d.objectsize
      :position-x=d.positionX
    />
  </div>
  <div @click="testBtn"
  class="bg-gray-300 min-h-8"><p>pushdata</p></div>
</template>
<style scoped>
.relative {
  position: relative;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}
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
