<template>
    <div>
        <!-- Nếu người dùng chua đang nhap, hiển thị form đang nhap -->
        <LoginComponent v-if="!isLoggedIn" @loggedIn="handleLoginSuccess" />

        <!-- Nếu người dùng đã đăng nhap, hiển thị component bình luan -->
        <CommentComponent v-else :username="loggedInUser" />
    </div>
</template>

<script setup>
import { ref } from 'vue';
import LoginComponent from './LoginComponent.vue';
import CommentComponent from './CommentComponent.vue';

// Quản lý trạng thái đăng nhập và tên người dùng đã đăng nhập
const isLoggedIn = ref(false);
const loggedInUser = ref('');

// xử lý khi người dùng đăng nhập thành công
function handleLoginSuccess(username) {
    loggedInUser.value = username;
    isLoggedIn.value = true;
}
</script>