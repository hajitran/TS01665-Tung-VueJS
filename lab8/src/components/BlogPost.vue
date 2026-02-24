<template>
  <div class="blog-post">
    <h2>{{ post.title }}</h2>
    <p>{{ post.content }}</p>
    <p><strong>Tác giả:</strong> {{ post.author }}</p>
    <router-link to="/blogs">← Quay lại danh sách</router-link>
  </div>
</template>

<script>
export default {
  name: 'BlogPost',
  data() {
    return {
      post: {}
    }
  },
  created() {
    const id = this.$route.params.id;
    // Giả lập dữ liệu blog
    const blogs = {
      1: { title: 'Ông Trump: Mỹ sẽ "dễ dàng chiến thắng" nếu nổ ra xung đột với Iran', content: 'Tổng thống Trump tuyên bố Mỹ có thể dễ dàng đánh bại Iran trong mọi cuộc xung đột, bác bỏ những cảnh báo về nguy cơ sa lầy.', author: 'Tung' },
      2: { title: 'Trùm ma túy khét tiếng nhất Mexico lộ tung tích vì bạn gái', content: 'Nemesio Oseguera, thủ lĩnh băng đảng ma túy CJNG lớn nhất Mexico, bị phát hiện và tiêu diệt sau khi cơ quan tình báo lần theo dấu vết bạn gái của ông ta.', author: 'Vũ Hoàng' },
      3: { title: 'Nga, Venezuela kêu gọi Mỹ "lập tức thả Tổng thống Maduro"', content: 'Nga bày tỏ tôn trọng chủ quyền Venezuela, ủng hộ giải quyết bất đồng bằng biện pháp hòa bình và kêu gọi Mỹ lập tức thả Tổng thống Maduro.', author: 'Tung' }
    };
    this.post = blogs[id] || { title: 'Không tìm thấy', content: 'Bài viết không tồn tại', author: '' };
  }
}
</script>

<style scoped>
.blog-post {
  padding: 20px;
}
</style>