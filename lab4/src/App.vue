<script setup>
import HelloWorld from './components/HelloWorld.vue'
import Hello from './components/Hello.vue';
import bai3 from './components/bai3.vue';
import bai4 from './components/bai4.vue';
</script>

<template>
  <Hello></Hello>
  <bai3></bai3>
  <bai4></bai4>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
