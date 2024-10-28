<template>
  <div class="brand flex-wrap">
    <div class="brand-logo">
      <a href="https://elearn.sulekha.com"
        ><img src="https://sulcdn.azureedge.net/content/images/elearn-logo.png"
      /></a>
    </div>
  </div>
</template>

<script>
export default {
  name: "Logo",
};
</script>

<style scoped>
.flex-wrap {
  align-items: center;
  width: 100%;
}

.brand {
  position: fixed;
  width: 100%;
  top: 0;
  background: #fff;
  padding: 5px 0;
  box-shadow: 0 0 4px 1px #ddd;
  z-index: 1;
}

.brand-logo {
  width: calc(100% - 50px);
  padding: 0 20px;
}
.brand-logo img {
  width: 80px;
}
</style>