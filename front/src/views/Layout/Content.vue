<template>
  <div>
    <div class="header">
      <i v-if="isCollapse" @click="changeMenu" class="iconfont icon-sousuo"></i>
      <i v-else @click="changeMenu" class="iconfont icon-zhedie2"></i>
      <div class="header-right">
        <div>
          <el-dropdown>
            <span class="el-dropdown-link">
            下拉菜单<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>中文</el-dropdown-item>
              <el-dropdown-item>英文</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div class="user">
          <!-- 欢迎：{{userinfo.phoneNum}} -->
          <span @click="loginout">退出登录</span>
        </div>
      </div>
    </div>
    <!-- 内容区域：路由出口 -->
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import {mapState,mapMutations} from 'vuex'
  export default {
    ...mapState('loginModule',['userinfo']),
    props:['isCollapse'],
    methods:{
      ...mapMutations('loginModule',['clearUser']),
      changeMenu(){
      // 点击切换按钮，修改父组件数据
      this.$emit('changeCollapse')
      },
      //退出登录
      loginout(){
        //退出登录
        //清空vuex数据
        this.clearUser()
        //清空本地存储
        localStorage.removeItem('user')
        // 返回登录
        this.$router.push('/login')

      }
    }
  }
</script>

<style scoped>
 .header {
   height: 60px;
   background-color: skyblue;
   line-height: 60px;
 }
 .iconfont {
   font-size: 25px;
 }
 .header-right {
   float: right;
   padding-right: 20px;
   display: flex;
 }
 .user {
   margin-left:30px ;
 }
</style>