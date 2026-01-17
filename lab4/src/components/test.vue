<template lang="">
    <div>
        <button @click="test= !test">Test</button>
    </div>
</template>
<script>
export default {
    data() {
        return {
            title: "hide"
        }
    },
    methods: {
    Test() {
        console.log(this.title);

        if (this.title === "hide") {
            this.title = "show"
        } else {
            this.title = "hide"
        }   
    }
}
}


</script>
<style lang="">
    
</style>