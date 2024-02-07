<script setup lang="ts"></script>

<template>
  <div class="message right">
    <div class="flex items-end">
      <div>
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/2_copy.jpg" />
      </div>
    </div>
    <div class="bubble">
      테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
      테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
      테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
      테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트 테스트
      <div class="corner"></div>
      <span>1 min</span>
    </div>
  </div>

  <div class="message">
    <div class="flex items-end">
      <div>
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/1_copy.jpg" />
      </div>
    </div>
    <div class="bubble">
      <div>
        아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주,
        좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소!
        아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주, 좋소! 아주,
        좋소! 아주, 좋소!
      </div>

      <div class="corner"></div>
      <span>Now</span>
    </div>
  </div>
</template>

<style scoped>
.message {
  @apply flex pr-0 w-full mb-10 mt-5;
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
  @apply text-[#aab8c2] text-[11px] absolute bottom-[-22px] right-0;
}
</style>
