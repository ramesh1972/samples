<template>
    <div class="contentPos">
        <Child msg="Hello Ramesh"></Child>
        <Child msg="Hello Ganesh"></Child>
        <Child msg="Hello Suresh"></Child>
    </div>
</template>

<script>
    import Child from './Child.vue';
    import { defineComponent } from 'vue';

    export default defineComponent({
        name: 'Parent',
        components: {
            Child
        }
    });
</script>

<style scoped>
.contentPos {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}
</style>