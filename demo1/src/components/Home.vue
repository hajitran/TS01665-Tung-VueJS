<script>
import { ref } from 'vue';

export default {
  setup() {
    const count = ref(0);
    return { count };
  },
  methods: {
    goToAbout() {
      this.$router.push('/about');
    }
  }
}
</script>

<template>
<div>
<h1> day la trang gioi thieu</h1>
  </div>
  <button @click="goToAbout">Go to About Page   </button>    
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
