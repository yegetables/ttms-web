<template>
  <div>
    <el-menu
      default-active="/profile"
      class="el-menu-vertical-demo"
      router
      active-text-color="#ef4238"
      background-color="#eee">
      <el-menu-item>
        <span slot="title">个人中心</span>
      </el-menu-item>
      <el-menu-item index="/profile">
        <i class="el-icon-menu"></i>
        <span slot="title">我的订单</span>
      </el-menu-item>
      <el-menu-item index="/profile/base">
        <i class="el-icon-document"></i>
        <span slot="title">基本信息</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  export default {
    
  }
</script>

<style  scoped>

</style>