<template>
  <div class="add-seat">
    <!-- 面包屑 -->
    <div class="title">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path:'/admin/user'}">用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>添加用户</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!-- 表单数据 -->
    <div class="myform">
      <el-form :model="playForm" :rules="rules" ref="playForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="playForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="playForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="playForm.email"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="playForm.age"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNum">
          <el-input v-model="playForm.phoneNum"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="playForm.gender"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('playForm')">确定</el-button>
          <el-button @click="resetForm('playForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        playForm: {
          username:"",
          password:"",
          email:"",
          age:0,
          phoneNum:'',
          gender:''
        },
        rules: {
          username: [
            { required: true, message: 'username', trigger: 'blur' }
          ],
          password: [
            { required: true, message: 'password', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
            console.log('获取输入的信息',this.playForm);
            //解构赋值
            let {username,password,email,age,gender,phoneNum}=this.playForm
            this.$api.addUser(
              {
                username,password,email,age,gender,phoneNum
              }
            ).then(res =>{
              console.log(res.data);
              if(res.data.code===0){
                //更新http的列表数据
                this.$router.push('/admin/user')
                this.$message({
                  message:'添加成功',
                  type:'sucess'
                })
              }
            })
          } else {
            this.$message.error('这是一条错误信息')
            // console.log('error submit!!');
            // return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>
.add-seat {
  margin:10px
}
.title {
  padding: 10px;
  background-color: #fff;
  border:1px solid #eee;
  margin-bottom:20px;
  font-size: 20px;
}
</style>