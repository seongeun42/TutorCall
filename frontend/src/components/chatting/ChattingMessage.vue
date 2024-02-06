<script setup lang="ts"></script>

<template>
  <div class="message right">
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/2_copy.jpg" />
    <div class="bubble">
      Can you share a link for the tutorial?
      <div class="corner"></div>
      <span>1 min</span>
    </div>
  </div>

  <div class="message">
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/1_copy.jpg" />
    <div class="bubble">
      Yeah, hold on
      <div class="corner"></div>
      <span>Now</span>
    </div>
  </div>
</template>

<style scoped>
.animate {
  @apply opacity-100 mt-0;
}
.message {
  @apply clear-both mb-[45px] pl-[58px] pr-0 pt-0 pb-[30px];
}
.message.right {
  @apply mr-[-19px] ml-[19px] pl-0 pr-[58px] pt-0 pb-[30px];
}
.message img {
  @apply float-left ml-[-38px] w-[30px] mt-3 rounded-[50%];
}
.message.right img {
  @apply float-right mr-[-38px] ml-0;
}
.message .bubble {
  @apply text-[13px] font-semibold text-[#8495a3] relative float-left px-[13px] py-3 rounded-[5px_5px_5px_0px] bg-[#f0f4f7];
}
.message.right .bubble {
  @apply float-right rounded-[5px_5px_0px_5px];
}
.bubble .corner {
  @apply absolute w-[7px] h-[7px] left-[-5px] bottom-0;
  background: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/bubble-corner.png') 0 0
    no-repeat;
}
.right .corner {
  @apply right-[-5px] left-auto;
  background: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/bubble-cornerR.png') 0 0
    no-repeat;
}
span {
  @apply text-[#aab8c2] text-[11px] absolute bottom-[-22px] right-0;
}
</style>
