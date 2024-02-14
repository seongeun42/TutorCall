<script setup lang="ts">
import type { ComputedRef } from 'vue';
import type { Ref } from 'vue';
import { computed } from 'vue';
import { ref } from 'vue';
import { instance } from '@/axios/axiosConfig'
import { isAxiosError, type AxiosResponse } from 'axios';
import type { reviewResponse, errorResponse, review } from '@/interface/common/interface'


interface ReviewForm{
    mannerRate: string,
    communicationRate: string,
    professionalismRate: string,
    content: string
}

const communicationRate:Ref<string>=ref("1");
const mannerRate:Ref<string> = ref("1");
const professionalismRate:Ref<string> = ref("1");

const means:ComputedRef<string> = computed(()=>{
    return Math.floor((2*(Number(communicationRate.value)+Number(mannerRate.value)+Number(professionalismRate.value))/3)).toString();
})

const rmeans:ComputedRef<string> = computed(()=>{
    return ((Number(communicationRate.value)+Number(mannerRate.value)+Number(professionalismRate.value))/3).toFixed(1);
})

const content:Ref<string> = ref("");

const emit = defineEmits<{
  change: [value:string]
  update: [value: review]
}>()

const props = defineProps<{"mode":string, id:number}>();

function closeReviewpage(event: Event):void{
    event.preventDefault();
    emit("change", "close");
}

async function regiestReview(event: Event):Promise<void>{

    event.preventDefault();

    let url: string = import.meta.env.VITE_VUE_API_URL+"/review/"
    if(props.mode.includes("tutorcall")) {
        url+=`tutorcall/${props.id}`;
    }else{
        url+=`lecture/${props.id}`;
    }

    const param:ReviewForm = {
        mannerRate: mannerRate.value,
        communicationRate: communicationRate.value,
        professionalismRate: professionalismRate.value,
        content: content.value
    }

    await instance.post(url, param)
    .then((response: AxiosResponse<reviewResponse>)=>{
        alert(response.data.message);
        const Review:review = {
                mannerRate: Number(mannerRate.value),
                communicationRate: Number(communicationRate.value),
                professionalismRate: Number(professionalismRate.value),
                content: content.value,
                createAt: ''
        }
        emit("update", Review);
    })
    .catch((error:unknown)=>{
        if(isAxiosError<errorResponse>(error)) alert(error.response?.data.message);
    })
}

