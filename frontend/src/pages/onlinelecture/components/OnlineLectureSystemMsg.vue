<script setup lang="ts">
const sysMsg = defineProps<{
    message: string;
}>();
</script>
<template>
    <div class="m-4 h-10 bg-gray-100 rounded-lg text-center">
        <p class="flex justify-center items-center h-full">{{ sysMsg.message }}</p>
    </div>
</template>
<style scoped>
</style>