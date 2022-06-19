<template>
  <div>
    <div>
        <!--基本信息-->
        <div class="header">
            <span class="title" style="color: #ec443f;">基本信息</span>

        </div>
        <!--详细信息-->
        <div style="margin-top: 50px;">
            <!--头像-->
            <div class="face">
                <img width="200px" height="200px" src="../../assets/login.webp"><br>
                <button style="width: 200px; height: 50px;">更换头像</button>
            </div>
            <!--内容-->
            <div class="message">
                <ul>
                    <li>
                        <span class="title">昵称：</span>
                        <span>{{profile.username}}</span>
                    </li>
                    <li>
                        <span class="title">邮箱：</span>
                        <span>{{profile.email}}</span>
                    </li>
                    <li>
                        <span class="title">电话：</span>
                        <span>{{profile.phoneNum}}</span>
                    </li>
                    <li>
                        <span class="title">性别：</span>
                        <span>{{profile.gender}}</span>
                    </li>
                    <li>
                        <span class="title">年龄：</span>
                        <span>{{profile.age}}</span>
                    </li>
                    <li style="text-align: center;">
                        <button class="btn2">保存</button>
                        <button class="btn2">取消</button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
  export default {
    data(){
        return {
            profile:{}
        }
    },
    created(){
        this.$api.getUserInfo().then(res=>{
            // console.log(res);
            if(res.data.code===0){
                this.profile=res.data.data
            }
        })
    }
  }
</script>

<style scoped>
        body {
            margin-top: 30px;
            margin-left: 400px;
            margin-right: 400px;
            padding: 0;
        }
        .header {
          margin-left: 35px;
          padding:20px;
          border-bottom: 1px solid #ccc;
        }
        .face {
            float: left;
            margin-left: 50px;
            width: 400x;
            height: 400px;
            text-align: center;
        }
        
        .title {
            font-size: 20px;
        }
        
        .message {
            float: right;
            margin-right: 200px;
        }
        
        .message ul {
            list-style: none;
        }
        
        .message ul li {
            margin-top: 10px;
        }
        
        .textForm {
            width: 200px;
            height: 30px;
            font-size: 20px;
            font-family: '微软雅黑';
        }
        
        .textForm:focus {
            outline: none;
            border-color: darkgray;
        }
        
        .btn2 {
            width: 80px;
            height: 40px;
            color: white;
            background-color: #ec443f;
        }
        
        .btn2:hover {
            background-color: #ec443f;
        }
</style>