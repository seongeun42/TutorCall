<template>
  <div>
    <div class="font-black text-3xl mb-10">
      현재 사용 가능한 포인트: <span class="text-blue-800">{{ availablePoint }}P</span>
    </div>
    <div class="font-black text-2xl">포인트 내역</div>
    <div><UsedList /></div>
    <br />
    <hr />
    <br />
    <div class="font-black text-2xl">쿠폰 내역</div>
    <div><NowUsable /></div>
  </div>
</template>

<script setup lang="ts">
import NowUsable from '@/pages/mypage/student/point/NowUsable.vue'
import UsedList from '@/pages/mypage/student/point/UsedList.vue'
import type { Ref } from 'vue'
import { ref } from 'vue'

const availablePoint: Ref<String> = ref('20,000') // 이부분은 db에서 가져올 예정.
</script>

<style scoped></style>
