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

const posts = ref([]);

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
