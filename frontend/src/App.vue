<script setup lang="ts">
import NavBar from '@/components/NavBar.vue'
import { RouterView } from "vue-router";

</script>

<template>
  <NavBar />
  <Router-View id="main" />
</template>

<style scoped>
.test {
  height: 100%;
}
</style>
