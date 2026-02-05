<template lang="">
<div>
<h2>Thêm sản phẩm mới</h2>
<input v-model="name" placeholder="Tên sản phẩm" />
<input v-model="price" placeholder="Giá tiền" />
<textarea v-model="description" placeholder="Mô tả sản phẩm"></textarea>
<button @click="submitPost">Thêm sản phẩm</button>  
</div>
</template>
<script setup>
import { ref } from 'vue'

// Sử dụng defineEmits để emit sự kiện
const emit = defineEmits(['add-post'])

const name = ref('')
const price = ref('')
const description = ref('')

function submitPost() {
  if (name.value && price.value && description.value) {
    const newPost = {
      name: name.value,
      price: price.value,
      description: description.value
    }

    // Gửi sự kiện 'add-post' với dữ liệu của bài viết
    emit('add-post', newPost)

    // Reset fields
    title.value = ''
    price.value = ''
    description.value = ''
  }
}
</script>
<style>
input,
textarea {
  display: block;
  width: 100%;
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 5px;
  font-size: 1px solid #ddd;
}

button {
  padding: 10px 20px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>