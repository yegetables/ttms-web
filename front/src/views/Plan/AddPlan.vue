<template>
  <div class="add-seat">
    <!-- 面包屑 -->
    <div class="title">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path:'/admin/Plan'}">演出计划管理</el-breadcrumb-item>
        <el-breadcrumb-item>添加演出计划</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!-- 表单数据 -->
    <div class="myform">
      <el-form :model="playForm" :rules="rules" ref="playForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="电影开始时间" prop="movieStartTime">
          <el-date-picker
          v-model="playForm.movieStartTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
           placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="电影结束时间" prop="movieEndTime">
          <el-date-picker
          v-model="playForm.movieEndTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
           placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="演出时间" prop="planDate">
          <el-date-picker
          v-model="playForm.planDate"
            type="date"
            value-format="yyyy-MM-dd"
           placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="电影id" prop="cinemaMovieId">
          <el-input v-model="playForm.cinemaMovieId"></el-input>
        </el-form-item>
        <el-form-item label="演出厅id" prop="hallId">
          <el-input v-model="playForm.hallId"></el-input>
        </el-form-item>
        <el-form-item label="票价" prop="ticketMoney">
          <el-input v-model="playForm.ticketMoney"></el-input>
        </el-form-item>
        <el-form-item label="电影院id" prop="cinemaId">
          <el-input v-model="playForm.cinemaId"></el-input>
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
          movieStartTime:'',
          movieEndTime:'',
          planDate:'',
          cinemaMovieId:0,
          hallId:0,
          ticketMoney:0,
          cinemaId:0
        },
        rules: {
          movieStartTime: [
            { required: true, message: '请输入电影院id', trigger: 'blur' }
          ],
          movieEndTime: [
            { required: true, message: '演出厅行数', trigger: 'blur' }
          ],
          planDate: [
            { required: true}],
          cinemaMovieId: [
            { required: true, message: '演出厅厅名字', trigger: 'blur' }
          ],
          hallId: [
            { required: true, message: '请选择影厅类型', trigger: 'blur' }
          ],

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
            let {movieStartTime,movieEndTime,planDate,cinemaMovieId,hallId,ticketMoney,cinemaId}=this.playForm
            this.$api.addPlan(
              {
                movieStartTime,movieEndTime,planDate,cinemaMovieId,hallId,ticketMoney,cinemaId
              }
            ).then(res =>{
              console.log(res.data);
              if(res.data.code===0){
                //更新http的列表数据
                this.$router.push('/admin/plan')
                this.$message({
                  message:'添加成功',
                  type:'success'
                })
              }else if(res.data.code==-1){
                this.$message.error(res.data.msg)
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