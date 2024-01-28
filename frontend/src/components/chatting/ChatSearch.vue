<script setup lang="ts"></script>

<template>
  <div id="search" class="h-[60px] w-[290px] absolute left-0 bottom-0 bg-[#e3e9ed]">
    <input
      type="text"
      class="text-sm font-[400px] text-[#8198ac] ml-[55px] mr-0 mt-[21px] mb-0 p-0 border-[none] bg-[#e3e9ed] font-family: 'Open Sans', 'sans-serif' focus:outline-none"
      placeholder="Search contacts..."
    />
  </div>
</template>

<style scoped>
#search {
  @apply h-[60px] w-[290px] absolute left-0 bottom-0;
  background: #e3e9ed url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/search.png') -11px 0 no-repeat;
}
</style>
