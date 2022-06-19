<template>
  <div class="title">
    <div class="register">
      <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm" label-width="80px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="loginForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNum">
          <el-input v-model="loginForm.phoneNum"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="loginForm.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="loginForm.gender"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="code">
          <el-input v-model="loginForm.code"></el-input>
          <el-button v-if="isClick" @click="sendcode">发送验证码</el-button>
          <el-button v-else type="info" disabled >等待中</el-button>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('loginForm')" >注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      var validateUser = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入手机号'));
        } else {
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };
      return {
        isClick:true,
        loginForm: {
          phoneNum: '',
          password: '',
          email:'',
          username:'',
          age:'',
          gender:'',
          code:''
        },
        rules: {
          phoneNum: [
            { validator: validateUser, trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ]
        }
      };
    },
    methods:{
      sendcode(){
        this.isClick=false
        console.log(this);
        this.$api.getCode({
          phoneNum:this.loginForm.phoneNum
        }).then(res=>{
          console.log('data',res.data);
          if(res.data.status==0){
            console.log(res.data.msg);
            this.$message({
              message: '验证码发送成功',
              type: 'success'
            });
          }
        })
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            console.log('register',this.loginForm);
            let{phoneNum,password,email,username,age,gender,code}=this.loginForm
            // var data1=JSON.stringify(this.loginForm)
            // console.log(this.loginForm);
            // 请求登录接口
            this.$api.register(
              {
                phoneNum,password,email,username,age,gender,code
              }
            ).then(res =>{
              console.log(res);
              if(res.data.status==0){
                console.log('注册成功');
                //  登陆成功后 1.存储登录信息 2.跳转网页 3.顶部区域显示用户信息 4.持久化
                this.$message({
                  message: '恭喜你，注册成功',
                  type: 'success'
                });
                this.$router.push('/login2')
              }else{
                this.$message.error('注册错误');
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    }
  }
</script>

<style scoped>
.register{
  width: 400px;
  padding: 10px;
  height: 550px;
  margin:150px auto 0;
  border-radius: 10px;
  border: 1px solid #eee;
  /* background-color: skyblue; */
}
.title {
  text-align: center;
  margin-bottom: 20px;
}
.demo-ruleForm{
  /* background-color: #eee; */
}
</style>