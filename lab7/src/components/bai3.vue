<template>
    <div v-if="!isLoggedIn" class="p-5 col-sm-4">
        <h3>Form Đăng Ký</h3>
        <form @submit.prevent="register">
            <div class="mb-3 mt-3">
                <label>Email:</label>
                <input type="email" class="form-control" v-model="email" placeholder="Nhập email">
                <p v-if="emailError" style="color: red;">{{ emailError }}</p>
            </div>
            <div class="mb-3">
                <label>Mật khẩu:</label>
                <input type="password" class="form-control" v-model="password" placeholder="Nhập mật khẩu">
                <p v-if="passwordError" style="color: red;">{{ passwordError }}</p>
            </div>
            <div class="mb-3">
                <label>Ngày sinh</label>
                <input type="date" class="form-control" v-model="birthDate">
            </div>
            <div class="mb-3">
                <label>Giới tính</label>
                <input type="radio" value="Nam" v-model="gender"> Nam
                <input type="radio" value="Nữ" v-model="gender"> Nữ
                <input type="radio" value="Khác" v-model="gender"> Khác
            </div>
            <div class="mb-3">
                <label>Ngôn ngữ</label>
                <input type="checkbox" value="Tiếng Việt" v-model="languages"> Tiếng Việt
                <input type="checkbox" value="Tiếng Anh" v-model="languages"> Tiếng Anh
                <input type="checkbox" value="Tiếng Nhật" v-model="languages"> Tiếng Nhật
            </div>
            <button type="submit" class="btn btn-primary">Đăng ký</button>
        </form>
    </div>
    <div v-else class="p-5 col-sm-5">
        <h1>Thông tin đã đăng ký</h1>
        <h3>Email: {{ email }}</h3>
        <h3>Ngày sinh: {{ birthDate }}</h3>
        <h3>Giới tính: {{ gender }}</h3>
        <h3>Ngôn ngữ: {{ languages.join(', ') }}</h3>
        <button @click="logout" class="btn btn-primary">Đăng xuất</button>
    </div>
</template>
<script setup>
import { ref } from 'vue';
const isLoggedIn = ref(false);
const email = ref('');
const password = ref('');
const birthDate = ref('');
const gender = ref('');
const languages = ref([]);
const emailError = ref('');
const passwordError = ref('');
const birthDateError = ref('');
const genderError = ref('');
const languagesError = ref('');
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const register = () => {
    // Reset thông điệp lỗi
    emailError.value = '';
    passwordError.value = '';
    // Validate email
    if (!email.value) {
        emailError.value = 'Email là bắt buộc.';
    } else if (!emailRegex.test(email.value)) {
        emailError.value = 'Vui lòng nhập email hợp lệ.';
    }
    // Validate mật khẩu
    if (!password.value) {
        passwordError.value = 'Mật khẩu là bắt buộc.';
    }
    if (!birthDate.value) {
        birthDateError.value = 'Ngày sinh là bắt buộc.';
        
    }
    if (!gender.value) {
        genderError.value = 'Giới tính là bắt buộc.';
        
    }
    if (languages.value.length === 0) {
        languagesError.value = 'Vui lòng chọn ít nhất một ngôn ngữ.';
    }
    // Nếu không có lỗi, xử lý đăng ký
    if (!emailError.value && !passwordError.value) {
        isLoggedIn.value = true;
    }
}
const logout = () => {
    isLoggedIn.value = false;
    email.value = '';
    password.value = '';
    emailError.value = '';
    passwordError.value = '';
    birthDate.value = '';
    gender.value = '';
    languages.value = [];
}
</script>