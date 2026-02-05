<!-- <script setup>
import bai1 from './components/bai1.vue';
import bai2 from './components/bai2.vue';
import bai3 from './components/bai3.vue';
</script> -->

<template>
  <div id="app">
    <h1>Ung dung nho voi vue.js</h1>
    <CreatePost @add-post="addPost" />
    <PostList :posts="posts" />
  </div>

</template>

<script setup>
import PostList from './components/PostList.vue';
import CreatePost from './components/CreatePost.vue';
import { ref } from 'vue';

const posts = ref([
  { id: 1, title: 'Bật Mí 8 Nguyên Tắc Phối Màu Quần Áo Sành Điệu', 
  content: 'Bạn có thể phối áo màu xanh lá, quần màu kem và giày màu tím theo cách phối màu kiểu tam giác. Hoặc có thể phối các set đồ cùng ton lạnh hoặc nóng ...', 
  author: 'Tran Thanh Tung' },
  { id: 2, title: '10 Cách Giúp Bạn Tự Tin Hơn Trong Giao Tiếp', 
  content: 'Tự tin là yếu tố quan trọng giúp bạn thành công trong giao tiếp',
  author: 'Nguyen Van Teo'}
]);

function addPost(post) {
  posts.value.push(post);
}
</script>

<style>
#app {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}
</style>
