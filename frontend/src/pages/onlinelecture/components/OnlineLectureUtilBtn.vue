<script setup lang="ts">

import { defineProps, ref } from 'vue';
import type { Ref } from 'vue';

interface btnProps { 
    btnName1: string,
    btnName2: string
}

const props = defineProps<btnProps>();

const btnOneClicked: Ref<boolean> = ref(true);
const btnTwoClicked: Ref<boolean> = ref(false);

const emit = defineEmits<{
    'update:btnClicked': [boolean];
}>();

function btnOneClickedEvent (): void {
    btnOneClicked.value = true;
    btnTwoClicked.value = false;
    emit('update:btnClicked', btnOneClicked.value);
}

function btnTwoClickedEvent (): void {
    btnTwoClicked.value = true;
    btnOneClicked.value = false;
    emit('update:btnClicked', btnOneClicked.value);
}

</script>
<template>
    <div class="grid justify-items-stretch grid-cols-2 bg-gray-200 rounded-lg mt-4 mb-2 h-10">
        <div class="justify-self-center w-full mx-2 py-1">
            <p class="mx-2 flex justify-center items-center h-full rounded-lg text-center text-base font-semibold" :class="{
            'bg-blue-500 hover:bg-blue-700 text-white': btnOneClicked,
            'bg-gray-200': !btnOneClicked
        }" @click = "btnOneClickedEvent">{{ props.btnName1 }}</p>
        </div>
        <div class="justify-self-center w-full mx-2 py-1">
            <p class="mx-2 flex justify-center items-center h-full rounded-lg text-center text-base font-semibold" :class="{
            'bg-blue-500 hover:bg-blue-700 text-white': btnTwoClicked,
            'bg-gray-200': !btnTwoClicked
        }" @click = "btnTwoClickedEvent">{{ props.btnName2 }}</p>
        </div>
    </div>
</template>
<style scoped>
</style>