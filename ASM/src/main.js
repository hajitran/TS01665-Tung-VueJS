import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js' 

import Register from './components/Register.vue'
import Login from './components/Login.vue'
import PostList from './components/PostList.vue'
import CreatePost from './components/CreatePost.vue'
import PostDetail from './components/PostDetails.vue'
import Profile from './components/Profile.vue'  
import About from './components/About.vue'

const routes = [
  { path: '/', redirect: '/posts' },
  { path: '/register', component: Register },
  { path: '/login', component: Login },
  { path: '/posts', component: PostList },
  { path: '/posts/create', component: CreatePost, meta: { requiresAuth: true } },
  { path: '/posts/:id', component: PostDetail },
  { path: '/profile', component: Profile, meta: { requiresAuth: true } },
  { path: '/about', component: About }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Route protection for authenticated pages
router.beforeEach((to, from, next) => {
  const requiresAuth = to.meta.requiresAuth;
  const user = localStorage.getItem('user');

  if (requiresAuth && !user) {
    next('/login');
  } else {
    next();
  }
});

const app = createApp(App)
app.use(router)
app.mount('#app')