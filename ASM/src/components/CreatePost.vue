<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const title = ref('');
const content = ref('');
const category = ref('');
const currentUser = ref(null);
const errorMessage = ref('');
const imagePreview = ref('');
const imageFile = ref(null);

let nextId = 4;

const handleImageUpload = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      imagePreview.value = e.target.result;
      imageFile.value = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

const handleSubmit = async () => {
  errorMessage.value = '';
  
  if (!currentUser.value) {
    alert('Vui lòng đăng nhập để đăng bài!');
    router.push('/login');
    return;
  }
  
  if (!title.value || !content.value) {
    alert('Vui lòng điền đầy đủ thông tin');
    return;
  }
  
  if (!window.newPosts) {
    window.newPosts = [];
  }
  
  const maxId = Math.max(3, ...window.newPosts.map(p => p.id || 0));
  
  const postData = {
    id: maxId + 1,
    title: title.value,
    content: content.value,
    category: category.value,
    author: currentUser.value.name,
    userId: currentUser.value.id,
    image: imageFile.value,
    createdAt: new Date().toISOString()
  };
  
  try {
    window.newPosts.push(postData);
    
    alert('Đăng bài thành công!');
    title.value = '';
    content.value = '';
    category.value = '';
    imagePreview.value = '';
    imageFile.value = null;
    router.push('/posts');
  } catch (error) {
    console.error('Lỗi khi lưu bài viết:', error);
    errorMessage.value = `Lỗi: ${error.message}`;
  }
};

onMounted(() => {
  const user = localStorage.getItem('user');
  if (user) {
    currentUser.value = JSON.parse(user);
  }
});
</script>

<template>
  <div class="container mt-4">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <h2 class="card-title mb-4">Tạo bài viết mới</h2>
            
            <div v-if="errorMessage" class="alert alert-danger">
              {{ errorMessage }}
            </div>
            
            <div v-if="!currentUser" class="alert alert-warning">
              Bạn cần <router-link to="/login">đăng nhập</router-link> để tạo bài viết
            </div>
            
            <form @submit.prevent="handleSubmit">
              <div class="mb-3">
                <label class="form-label">Tiêu đề</label>
                <input v-model="title" type="text" class="form-control" required>
              </div>

              <div class="mb-3">
                <label class="form-label">Danh mục</label>
                <select v-model="category" class="form-control" required>
                  <option value="">-- Chọn danh mục --</option>
                  <option value="Sức khỏe">Sức khỏe</option>
                  <option value="Dinh dưỡng">Dinh dưỡng</option>
                  <option value="Nấu ăn">Nấu ăn</option>
                  <option value="Công thức">Công thức</option>
                </select>
              </div>
              
              <div class="mb-3">
                <label class="form-label">Nội dung</label>
                <textarea v-model="content" class="form-control" rows="10" required></textarea>
              </div>

              <div class="mb-3">
                <label class="form-label">Hình ảnh</label>
                <input type="file" accept="image/*" @change="handleImageUpload" class="form-control">
              </div>

              <div v-if="imagePreview" class="mb-3">
                <img :src="imagePreview" alt="Preview" class="img-fluid" style="max-height: 300px;">
              </div>
              
              <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary">Đăng bài</button>
                <router-link to="/posts" class="btn btn-secondary">Hủy</router-link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>