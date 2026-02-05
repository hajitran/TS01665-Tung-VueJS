<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const name = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const errorMessage = ref('');

const handleRegister = () => {
  errorMessage.value = '';

  if (!name.value.trim()) {
    errorMessage.value = 'Vui lòng nhập họ tên';
    return;
  }

  if (!email.value.trim()) {
    errorMessage.value = 'Vui lòng nhập email';
    return;
  }

  if (password.value !== confirmPassword.value) {
    errorMessage.value = 'Mật khẩu không khớp';
    return;
  }

  if (password.value.length < 6) {
    errorMessage.value = 'Mật khẩu phải có ít nhất 6 ký tự';
    return;
  }

  let users = [];
  const existingUsers = localStorage.getItem('users');
  if (existingUsers) {
    users = JSON.parse(existingUsers);
  }

  if (users.some(u => u.email === email.value)) {
    errorMessage.value = 'Email này đã được đăng ký';
    return;
  }

  try {
    const newId = users.length > 0 ? Math.max(...users.map(u => u.id)) + 1 : 1;

    const newUser = {
      id: newId,
      name: name.value,
      email: email.value,
      password: password.value,
      profilePicture: null
    };

    users.push(newUser);
    localStorage.setItem('users', JSON.stringify(users));

    alert('Đăng ký thành công!');
    router.push('/login');
  } catch (error) {
    errorMessage.value = 'Đăng ký thất bại';
  }
};
</script>

<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">
            <h2 class="card-title text-center mb-4">Đăng ký tài khoản</h2>
            
            <div v-if="errorMessage" class="alert alert-danger">
              {{ errorMessage }}
            </div>
            
            <form @submit.prevent="handleRegister">
              <div class="mb-3">
                <label class="form-label">Họ tên</label>
                <input v-model="name" type="text" class="form-control" required>
              </div>
              
              <div class="mb-3">
                <label class="form-label">Email</label>
                <input v-model="email" type="email" class="form-control" required>
              </div>
              
              <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input v-model="password" type="password" class="form-control" required>
              </div>
              
              <div class="mb-3">
                <label class="form-label">Xác nhận mật khẩu</label>
                <input v-model="confirmPassword" type="password" class="form-control" required>
              </div>
              
              <button type="submit" class="btn btn-primary w-100">Đăng ký</button>
            </form>
            
            <p class="text-center mt-3">
              Đã có tài khoản? <router-link to="/login">Đăng nhập</router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>