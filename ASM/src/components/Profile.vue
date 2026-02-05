<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const currentUser = ref(null);
const name = ref('');
const email = ref('');
const profilePicture = ref(null);
const currentPassword = ref('');
const newPassword = ref('');
const confirmPassword = ref('');
const errorMessage = ref('');
const successMessage = ref('');
const isLoggedIn = ref(false);
const myPosts = ref([]);

const loadUserData = () => {
    const user = localStorage.getItem('user');
    if (!user) {
        isLoggedIn.value = false;
        return;
    }
    isLoggedIn.value = true;
    currentUser.value = JSON.parse(user);
    name.value = currentUser.value.name;
    email.value = currentUser.value.email;
    profilePicture.value = currentUser.value.profilePicture || null;
    loadMyPosts();
};

const loadMyPosts = () => {
    if (window.newPosts && window.newPosts.length > 0) {
        myPosts.value = window.newPosts.filter(post => post.userId === currentUser.value.id);
    }
};

const updateProfile = () => {
    errorMessage.value = '';
    successMessage.value = '';

    try {
        const updatedData = {
            ...currentUser.value,
            name: name.value,
            email: email.value
        };

        localStorage.setItem('user', JSON.stringify(updatedData));
        currentUser.value = updatedData;
        successMessage.value = 'Cập nhật thông tin thành công!';
    } catch (error) {
        errorMessage.value = 'Cập nhật thất bại!';
    }
};

const changePassword = () => {
    errorMessage.value = '';
    successMessage.value = '';

    if (currentPassword.value !== currentUser.value.password) {
        errorMessage.value = 'Mật khẩu hiện tại không đúng!';
        return;
    }

    if (newPassword.value !== confirmPassword.value) {
        errorMessage.value = 'Mật khẩu mới không khớp!';
        return;
    }

    if (newPassword.value.length < 6) {
        errorMessage.value = 'Mật khẩu mới phải có ít nhất 6 ký tự!';
        return;
    }

    try {
        const updatedData = {
            ...currentUser.value,
            password: newPassword.value
        };

        localStorage.setItem('user', JSON.stringify(updatedData));
        currentUser.value = updatedData;

        currentPassword.value = '';
        newPassword.value = '';
        confirmPassword.value = '';

        successMessage.value = 'Đổi mật khẩu thành công!';
    } catch (error) {
        errorMessage.value = 'Đổi mật khẩu thất bại!';
    }
};

const deletePost = (id) => {
    if (confirm('Bạn có chắc muốn xóa bài viết này?')) {
        myPosts.value = myPosts.value.filter(post => post.id !== id);
        if (window.newPosts) {
            window.newPosts = window.newPosts.filter(post => post.id !== id);
        }
    }
};

const viewPost = (id) => {
    router.push(`/posts/${id}`);
};

const handleImageUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            profilePicture.value = e.target.result;
            const updatedData = {
                ...currentUser.value,
                profilePicture: e.target.result
            };
            localStorage.setItem('user', JSON.stringify(updatedData));
            currentUser.value = updatedData;
            successMessage.value = 'Cập nhật ảnh đại diện thành công!';
        };
        reader.readAsDataURL(file);
    }
};

onMounted(() => {
    loadUserData();
});
</script>

<template>
    <div class="container mt-4">
        <div v-if="!isLoggedIn" class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body text-center py-5">
                        <h3 class="mb-4">Truy cập tài khoản</h3>
                        <p class="mb-4">Bạn cần đăng nhập để xem và quản lý thông tin tài khoản</p>
                        <div class="d-flex gap-3 justify-content-center">
                            <router-link to="/login" class="btn btn-primary btn-lg">Đăng nhập</router-link>
                            <router-link to="/register" class="btn btn-outline-primary btn-lg">Đăng ký</router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div v-else>
            <h2 class="mb-4">Quản lý tài khoản</h2>

            <div class="row">
                <!-- Thông tin cá nhân -->
                <div class="col-md-6 mb-4">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h4 class="mb-0">Thông tin cá nhân</h4>
                        </div>
                        <div class="card-body">
                            <div v-if="errorMessage" class="alert alert-danger">
                                {{ errorMessage }}
                            </div>

                            <form @submit.prevent="updateProfile">
                                <div class="mb-3">
                                    <label class="form-label">Họ tên</label>
                                    <input v-model="name" type="text" class="form-control" required>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Email</label>
                                    <input v-model="email" type="email" class="form-control" required>
                                </div>

                                <button type="submit" class="btn btn-primary">Cập nhật thông tin</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 mb-4">
                    <div class="card">
                        <div class="card-header bg-warning">
                            <h4 class="mb-0">Đổi mật khẩu</h4>
                        </div>
                        <div class="card-body">
                            <div v-if="errorMessage" class="alert alert-danger">
                                {{ errorMessage }}
                            </div>

                            <form @submit.prevent="changePassword">
                                <div class="mb-3">
                                    <label class="form-label">Mật khẩu hiện tại</label>
                                    <input v-model="currentPassword" type="password" class="form-control" required>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Mật khẩu mới</label>
                                    <input v-model="newPassword" type="password" class="form-control" required>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Xác nhận mật khẩu mới</label>
                                    <input v-model="confirmPassword" type="password" class="form-control" required>
                                </div>

                                <button type="submit" class="btn btn-warning">Đổi mật khẩu</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 mb-4">
                    <div class="card">
                        <div class="card-header bg-success text-white">
                            <h4 class="mb-0">Ảnh đại diện</h4>
                        </div>
                        <div class="card-body text-center">
                            <div v-if="errorMessage" class="alert alert-danger">
                                {{ errorMessage }}
                            </div>
                            <div v-if="successMessage" class="alert alert-success">
                                {{ successMessage }}
                            </div>
                            <div v-if="profilePicture" class="mb-3">
                                <img :src="profilePicture" alt="Ảnh đại diện" class="img-fluid rounded-circle mb-3"
                                    style="max-width: 150px;">
                            </div>
                            <div v-else class="mb-3">
                                <p class="text-muted">Chưa có ảnh đại diện</p>
                            </div>
                            <input type="file" accept="image/*" @change="handleImageUpload" class="form-control mb-3">
                            <button type="button" class="btn btn-success">Cập nhật ảnh đại diện</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Bài viết của tôi -->
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header bg-info text-white">
                            <h4 class="mb-0">Bài viết của tôi</h4>
                        </div>
                        <div class="card-body">
                            <div v-if="myPosts.length === 0" class="alert alert-light">
                                Bạn chưa có bài viết nào
                            </div>

                            <div class="table-responsive" v-else>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Tiêu đề</th>
                                            <th>Ngày đăng</th>
                                            <th>Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr v-for="post in myPosts" :key="post.id">
                                            <td>{{ post.title }}</td>
                                            <td>{{ new Date(post.createdAt).toLocaleDateString() }}</td>
                                            <td>
                                                <button @click="viewPost(post.id)" class="btn btn-sm btn-info me-2">
                                                    Xem
                                                </button>
                                                <button @click="deletePost(post.id)" class="btn btn-sm btn-danger">
                                                    Xóa
                                                </button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>