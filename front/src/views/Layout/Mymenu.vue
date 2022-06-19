<template>
  <div>
    <!-- @open="handleOpen" -->
      <!-- @close="handleClose" -->
    <el-menu
      default-active="/"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      router
      :collapse="isCollapse"
      active-text-color="#ffd04b">
      <el-menu-item>
        <span slot="title">西柚影院后台管理系统</span>
      </el-menu-item>
      <el-menu-item index="/admin">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-menu-item index="/admin/play">
        <i class="el-icon-document"></i>
        <span slot="title">电影管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/plan">
        <i class="el-icon-setting"></i>
        <span slot="title">演出计划管理</span>
      </el-menu-item>
      <el-submenu index="/admin/ticket">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>票务管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/admin/ticket/ticket-list">
            <i class="el-icon-location"></i>
            <span>订单列表</span>
          </el-menu-item>
          <el-menu-item index="/admin/ticket/ticket-back">
            <i class="el-icon-location"></i>
          <span>取消订单</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/admin/seat">
        <i class="el-icon-menu"></i>
        <span slot="title">座位管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/hall">
        <i class="el-icon-menu"></i>
        <span slot="title">演出厅管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/user">
        <i class="el-icon-menu"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  export default {
    props:['isCollapse'],
    data(){
      return {
        // isCollapse:false
      }
    }
  }
</script>

<style scoped>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
  .el-menu-vertical-demo {
    height: 100%;
  }
</style>