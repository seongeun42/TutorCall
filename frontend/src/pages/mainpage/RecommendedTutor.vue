<template>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
  />
  <div class="card">
    <div class="card-bg" :style="{ backgroundImage: 'url(' + data.profileUrl + ')' }"></div>
    <div class="card-content">
      <h2 class="promotion-title">"{{ data.promotionTitle }}"</h2>
      <p class="tutor-nickname">{{ data.tutorNickname }}</p>
      <p class="introduction">{{ data.introduction }}</p>
      <div class="reliability-rate">
        <i
          v-for="i in Math.floor(data.reliability / 20)"
          :key="`${data.lectureId}+${i}-${i}`"
          class="fa-solid fa-heart"
        ></i>
        <i v-if="data.reliability % 20 >= 10" class="fa-regular fa-heart"></i>
        <i v-if="data.reliability % 20 >= 20" class="fa-regular fa-heart"></i>
        &nbsp;{{ data.reliability }}
      </div>
      <div class="tag"># {{ data.tag }}</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue'

interface Promotion {
  lectureId: number
  promotionTitle: string
  // promotionContent: string
  tutorId: number
  tutorNickname: string
  introduction: string
  profileUrl: string
  reliability: number
  tag: string
}

defineProps({
  data: {
    type: Object as () => Promotion,
    required: true
  }
})
</script>

<style scoped>
.card {
  width: 300px;
  height: 400px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.card-bg {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
}

.card-content {
  padding: 16px;
  background-color: #ffffff;
}

.promotion-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 8px;
}

.tutor-nickname {
  font-size: 16px;
  color: #888888;
  margin-bottom: 8px;
}

.introduction {
  font-size: 14px;
  margin-bottom: 8px;
}

.reliability-rate {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #ff1500;
  margin-bottom: 8px;
}

.reliability-rate i {
  margin-right: 4px;
  font-size: 16px;
}

.tag {
  font-size: 14px;
  background-color: #f2f2f2;
  padding: 4px 8px;
  border-radius: 4px;
  display: inline-block;
}
</style>