</script>
<template>
    <div class="min-w-80 min-h-80 flex justify-center items-center">
        <div class="flex flex-col gap-4 mt-8 mb-8 mr-4">
            <div class="flex flex-col items-center mb-8">
                <div class="flex items-center mb-4">
                    <p class="text-2xl text-center font-semibold">평가</p>
                </div>
                <div class="grid grid-cols-4 flex items-center justify-center">
                    <div class="rating rating-lg rating-half col-start-2 col-end-4">
                        <input type="radio" name="rating-mean" v-model="means" value="0" class="rating-hidden" />
                        <input type="radio" name="rating-mean" v-model="means" value="1" class="bg-green-500 mask mask-star-2 mask-half-1" />
                        <input type="radio" name="rating-mean" v-model="means" value="2" class="bg-green-500 mask mask-star-2 mask-half-2" />
                        <input type="radio" name="rating-mean" v-model="means" value="3" class="bg-green-500 mask mask-star-2 mask-half-1" />
                        <input type="radio" name="rating-mean" v-model="means" value="4" class="bg-green-500 mask mask-star-2 mask-half-2" />
                        <input type="radio" name="rating-mean" v-model="means" value="5" class="bg-green-500 mask mask-star-2 mask-half-1" />
                        <input type="radio" name="rating-mean" v-model="means" value="6" class="bg-green-500 mask mask-star-2 mask-half-2" />
                        <input type="radio" name="rating-mean" v-model="means" value="7" class="bg-green-500 mask mask-star-2 mask-half-1" />
                        <input type="radio" name="rating-mean" v-model="means" value="8" class="bg-green-500 mask mask-star-2 mask-half-2" />
                        <input type="radio" name="rating-mean" v-model="means" value="9" class="bg-green-500 mask mask-star-2 mask-half-1" />
                        <input type="radio" name="rating-mean" v-model="means" value="10" class="bg-green-500 mask mask-star-2 mask-half-2" />
                        </div>
                    <p class="text-2xl text-center">{{ rmeans }}</p>
                </div>
            </div>
            <div class="grid grid-cols-2">
                <div class="col-span-1 mr-8 flex items-center justify-center">
                    <p class="text-2xl text-center font-semibold">전달력</p>
                </div>
                <div class="rating justify-center">
                    <input type="radio" name="rating-comm" class="mask mask-star-2 bg-orange-400 size-9" value="1" v-model="communicationRate" />
                    <input type="radio" name="rating-comm" class="mask mask-star-2 bg-orange-400 size-9" value="2" v-model="communicationRate" />
                    <input type="radio" name="rating-comm" class="mask mask-star-2 bg-orange-400 size-9" value="3" v-model="communicationRate" />
                    <input type="radio" name="rating-comm" class="mask mask-star-2 bg-orange-400 size-9" value="4" v-model="communicationRate" />
                    <input type="radio" name="rating-comm" class="mask mask-star-2 bg-orange-400 size-9" value="5" v-model="communicationRate" />
                </div>
            </div>
            <div class="grid grid-cols-2">
                <div class="col-span-1 mr-8 flex items-center justify-center">
                    <p class="text-2xl text-center font-semibold">매너</p>
                </div>
                <div class="rating justify-center">
                    <input type="radio" name="rating-manner" class="mask mask-star-2 bg-orange-400 size-9" value="1" v-model="mannerRate" />
                    <input type="radio" name="rating-manner" class="mask mask-star-2 bg-orange-400 size-9" value="2" v-model="mannerRate" />
                    <input type="radio" name="rating-manner" class="mask mask-star-2 bg-orange-400 size-9" value="3" v-model="mannerRate" />
                    <input type="radio" name="rating-manner" class="mask mask-star-2 bg-orange-400 size-9" value="4" v-model="mannerRate" />
                    <input type="radio" name="rating-manner" class="mask mask-star-2 bg-orange-400 size-9" value="5" v-model="mannerRate" />
                </div>
            </div>
            <div class="grid grid-cols-2">
                <div class="col-span-1 mr-8 flex items-center justify-center">
                    <p class="text-2xl text-center font-semibold">전문성</p>
                </div>
                <div class="rating justify-center">
                    <input type="radio" name="rating-prof" class="mask mask-star-2 bg-orange-400 size-9" value="1" v-model="professionalismRate" />
                    <input type="radio" name="rating-prof" class="mask mask-star-2 bg-orange-400 size-9" value="2" v-model="professionalismRate" />
                    <input type="radio" name="rating-prof" class="mask mask-star-2 bg-orange-400 size-9" value="3" v-model="professionalismRate" />
                    <input type="radio" name="rating-prof" class="mask mask-star-2 bg-orange-400 size-9" value="4" v-model="professionalismRate" />
                    <input type="radio" name="rating-prof" class="mask mask-star-2 bg-orange-400 size-9" value="5" v-model="professionalismRate" />
                </div>
            </div>
            <div class="mt-6">
                <textarea class="textarea textarea-bordered w-11/12 mx-8"
                placeholder="평가를 작성해 주세요"
                v-model="content"></textarea>
            </div>
            <div class="flex flex-row flex justify-around mt-6">
                <button class="btn btn-info" @click="regiestReview($event)">제출하기</button>
                <button class="btn btn-error" @click="closeReviewpage($event)">나중에 하기</button>
            </div>
        </div>
    </div>
</template>
<style scoped>

</style>