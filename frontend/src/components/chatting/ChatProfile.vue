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

/* 애니메이션 관련인 듯...? */
.cx,
.cy {
  @apply absolute w-0 h-[3px] transition-all duration-[250ms] ease-[ease-in-out] right-[15px] top-[15px];
  background: #fff;
  -webkit-transition: all 250ms ease-in-out;
  -moz-transition: all 250ms ease-in-out;
  -ms-transition: all 250ms ease-in-out;
  -o-transition: all 250ms ease-in-out;
}
.cx.s1,
.cy.s1 {
  @apply w-5 transition-all duration-100 ease-[ease-out] right-0;
  -webkit-transition: all 100ms ease-out;
  -moz-transition: all 100ms ease-out;
  -ms-transition: all 100ms ease-out;
  -o-transition: all 100ms ease-out;
}
.cy.s2 {
  @apply rotate-[50deg] transition-all duration-100 ease-[ease-out];
  -ms-transform: rotate(50deg);
  -webkit-transform: rotate(50deg);
  -webkit-transition: all 100ms ease-out;
  -moz-transition: all 100ms ease-out;
  -ms-transition: all 100ms ease-out;
  -o-transition: all 100ms ease-out;
}
.cy.s3 {
  @apply rotate-45 transition-all duration-100 ease-[ease-out];
  -ms-transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
  -webkit-transition: all 100ms ease-out;
  -moz-transition: all 100ms ease-out;
  -ms-transition: all 100ms ease-out;
  -o-transition: all 100ms ease-out;
}
.cx.s1 {
  @apply w-5 transition-all duration-100 ease-[ease-out] right-0;
  -webkit-transition: all 100ms ease-out;
  -moz-transition: all 100ms ease-out;
  -ms-transition: all 100ms ease-out;
  -o-transition: all 100ms ease-out;
}
.cx.s2 {
  @apply rotate-[140deg] transition-all duration-100 ease-[ease-out];
  -ms-transform: rotate(140deg);
  -webkit-transform: rotate(140deg);
  -webkit-transition: all 100ms ease-out;
  -moz-transition: all 100ms ease-out;
  -ms-transition: all 100ease-out;
  -o-transition: all 100ms ease-out;
}
.cx.s3 {
  @apply rotate-[135deg] transition-all duration-100 ease-[ease-out];
  -ms-transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
  -webkit-transition: all 100ease-out;
  -moz-transition: all 100ms ease-out;
  -ms-transition: all 100ms ease-out;
  -o-transition: all 100ms ease-out;
}
</style>
