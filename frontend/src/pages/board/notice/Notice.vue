<template>
  <div class="container mx-auto my-10">
    <div class="mx-10 my-5">
      <MenuBar />
      <!-- <NoticeArticle /> -->
      <!-- <DetailNotice /> -->
      <FAQArticle />
    </div>
  </div>
</template>

<script setup lang="ts">
import NoticeArticle from './NoticeArticle.vue'
import FAQArticle from './FAQArticle.vue'
import DetailNotice from './DetailNotice.vue'
import MenuBar from './MenuBar.vue'
</script>

<style scoped></style>
