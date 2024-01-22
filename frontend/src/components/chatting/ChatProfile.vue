<script setup lang="ts"></script>

<template>
  <div
    id="profile"
    class="h-[153px] overflow-hidden text-center text-white bg-[#ffffff] bg-[url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/timeline1.png')] bg-no-repeat"
  >
    <div id="close">
      <div class="cy"></div>
      <div class="cx"></div>
    </div>

    <p>Miro Badev</p>
    <span class="font-normal text-[11px]">miro@badev@gmail.com</span>
  </div>
</template>

<style scoped>
#profile .avatar {
  @apply w-[68px] mt-[23px] mb-0 mx-0 rounded-[50%] border-[3px] border-solid border-white;
}
#profile p {
  @apply font-semibold text-[15px] opacity-0 transition-all duration-200 ease-[cubic-bezier(0.000,0.995,0.990,1.000)] mt-[118px] -mb-px mx-0;
  -webkit-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -moz-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -ms-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -o-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
}
#profile p.animate {
  @apply opacity-100 transition-all duration-200 ease-[cubic-bezier(0.000,0.995,0.990,1.000)] mt-[97px];
  -webkit-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -moz-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -ms-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
  -o-transition: all 200ms cubic-bezier(0, 0.995, 0.99, 1);
}
</style>
