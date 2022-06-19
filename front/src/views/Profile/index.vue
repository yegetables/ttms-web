<template>
  <div>
    <div class="profile">
      <Mymenu class="menu"></Mymenu>
      <Content class="content"></Content>
    </div>
  </div>
</template>

<script>
import Mymenu from './Mymenu.vue'
import Content from './Content.vue'
  export default {
  components: { Mymenu,
    Content },
    
  }
</script>

<style scoped>
.profile {
  font-size: 18px;
  width: 1000px;
  /* background-color: #ccc; */
  margin: 30px auto;
}
  .menu {
    /* width: 200px; */
    /* background-color: pink; */
    position:fixed;
    top:100px;
    bottom: 0;
  }
  .content {
    margin-left: 200px;
  }
</style>