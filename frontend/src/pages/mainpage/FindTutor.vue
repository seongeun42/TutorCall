<template>
  <div class="w-100% mt-20">
    <div class="mb-4">
      <div class="block text-3xl text-center truncate">
        <p class="font-black text-center">김선생님도 과외 홍보 게시판에 등록해보세요</p>
      </div>
    </div>
    <Carousel :items-to-show="2.5" :wrapAround="true">
      <Slide v-for="(slide, index) in Promotions" :key="index">
        <div class="carousel__item"><RecommendedTutorVue :data="slide" /></div>
      </Slide>
      <template #addons>
        <Navigation />
      </template>
    </Carousel>
  </div>
</template>

<script setup lang="ts">
import RecommendedTutorVue from './RecommendedTutor.vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'
import { ref, type Ref, defineComponent } from 'vue'

defineComponent({
  name: 'WrapAround',
  components: {
    Carousel,
    Slide,
    Navigation,
    RecommendedTutorVue
  }
})

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

const Promotions: Ref<Promotion[]> = ref([
  {
    lectureId: 1,
    promotionTitle: '절찬리 제자 모집중 과연 제목 길이의 한계는 어디일것인가위바위보',
    // promotionContent: '내가 수포자다 싶으면 들어오세요',
    tutorId: 1,
    tutorNickname: '투블럭의 여집합',
    introduction:
      '수포자들 들어와라 얼마나 길어져도 어덯게 표시될지 테스트하려는데 어디까지 하면 제대로 표시될까요롤레이',
    profileUrl: 'src/img/student.jpg',
    reliability: 100,
    tag: '수학'
  },
  {
    lectureId: 2,
    promotionTitle: '절찬리 제자 모집중',
    // promotionContent: '내가 수포자다 싶으면 들어오세요',
    tutorId: 1,
    tutorNickname: '투블럭의 여집합',
    introduction: '수포자들 들어와라',
    profileUrl: 'src/img/student.jpg',
    reliability: 50,
    tag: '수학'
  },
  {
    lectureId: 3,
    promotionTitle: '절찬리 제자 모집중',
    // promotionContent: '내가 수포자다 싶으면 들어오세요',
    tutorId: 1,
    tutorNickname: '투블럭의 여집합',
    introduction: '수포자들 들어와라',
    profileUrl: 'src/img/student.jpg',
    reliability: 75,
    tag: '수학'
  },
  {
    lectureId: 4,
    promotionTitle: '절찬리 제자 모집중',
    // promotionContent: '내가 수포자다 싶으면 들어오세요',
    tutorId: 1,
    tutorNickname: '투블럭의 여집합',
    introduction: '수포자들 들어와라',
    profileUrl: 'src/img/student.jpg',
    reliability: 80,
    tag: '수학'
  },
  {
    lectureId: 5,
    promotionTitle: '절찬리 제자 모집중',
    // promotionContent: '내가 수포자다 싶으면 들어오세요',
    tutorId: 1,
    tutorNickname: '투블럭의 여집합',
    introduction: '수포자들 들어와라',
    profileUrl: 'src/img/student.jpg',
    reliability: 83,
    tag: '수학'
  },
  {
    lectureId: 6,
    promotionTitle: '절찬리 제자 모집중',
    // promotionContent: '내가 수포자다 싶으면 들어오세요',
    tutorId: 1,
    tutorNickname: '투블럭의 여집합',
    introduction: '수포자들 들어와라',
    profileUrl: 'src/img/student.jpg',
    reliability: 25,
    tag: '수학'
  }
])
</script>

<style scoped>
.carousel__item {
  min-height: 200px;
  width: 100%;
  background-color: whitesmoke;
  color: var(--vc-clr-background-color);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel__slide {
  padding: 10px;
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  border: 5px solid white;
}
</style>
