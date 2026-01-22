<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const currentUser = ref(null);

import img1 from '../assets/images/orange.jpg';
import img2 from '../assets/images/sesameoil.jpg';
import img3 from '../assets/images/spinach.jpg';
const posts = ([
  {
    title: '8 loại rau củ quả giàu canxi', content: 'Canxi là khoang chất cần thiết đối với cơ thể người.Có nhiều cách để bổ sung canxi, trong đó bổ sung qua đường ăn uống là cách tốt nhất.Có 8 loại rau củ giàu canxi ...', image: img1
  },
  {
    title: 'Các loại gia vị tốt cho sức khỏe', content: 'Một số loại gia vị cung cấp nhiều polypheno chống oxy hóa cao hơn các loại gia vị quen thuộc khác. Với một lượng nhỏ các món ăn đã thêm hương thơm và vị hấp dẫn ...',
    image: img2
  },
  {
    title: '9 loại đậu bổ dưỡng nên dùng nhiều', content: 'Đậu lăng, đậu nành, đậu phộng, đậu Hà Lan giàu chất xơ, protein cùng nhiều vitamin và khoáng chất giúp giảm lượng đường trong máu, tốt cho tim ...', image:
      img3
  },
]);


const fetchPosts = async () => {
  try {
    const response = await axios.get('http://localhost:3000/posts');
    posts.value = response.data;
  } catch (error) {
    console.error('Lỗi khi lấy bài viết:', error);
  }
};

const deletePost = async (id) => {
  if (confirm('Bạn có chắc muốn xóa bài viết này?')) {
    try {
      await axios.delete(`http://localhost:3000/posts/${id}`);
      fetchPosts();
    } catch (error) {
      console.error('Lỗi khi xóa:', error);
    }
  }
};

onMounted(() => {
  const user = localStorage.getItem('user');
  if (user) {
    currentUser.value = JSON.parse(user);
  }
  fetchPosts();
});
</script>

<template>
  <div class="container mt-4">
    <h2 class="mb-4">Danh sách bài viết</h2>

    <div v-if="posts.length === 0" class="alert alert-info">
      Chưa có bài viết nào
    </div>

    <div class="row">
      <div v-for="post in posts" :key="post.id" class="col-md-6 mb-4">
        <div class="card h-100">
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