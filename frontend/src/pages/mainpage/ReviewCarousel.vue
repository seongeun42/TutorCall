<template>
  <div>
    <div v-if="userStore.isTutor">
      <div class="text-3xl text-center font-black text-neutral-700 mb-5">
        내 강의를 들은 학생들의 리뷰를 확인하세요
      </div>
      <Carousel :autoplay="2000" :itemsToShow="3.95" :wrapAround="true" :transition="500">
        <Slide v-for="(slide, index) in tutorReviews" :key="index">
          <ReviewDetail :data="slide" />
        </Slide>
      </Carousel>
    </div>
    <div v-if="!userStore.isTutor && userStore.isLogin">
      <div class="text-3xl text-center font-black text-neutral-700 mb-5">
        내가 등록한 리뷰를 확인하세요
      </div>
      <Carousel :autoplay="2000" :itemsToShow="3.95" :wrapAround="true" :transition="500">
        <Slide v-for="(slide, index) in myReviews" :key="index">
          <ReviewDetail :data="slide" />
        </Slide>
      </Carousel>
    </div>
    <!-- <div v-else>
      <div class="text-3xl text-center font-black text-neutral-700 mb-5">
        비로그인용 더미데이터?
      </div>
      <Carousel :autoplay="2000" :itemsToShow="3.95" :wrapAround="true" :transition="500">
        <Slide v-for="(slide, index) in Reviews" :key="index">
          <ReviewDetail :data="slide" />
        </Slide>
      </Carousel>
    </div> -->
    <!-- <RouterLink :to="{ name: 'tutorMyLectures', params: { userId: 필요한가? }}" v-if="isTutor" class="more" > 더 보기 > </RouterLink> // 이 부분 선생마이페이지 리뷰 추가되면 작성하기 -->
  </div>
</template>

<script setup lang="ts">
import { ref, defineComponent, onMounted } from 'vue'
import type { Ref } from 'vue'
import { Carousel, Slide } from 'vue3-carousel'
import ReviewDetail from './ReviewDetail.vue'
import { useUserStore } from '@/store/userStore'
import * as api from '@/api/mainpage/mainpage'
import { type AxiosResponse } from 'axios'
import type {reviewerInfo, tutorInfo, tutorReviewInfo, tutorReviewResponse, tagInfo, lectureResponse} from '@/interface/mainpage/interface'

import 'vue3-carousel/dist/carousel.css'

const userStore = useUserStore()

async function init(tutorId: number): Promise<void> {
  await api.tutorReview(tutorId).then((response: AxiosResponse<tutorReviewResponse>) => {
    if (response.status == 200) {
      for(let i = 0; i < response.data.content.length; i++) {
        const review = response.data.content[i];
  const reviewer = review.reviewer;

  tutorReviews.value.push({
    profileUrl: reviewer.profile,
    nickname: reviewer.nickname,
    rating: ((review.communicationRate + review.mannerRate + review.professionalismRate) / 3),
    content: review.content
  });
      }
      // tutorReviews.value = response.data.content.
      console.log(tutorReviews.value)
      // console.log(response.data.content[0])
    }
  })
}

defineComponent({
  name: 'AutoPlay',
  components: {
    Carousel,
    Slide,
    ReviewDetail
  }
})

interface Review {
  // reviewId: number
  profileUrl: string
  nickname: string
  rating: number
  content: string
}

const tutorReviews: Ref<Review[]> = ref([])

const myReviews: Ref<Review[]> = ref([])

const defaultReviews: Ref<Review[]> = ref([
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

onMounted(async (): Promise<void> => {
  await init(1)
})
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

.more {
  color: #fff;
  background: linear-gradient(315deg, #42d392 25%, #647eff);
  border: none;
  padding: 5px 10px;
  margin: 5px;
  border-radius: 8px;
  cursor: pointer;
  float: right;
}
</style>
