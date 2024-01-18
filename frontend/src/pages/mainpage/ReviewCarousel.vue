<template>
  <div>
    <div class="text-3xl text-center font-black text-neutral-700 mb-5">
      학생들의 리뷰를 확인하세요
    </div>
    <Carousel :autoplay="2000" :itemsToShow="3.95" :wrapAround="true" :transition="500">
      <Slide v-for="(slide, index) in 10" :key="index">
        <ReviewDetail :data="slide" />
        <!-- <div class="carousel__item">{{ slide }}</div> -->
      </Slide>

      <!-- ... -->
    </Carousel>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { Carousel, Pagination, Slide } from 'vue3-carousel'
import ReviewDetail from './ReviewDetail.vue'

import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'Autoplay',
  components: {
    Carousel,
    Slide,
    Pagination,
    ReviewDetail
  }
})
</script>

<style scoped>
.carousel__slide {
  padding: 5px;
}

.carousel__viewport {
  perspective: 2000px;
}

.carousel__track {
  transform-style: preserve-3d;
}

.carousel__slide--sliding {
  transition: 0.5s;
}

.carousel__slide {
  opacity: 0.9;
  transform: rotateY(-20deg) scale(0.9);
}

.carousel__slide--active ~ .carousel__slide {
  transform: rotateY(20deg) scale(0.9);
}

.carousel__slide--prev {
  opacity: 1;
  transform: rotateY(-10deg) scale(0.95);
}

.carousel__slide--next {
  opacity: 1;
  transform: rotateY(10deg) scale(0.95);
}

.carousel__slide--active {
  opacity: 1;
  transform: rotateY(0) scale(1.1);
}
</style>
