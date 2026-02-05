<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const post = ref(null);
const comments = ref([]);
const newComment = ref('');
const currentUser = ref(null);

const allPosts = [
  {
    "id": 1,
    "title": "8 loại rau củ quả giàu canxi", 
    "content": "Canxi là khoang chất cần thiết đối với cơ thể người.Có nhiều cách để bổ sung canxi, trong đó bổ sung qua đường ăn uống là cách tốt nhất.Có 8 loại rau củ giàu canxi ...",
    "category": "Dinh dưỡng", 
    "author": "Tran Thanh Tung",
    "userId": 1,
    "createdAt": new Date().toISOString()
  },
  {
    "id": 2,
    "title": "Các loại gia vị tốt cho sức khỏe", 
    "content": "Một số loại gia vị cung cấp nhiều polypheno chống oxy hóa cao hơn các loại gia vị quen thuộc khác. Với một lượng nhỏ các món ăn đã thêm hương thơm và vị hấp dẫn ...",
    "category": "Sức khỏe",
    "author": "Tran Thanh Tung",
    "userId": 1,
    "createdAt": new Date().toISOString()
  },
  {
    "id": 3,
    "title": "9 loại đậu bổ dưỡng nên dùng nhiều", 
    "content": "Đậu lăng, đậu nành, đậu phộng, đậu Hà Lan giàu chất xơ, protein cùng nhiều vitamin và khoáng chất giúp giảm lượng đường trong máu, tốt cho tim ...",
    "category": "Dinh dưỡng",
    "author": "Tran Thanh Tung",
    "userId": 1,
    "createdAt": new Date().toISOString()
  }
];

const getPost = () => {
  const postId = parseInt(route.params.id);
  
  // Combine sample posts with new posts
  let allAvailablePosts = [...allPosts];
  if (window.newPosts && window.newPosts.length > 0) {
    allAvailablePosts = [...allAvailablePosts, ...window.newPosts];
  }
  
  post.value = allAvailablePosts.find(p => p.id === postId);
  if (!post.value) {
    router.push('/posts');
  }
};

const addComment = () => {
  if (!currentUser.value) {
    alert('Vui lòng đăng nhập để bình luận!');
    router.push('/login');
    return;
  }
  
  if (!newComment.value.trim()) return;
  
  const newCommentObj = {
    id: comments.value.length + 1,
    postId: route.params.id,
    author: currentUser.value.name,
    content: newComment.value,
    createdAt: new Date().toISOString()
  };
  
  comments.value.push(newCommentObj);
  newComment.value = '';
};

onMounted(() => {
  const user = localStorage.getItem('user');
  if (user) {
    currentUser.value = JSON.parse(user);
  }
  getPost();
});
</script>

<template>
  <div class="container mt-4">
    <router-link to="/posts" class="btn btn-secondary mb-3">← Quay lại</router-link>
    
    <div v-if="post" class="card mb-4">
      <div class="card-body">
        <h2 class="card-title">{{ post.title }}</h2>
        <span v-if="post.category" class="badge bg-primary mb-2">{{ post.category }}</span>
        <p class="text-muted">Đăng bởi: {{ post.author }} - {{ new Date(post.createdAt).toLocaleDateString() }}</p>
        <hr>
        <p class="card-text" style="white-space: pre-wrap;">{{ post.content }}</p>
      </div>
    </div>
    
    <div class="card">
      <div class="card-body">
        <h4 class="mb-3">Bình luận ({{ comments.length }})</h4>
        
        <div v-if="!currentUser" class="alert alert-warning">
          Bạn cần <router-link to="/login">đăng nhập</router-link> để bình luận
        </div>
        
        <form v-if="currentUser" @submit.prevent="addComment" class="mb-4">
          <div class="mb-3">
            <textarea v-model="newComment" 
                      class="form-control" 
                      placeholder="Viết bình luận..." 
                      rows="3"></textarea>
          </div>
          <button type="submit" class="btn btn-primary">Gửi bình luận</button>
        </form>
        
        <div v-if="comments.length === 0" class="alert alert-light">
          Chưa có bình luận nào
        </div>
        
        <div v-for="comment in comments" :key="comment.id" class="mb-3 border-bottom pb-3">
          <p class="mb-1"><strong>{{ comment.author }}</strong></p>
          <p class="mb-1">{{ comment.content }}</p>
          <small class="text-muted">{{ new Date(comment.createdAt).toLocaleString() }}</small>
        </div>
      </div>
    </div>
  </div>
</template>