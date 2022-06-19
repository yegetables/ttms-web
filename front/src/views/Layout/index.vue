<template>
  <div class="layout">
    <MyMenu class="menu" :isCollapse="isCollapse"></MyMenu>
    <Content class="content" :class="{isActive:isCollapse}" @changeCollapse="changeCollapse"></Content>
  </div>
</template>

<script>
import MyMenu from './Mymenu.vue'
import Content from './Content.vue'
  export default {
    components: {
        MyMenu,
        Content
    },
    data(){
      return {
        isCollapse:false
      }
      
    },
    methods:{
      changeCollapse(){
        this.isCollapse= !this.isCollapse
      }
    }
}
</script>

<style scoped>
  .layout {
    background-color: #fff;
  }
  .menu {
    /* width: 200px; */
    /* background-color: pink; */
    position:fixed;
    top:0;
    bottom: 0;
  }
  .content {
    margin-left: 200px;
  }
  .isActive{
    margin-left: 64px;
  }
</style>