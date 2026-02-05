import { createRouter, createWebHistory } from 'vue-router';
// import components for your routes
import Home from '../components/Home.vue';
import About from '../components/about.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home

    },
    {
        path: '/about',
        name: 'About',
        component: About
    }
];
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
});

export default router;