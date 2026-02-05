<script setup>
import { ref, onMounted } from 'vue';
import img1 from '../assets/images/orange.jpg';
import img2 from '../assets/images/sesameoil.jpg';
import img3 from '../assets/images/spinach.jpg';

const currentUser = ref(null);
const selectedCategory = ref('');

const samplePosts = [
  {
    "id": 1,
    "title": "8 loại rau củ quả giàu canxi", 
    "content": "Canxi là khoang chất cần thiết đối với cơ thể người.Có nhiều cách để bổ sung canxi, trong đó bổ sung qua đường ăn uống là cách tốt nhất.Có 8 loại rau củ giàu canxi ...", 
    "category": "Dinh dưỡng",
    "image": img1, 
    "author": "Tran Thanh Tung",
    "userId": 1
  },
  {
    "id": 2,
    "title": "Các loại gia vị tốt cho sức khỏe", 
    "content": "Một số loại gia vị cung cấp nhiều polypheno chống oxy hóa cao hơn các loại gia vị quen thuộc khác. Với một lượng nhỏ các món ăn đã thêm hương thơm và vị hấp dẫn ...",
    "category": "Sức khỏe",
    "image": img2, 
    "author": "Tran Thanh Tung",
    "userId": 1
  },
  {
    "id": 3,
    "title": "9 loại đậu bổ dưỡng nên dùng nhiều", 
    "content": "Đậu lăng, đậu nành, đậu phộng, đậu Hà Lan giàu chất xơ, protein cùng nhiều vitamin và khoáng chất giúp giảm lượng đường trong máu, tốt cho tim ...", 
    "category": "Dinh dưỡng",
    "image": img3,
    "author": "Tran Thanh Tung",
    "userId": 1
  }
];

const posts = ref([]);

const filteredPosts = () => {
  if (!selectedCategory.value) {
    return posts.value;
  }
  return posts.value.filter(post => post.category === selectedCategory.value);
};

const deletePost = (id) => {
  if (confirm('Bạn có chắc muốn xóa bài viết này?'))  {
    posts.value = posts.value.filter(post => post.id !== id);
  }
};

onMounted(() => {
  const user = localStorage.getItem('user');
  if (user) {
    currentUser.value = JSON.parse(user);
  }
  
  posts.value = [...samplePosts];
  
  if (window.newPosts && window.newPosts.length > 0) {
    posts.value = [...posts.value, ...window.newPosts];
  }
});
</script>

<template>
  <div class="container mt-4">
    <h2 class="mb-4">Danh sách bài viết</h2>

    <div class="mb-3">
      <label class="form-label">Lọc theo danh mục:</label>
      <select v-model="selectedCategory" class="form-control" style="max-width: 300px;">
        <option value="">-- Tất cả danh mục --</option>
        <option value="Sức khỏe">Sức khỏe</option>
        <option value="Dinh dưỡng">Dinh dưỡng</option>
        <option value="Nấu ăn">Nấu ăn</option>
        <option value="Công thức">Công thức</option>
      </select>
    </div>

    <div v-if="filteredPosts().length === 0" class="alert alert-info">
      Chưa có bài viết nào
    </div>

    <div class="row">
      <div v-for="post in filteredPosts()" :key="post.id" class="col-md-6 mb-4">
        <div class="card h-100">
          <img v-if="post.image" :src="post.image" class="card-img-top" alt="Post image" style="max-height: 200px; object-fit: cover;">
          <div class="card-body">
            <h5 class="card-title">{{ post.title }}</h5>
            <p class="card-text">{{ post.content.substring(0, 150) }}...</p>
            <p class="text-muted small">Đăng bởi: {{ post.author }}</p>
          </div>
          <div class="card-footer">
            <router-link :to="`/posts/${post.id}`" class="btn btn-sm btn-info me-2">
              Xem chi tiết
            </router-link>
            <button v-if="currentUser && currentUser.id === post.userId" @click="deletePost(post.id)"
              class="btn btn-sm btn-danger">
              Xóa
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>