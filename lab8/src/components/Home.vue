<template>
  <div class="home">
    <h1>Chào mừng đến với Lab8</h1>
    <nav>
      <router-link to="/blogs">Danh sách bài viết</router-link> |
      <router-link to="/profile">Thông tin người dùng</router-link> |
      <router-link to="/dashboard">Dashboard</router-link>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style scoped>
.home {
  text-align: center;
  padding: 40px;
}
nav {
  margin-top: 20px;
}
nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #e74c3c;
}
</style>