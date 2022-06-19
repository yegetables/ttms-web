<template>
  <div>
    <div class="login-box">
      <h3 class="title">登录</h3>
      <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm" label-width="80px" class="demo-ruleForm">
        <el-form-item label="手机号码" prop="phoneNum">
          <el-input v-model="loginForm.phoneNum"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('loginForm')">提交</el-button>
          <el-button @click="resetForm('loginForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex'
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
        loginForm: {
          phoneNum: '',
          password: ''
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
    methods: {
      ...mapMutations('loginModule',['setUser']),
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            console.log('pass',this.loginForm);
            let{phoneNum,password}=this.loginForm
            // var data1=JSON.stringify(this.loginForm)
            console.log(this.loginForm);
            // 请求登录接口
            this.$api.byPassword(this.loginForm).then(res =>{
              console.log(res);
              if(res.data.status==0){
                //  登陆成功后 1.存储登录信息 2.跳转网页 3.顶部区域显示用户信息 4.持久化
                let obj ={
                  phoneNum:res.data.data.phoneNum,
                  token:res.headers.token
                }
                this.setUser(obj)
                // 存储本地
                localStorage.setItem('user',JSON.stringify(obj))
                this.$router.push('/admin')
                this.$message({
                  message: '恭喜你，登录成功',
                  type: 'success'
                });
              }else{
                this.$message.error('登录错误');
              }
            })
          } else {
            console.log('error submit!!');
            return false;
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
.login-box {
  width: 400px;
  padding: 10px;
  height: 250px;
  margin: 170px auto;
  border-radius: 10px;
  border: 1px solid #eee;
  /* background-color: skyblue; */
}
.title {
  text-align: center;
  margin-bottom: 20px;
}
</style>