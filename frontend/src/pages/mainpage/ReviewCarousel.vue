<template>
  <div>
    <div class="text-3xl text-center font-black text-neutral-700 mb-5">
      학생들의 리뷰를 확인하세요
    </div>
    <Carousel :autoplay="2000" :itemsToShow="3.95" :wrapAround="true" :transition="500">
      <Slide v-for="(slide, index) in Reviews" :key="index">
        <ReviewDetail :data="slide" />
      </Slide>
    </Carousel>
  </div>
</template>

<script setup lang="ts">
import { ref, defineComponent } from 'vue'
import type { Ref } from 'vue'
import { Carousel, Pagination, Slide } from 'vue3-carousel'
import ReviewDetail from './ReviewDetail.vue'

import 'vue3-carousel/dist/carousel.css'

defineComponent({
  name: 'AutoPlay',
  components: {
    Carousel,
    Slide,
    ReviewDetail
  }
})

interface Review {
  reviewId: number
  profileUrl: string
  nickname: string
  rating: number
  content: string
}

const Reviews: Ref<Review[]> = ref([
  {
    reviewId: 1,
    profileUrl: 'src/img/default_profile.png',
    nickname: '닉네임1',
    rating: 1,
    content:
      '하나쯤은 리뷰 내용이 길어질때 어떻게 표시되는지 테스트하는 내용입니다. 단순히 내용 채우기용입니다. 내용은 내용입니다. 내용. 내 안의 용.'
  },
  {
    reviewId: 2,
    profileUrl: 'src/img/google_logo.png',
    nickname: '닉네임2',
    rating: 1.7,
    content: '리뷰 내용2'
  },
  {
    reviewId: 3,
    profileUrl: 'src/img/insta_logo.png',
    nickname: '닉네임3',
    rating: 2,
    content: '리뷰 내용1'
  },
  {
    reviewId: 4,
    profileUrl: 'src/img/naver_logo.png',
    nickname: '닉네임4',
    rating: 2.4,
    content: '리뷰 내용2'
  },
  {
    reviewId: 5,
    profileUrl: 'src/img/google_logo.png',
    nickname: '닉네임5',
    rating: 3,
    content: '리뷰 내용1'
  },
  {
    reviewId: 6,
    profileUrl: 'src/img/google_logo.png',
    nickname: '닉네임6',
    rating: 3.2,
    content: '리뷰 내용2'
  },
  {
    reviewId: 7,
    profileUrl: 'src/img/google_logo.png',
    nickname: '닉네임7',
    rating: 4.5,
    content: '리뷰 내용1'
  },
  {
    reviewId: 8,
    profileUrl: 'src/img/google_logo.png',
    nickname: '닉네임8',
    rating: 4,
    content: '리뷰 내용2'
  }
])
</script>

<style scoped>
.carousel__slide {
  padding: 5px;
}

.carousel__viewport {
  perspective: 2000px;
  padding-top: 2rem;
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
