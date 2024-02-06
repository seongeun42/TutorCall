<script setup lang="ts"></script>

<template>
  <div
    class="h-[70px] relative border-b-[#e7ebee] border-b border-solid hover:cursor-pointer hover:bg-[#f1f4f6]"
  >
    <div>
      <img
        src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/1_copy.jpg"
        class="w-10 float-left m-[15px] rounded-[50%]"
      />
    </div>
    <div>
      <div class="float-left w-[220px] pt-[15px] pb-0 px-0">
        <div>
          <strong class="font-semibold text-[15px] text-[#597a96]">Miro Badev</strong>
        </div>
        <div class="text-[13px] font-normal text-[#aab8c2]">mirobadev@gmail.com</div>
      </div>
    </div>
    <div
      class="status w-[9px] h-[9px] absolute rounded-[50%] right-[17px] top-[31px] bg-[#26c281]"
    ></div>
  </div>
</template>

<style scoped>
.status.away {
  @apply bg-[#ffce54];
}
.status.inactive {
  @apply bg-[#eaeef0];
}
</style>
