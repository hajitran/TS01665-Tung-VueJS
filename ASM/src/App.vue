<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const router = useRouter();
const route = useRoute();
const currentUser = ref(null);

const checkUser = () => {
  const user = localStorage.getItem('user');
  currentUser.value = user ? JSON.parse(user) : null;
};

const logout = () => {
  localStorage.removeItem('user');
  currentUser.value = null;
  router.push('/posts');
};

onMounted(() => {
  checkUser();
});

watch(() => route.path, () => {
  checkUser();
});
</script>

<template>
  <div>
    <nav class="navbar navbar-expand-sm navbar-light" style="background-color: rgb(217, 234, 240);">
      <div class="container">
        <router-link to="/posts" class="navbar-brand">
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <router-link to="/posts" class="nav-link"><i class="fa-solid fa-list"></i> Bài viết</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/posts/create" class="nav-link"><i class="fa-solid fa-film"></i> Tạo bài
                viết</router-link>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa-solid fa-circle-info"></i> Giới thiệu</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa-solid fa-calendar-days"></i> Sự kiện</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                <i class="fa-solid fa-user"></i> Tài khoản
              </a>
              <ul class="dropdown-menu">
                <li v-if="!currentUser">
                  <router-link to="/login" class="dropdown-item">Đăng Nhập</router-link>
                </li>
                <li v-if="!currentUser">
                  <router-link to="/register" class="dropdown-item">Đăng ký thành viên</router-link>
                </li>
                <li v-if="currentUser">
                  <router-link to="/profile" class="dropdown-item">Tài khoản của tôi</router-link>
                </li>
                <li v-if="currentUser">
                  <a class="dropdown-item" href="#" @click.prevent="logout">Đăng xuất</a>
                </li>
              </ul>
            </li>
          </ul>
          <ul class="navbar-nav d-flex ms-auto">
            <li class="nav-item" v-if="currentUser">
              <span class="nav-link">Xin chào, {{ currentUser.name }}</span>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <router-view />
  </div>
</template>

<style>
body {
  margin: 0;
  padding: 0;
  background-color: #f8f9fa;
}

.navbar-nav .nav-link.router-link-active {
  color: #0e0000 !important;
  font-weight: bold;
}
</style>