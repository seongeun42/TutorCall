<script setup lang="ts">
import { defineProps } from 'vue';

const props = defineProps<{
  profile: String,
  message: String,
}>()
</script>

<template>
  <div class="message">
    <div class="flex items-end">
      <div>
        <img :src="props.profile" />
      </div>
    </div>
    <div class="bubble">
      <div>
        {{props.message}}
      </div>
      <div class="corner"></div>
    </div>
  </div>
</template>

<style scoped>
.message {
  @apply flex pr-0 w-full;
}
.message.right {
  @apply flex-row-reverse mr-[-19px] ml-[19px] pl-0 pt-0;
}
.message img {
  @apply w-[30px] h-[30px] rounded-[50%] m-3;
}
.message .bubble {
  @apply text-[13px] font-semibold text-[#8495a3] relative float-left px-2 py-1 rounded-[5px_5px_5px_0px] bg-[#f0f4f7] flex-1;
}
.bubble .corner {
  @apply absolute w-[7px] h-[7px] left-[-5px] bottom-0;
}
.right .corner {
  @apply right-[-5px] left-auto;
}
span {
  @apply text-[#aab8c2] text-[10px] absolute bottom-[-22px] right-0;
}
</style>
