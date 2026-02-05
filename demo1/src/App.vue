<script>
export default {
  methods: {
    goToAbout() {
      this.$router.push('/about');
    }
  }
}
</script>

<template>
<h3>Day la trang chu</h3>
<nav>
  <routerlink to="/">Home</routerlink> |
  <routerlink to="/about">About</routerlink>
</nav>
  <main>
    <RouterView/>
  </main>
</template>

<style scoped>
</style>
