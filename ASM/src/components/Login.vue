<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import img1 from '../assets/images/orange.jpg';

const router = useRouter();
const email = ref('');
const password = ref('');
const errorMessage = ref('');

const defaultUsers = [
  {
    "id": 1,
    "email": "test@gmail.com",
    "password": "123456",
    "name": "Tran Thanh Tung",
    "profilePicture": img1
  },
  {
    "id": 2,
    "email": "admin@gmail.com",
    "password": "admin123",
    "name": "Admin",
    "role": "admin",
    "profilePicture": img1
  }
];

const initializeUsers = () => {
  const existingUsers = localStorage.getItem('users');
  if (!existingUsers) {
    localStorage.setItem('users', JSON.stringify(defaultUsers));
  }
};

const handleLogin = () => {
  errorMessage.value = '';

  const users = JSON.parse(localStorage.getItem('users') || '[]');
  const user = users.find(u => u.email === email.value && u.password === password.value);

  if (user) {
    localStorage.setItem('user', JSON.stringify(user));
    router.push('/posts');
  } else {
    errorMessage.value = 'Email hoặc mật khẩu không đúng';
  }
};

onMounted(() => {
  initializeUsers();
});
</script>

<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-5">
        <div class="card">
          <div class="card-body">
            <h2 class="card-title text-center mb-4">Đăng nhập</h2>

            <div v-if="errorMessage" class="alert alert-danger">
              {{ errorMessage }}
            </div>

            <form @submit.prevent="handleLogin">
              <div class="mb-3">
                <label class="form-label">Email</label>
                <input v-model="email" type="email" class="form-control" required>
              </div>

              <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input v-model="password" type="password" class="form-control" required>
              </div>

              <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
            </form>

            <p class="text-center mt-3">
              Chưa có tài khoản? <router-link to="/register">Đăng ký</router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>