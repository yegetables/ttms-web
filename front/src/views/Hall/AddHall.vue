<template>
  <div class="add-seat">
    <!-- 面包屑 -->
    <div class="title">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path:'/admin/Hall'}">演出厅管理</el-breadcrumb-item>
        <el-breadcrumb-item>添加演出厅</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!-- 表单数据 -->
    <div class="myform">
      <el-form :model="playForm" :rules="rules" ref="playForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="电影院id" prop="cinemaId">
          <el-input v-model="playForm.cinemaId"></el-input>
        </el-form-item>
        <el-form-item label="演出厅行数" prop="seatLine">
          <el-input v-model="playForm.seatLine"></el-input>
        </el-form-item>
        <el-form-item label="演出厅列数" prop="seatColumn">
          <el-input v-model="playForm.seatColumn"></el-input>
        </el-form-item>
        <el-form-item label="演出厅名字" prop="hallName">
          <el-input v-model="playForm.hallName"></el-input>
        </el-form-item>
        <el-form-item label="影厅类型" prop="movieType">
          <el-input v-model="playForm.movieType"></el-input>
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
          cinemaId:0,
          seatLine:0,
          seatColumn:0,
          hallName:'',
          movieType:''
        },
        rules: {
          cinemaId: [
            { required: true, message: '请输入电影院id', trigger: 'blur' }
          ],
          seatLine: [
            { required: true, message: '演出厅行数', trigger: 'blur' }
          ],
          seatColumn: [
            { required: true}],
          hallName: [
            { required: true, message: '演出厅厅名字', trigger: 'blur' }
          ],
          movieType: [
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
            let {cinemaId,seatLine,seatColumn,hallName,movieType}=this.playForm
            this.$api.addHall(
              {
                cinemaId,seatLine,seatColumn,hallName,movieType
              }
            ).then(res =>{
              // console.log(res.data);
              if(res.data.code===0){
                //更新http的列表数据
                this.$router.push('/admin/hall')
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