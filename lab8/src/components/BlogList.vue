<template>
  <div class="blog-list">
    <h2>Danh sách Blog</h2>
    <ul>
      <li v-for="blog in blogs" :key="blog.id">
        <router-link :to="`/blog/${blog.id}`">{{ blog.title }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'BlogList',
  data() {
    return {
      blogs: [
        { id: 1, title: 'Ông Trump: Mỹ sẽ "dễ dàng chiến thắng" nếu nổ ra xung đột với Iran' },
        { id: 2, title: 'Trùm ma túy khét tiếng nhất Mexico lộ tung tích vì bạn gái' },
        { id: 3, title: 'Nga, Venezuela kêu gọi Mỹ "lập tức thả Tổng thống Maduro"' }
      ]
    }
  }
}
</script>

<style scoped>
.blog-list {
  padding: 20px;
}
ul {
  list-style: none;
  padding: 0;
}
li {
  margin: 10px 0;
}
</style>