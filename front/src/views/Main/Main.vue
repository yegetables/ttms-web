<template>
  <div>
    <div class="header">
      <h2>西柚影院</h2>
      <div class="nav">
        <!-- <div><router-link to="/">首页</router-link></div>
        <div><router-link to="/film">电影</router-link></div>
        <div><router-link to="/list">榜单</router-link></div> -->
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" router active-text-color="#ef4238">
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/film">电影</el-menu-item>
          <el-menu-item index="/list">榜单</el-menu-item>
          <el-menu-item v-if='isAdmin' index="/admin">后台管理系统</el-menu-item>
        </el-menu>
      </div>
      <div class="user">
        <div  v-if="phoneNum">
          <router-link to="/profile">{{ phoneNum }}</router-link>
          <span @click="loginOut" style="margin-left:10px;font-size: 14px;color: rgb(116, 117, 118);">退出登录</span>
        </div>
        <div v-else>
          <router-link to="/login">登录</router-link>
          <router-link to="/register">注册</router-link>
        </div>
      </div>
    </div>
    <div class="content">
      <router-view></router-view>
    </div> 
  </div>
</template>

<script>
  export default {
    props:['isLogin'],
    data() {
      return {
        activeIndex: '1',
        isAdmin:false,
        phoneNum:''
      };
    },
    mounted(){
      if(localStorage.getItem('user')){
        if(JSON.parse(localStorage.getItem('user')).phoneNum === 'admin'){
          this.phoneNum = 'admin'
          this.isAdmin = true
        }else{
          if(JSON.parse(localStorage.getItem('user')).phoneNum){
            this.phoneNum = JSON.parse(localStorage.getItem('user')).phoneNum
          }
          this.isAdmin = false
        }
      }
    },
    methods:{
      loginOut(){
        localStorage.removeItem('user')
        this.$router.push('/login')
      }
    }
  }
</script>

<style scoped>
.title {
  color:red;
}
.header {
  position: fixed;
  top: 0;
  z-index: 999;
  width: 100%;
  background-color: #fff;
  border-bottom: 1px solid #d8d8d8;
}
.header h2 {
  float: left;
  line-height: 80px;
  margin-left:100px;
  margin-right: 180px;
}
.header .nav {
    /* background-color: pink; */
    overflow: hidden;
    width: 530px;
    float: left;
    display: flex;
}
.header .nav div a {
    text-align: center;
    display: inline-block;
    height: 80px;
    line-height: 80px;
    width: 100%;
    font-size: 18px;
    color: #333;
}
.header .nav div {
  flex:1;
}
.header .nav div a:hover {
    color: #fff;
    background-color: #ef4238;
}
.header .user {
    float: right;
    position: relative;
    z-index: 9999;
    height: 100%;
    line-height: 80px;
    margin-right: 80px;
}
.header .user a {
  margin-left: 20px;
}
.content {
  /* width: 1200px; */
  /* background-color: pink; */
  margin:100px auto;
}
</style>