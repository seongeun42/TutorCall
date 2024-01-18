<script setup lang="ts">
const isMychat = defineProps<{
    isMychat: boolean;
    message: string;
}>();
</script>
<template>
        <div v-if="!isMychat.isMychat">
            <div class="chat chat-start mx-2 my-1">
            <div class="chat-image avatar">
                <div class="w-10 rounded-full">
                <img alt="Tailwind CSS chat bubble component" src="https://daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg" />
                </div>
            </div>
            <div class="chat-header">
                Obi-Wan Kenobi
            </div>
            <div class="chat-bubble">{{ isMychat.message }}</div>
            </div>
        </div>
        <div v-if="isMychat.isMychat">
            <div class="chat chat-end mx-2 my-1">
                <div class="chat-image avatar">
                    <div class="w-10 rounded-full">
                        <img alt="Tailwind CSS chat bubble component" src="https://daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg" />
                    </div>
                </div>
                <div class="chat-header">
                    Anakin
                </div>
                <div class="chat-bubble">{{ isMychat.message }}</div>
            </div>     
        </div>
</template>
<style scoped>
</style>