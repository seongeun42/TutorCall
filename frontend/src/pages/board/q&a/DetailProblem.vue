<script setup lang="ts">
const props = defineProps<{ content: string | undefined }>()
</script>
<template>
  <div class="bg-slate-100 rounded-xl">
    <div class="flex my-10 p-7 justify-center items-center h-full">
      <p class="font-medium text-xl leading-10">
        {{ props.content }}
      </p>
    </div>
  </div>
</template>
<style scoped></style>
