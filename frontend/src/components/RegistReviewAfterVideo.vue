<script setup lang="ts">
import router from '@/router';
import lectureReview from '@/components/Review.vue'
import type { ComputedRef } from 'vue';
import { computed } from 'vue';

const mode:ComputedRef<string> = computed(()=>{
    if(router.currentRoute.value.fullPath.includes('tutorcall')){
        return 'tutorcall'
    }else return 'lecture'
})

const id:ComputedRef<number> = computed(()=>{
    if(router.currentRoute.value.fullPath.includes('tutorcall')){
        // tutorcallid를 어떻게 받는질 모르겠음. router로 전달해주면 될듯??
        return Number(router.currentRoute.value.params.tutorcallId[0]);
    }else return Number(router.currentRoute.value.params.lectureId[0]);
})

function afterReview():void{
    router.push('/');
}

</script>
<template>
    <div>
        <div class="review-box rounded-xl shadow-md">
            <div class="modal-box fixed top-[10%] left-[35%] min-h-[30rem] z-10">
                <lectureReview :mode="mode" @update="afterReview" @change="afterReview" :id="id"/>
            </div>
            <div class="modal-overlay  z-5"></div>
        </div>
      </div>
</template>
<style scoped>
.review-box {
  background-color: #faf6ef;
  min-width: 480px;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 배경을 투명한 검정색으로 설정 */
}
</style>