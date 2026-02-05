<template lang="">
    <div>
        <h1>Day la trang about</h1>
    </div>
</template>
<script setup>
import { computed } from 'vue';
import { useRoute , useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

const search = computed({
    get() {
        return route.query.search || '';
    },
    set(search) {
        router.replace({ query: { ...route.query, search } });
    }
});
</script>
<style lang="">
    
</style>