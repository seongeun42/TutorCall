<script setup lang="ts">
interface notifyDate{
    message: string
}
const props = defineProps<{data:notifyDate, show:boolean}>();

const emit = defineEmits<{
    'update:clicked': [show: boolean, mode:string]
}>();


function denyClicked():void{
    emit("update:clicked", false, "deny");
}

function acceptClicked(): void{
    emit("update:clicked", false, "accept");
}

</script>
<template>
    <div class="grid justify-items-stretch">
        <div role="alert" class="alert max-w-96 mt-8 mr-8 justify-self-end flex flex-col bg-white focus:ring-2 shadow-lg">
            <span class="flex flex-rows w-full">
                <svg xmlns="http://www.w3.org/2000/svg" fill="#faf573" viewBox="0 0 24 24" stroke-width="1.5" stroke="black" class="w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M14.857 17.082a23.848 23.848 0 0 0 5.454-1.31A8.967 8.967 0 0 1 18 9.75V9A6 6 0 0 0 6 9v.75a8.967 8.967 0 0 1-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 0 1-5.714 0m5.714 0a3 3 0 1 1-5.714 0M3.124 7.5A8.969 8.969 0 0 1 5.292 3m13.416 0a8.969 8.969 0 0 1 2.168 4.5" />
                </svg>
                {{ props.data.message }}</span>
            <p class="w-full">과목</p>
        <div class="">
            <button class="btn btn-sm" @click="denyClicked">Deny</button>
            <button class="btn btn-sm btn-primary" @click="acceptClicked">Accept</button>
        </div>
        </div>
    </div>
</template>
<style scoped>

</style>