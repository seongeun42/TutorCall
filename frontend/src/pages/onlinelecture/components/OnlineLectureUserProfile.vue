<script setup lang="ts">

interface userInfo { 
    imgUrl: string,
    nickName: string,
    isHost: boolean,
    mikeStatus: boolean
}

const props = defineProps<{info:userInfo}>();

</script>
<template>
    <div class="grid grid-cols-8 mx-3 my-2">
        <div class="col-span-1 justify-self-center">
            <div class="w-full h-full">
                <img src="@/img/pika.jpg" alt="" class="object-cover rounded-full border">
            </div>
        </div>
        <div class="col-span-5 justify-self-start text-center">
            <p class="flex justify-center items-center h-full mx-2 font-semibold">{{ info.nickName }}</p>
        </div>
        <div class="col-span-1 flex items-center justify-center" v-if ="info.isHost">
            <img src="@/img/host.jpg" alt="">
        </div>
        <div class="col-span-1 flex items-center justify-center" v-if ="!info.mikeStatus">
            <img src="@/img/nomike.jpg" alt="">
        </div>
    </div>
</template>
<style scoped>
</style>