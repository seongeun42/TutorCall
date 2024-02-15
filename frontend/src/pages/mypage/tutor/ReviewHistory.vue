<script setup lang="ts">
import type { review } from '@/interface/common/interface';
import StarScore from './StarScore.vue'
import { useUserStore } from '@/store/userStore';
import { onMounted } from 'vue';
import type { TutorReview } from '@/interface/mypage/interface';
import { ref, type Ref} from 'vue';

const userStore = useUserStore();
const props = defineProps<{data: any}>();
const score:number = Math.round((props.data.communicationRate+props.data.mannerRate+props.data.professionalismRate)/3)
function isReviewType(data: any): data is TutorReview{
  return data.reviewer == undefined;
}
</script>
<template>
  <div v-if="isReviewType(props.data)" class="p-4 m-4" id="student">
    <div class="flex pt-2 w-full justify-center">
      <div class="flex-row">
        <div class="flex flex-col">
          <div class="flex flex-row justify-between gap-3">
            <div class="flex flex-row">
              <div>
                <img :src="userStore.$state.profile" class="w-14 h-14 rounded-full ml-4" alt="" />
              </div>
              <div>
                <p class="ml-5 font-semibold">{{ userStore.$state.nickname }}</p>
                <div class="flex ml-5">
                  <div>{{ props.data.createAt }}</div>
                </div>
              </div>
            </div>
            <div class="flex justify-center items-center">
              <p class="mr-5 ml-5 font-semibold text-lg">평점</p>
              <StarScore :score="score" />
            </div>
          </div>
          <div>
            <div class="flex pt-5 pb-5 ml-4">
              <div class="flex justify-center h-full flex-col">
                <p class="font-semibold text-lg">리뷰 내용</p>
                <p class="text-lg">
                  {{ props.data.content }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div v-else class="p-4 m-4 shadow-md" id="tutorpage">
    <div class="flex pt-2 w-full justify-center">
      <div class="flex-row w-10/12">
        <div class="flex flex-col">
          <div class="flex flex-row justify-between gap-3">
            <div class="flex flex-row">
              <div>
                <img :src="props.data.reviewer.profile" class="w-14 h-14 rounded-full ml-4" alt="" />
              </div>
              <div>
                <p class="ml-5 font-semibold">{{ props.data.reviewer.nickname }}</p>
                <div class="flex ml-5">
                  <div>{{ props.data.createdAt.split(".")[0].replace("T", " ") }}</div>
                </div>
              </div>
            </div>
            <div class="flex justify-center items-center">
              <p class="mr-5 ml-5 font-semibold text-lg">평점</p>
              <StarScore :score="score" />
            </div>
          </div>
          <div>
            <div class="flex pt-5 pb-5 ml-4">
              <div class="flex justify-center h-full flex-col">
                <p class="font-semibold text-lg">리뷰 내용</p>
                <p class="text-lg">
                  {{ props.data.content }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>
<style scoped>
</style>
