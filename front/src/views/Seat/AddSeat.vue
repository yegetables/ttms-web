<template>
  <div class="add-seat">
    <!-- 面包屑 -->
    <div class="title">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path:'/admin/Seat'}">座位管理</el-breadcrumb-item>
        <el-breadcrumb-item>添加座位</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!-- 表单数据 -->
    <div class="myform">
      <el-form :model="playForm" :rules="rules" ref="playForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="座位行" prop="seatLine">
          <el-input v-model="playForm.seatLine"></el-input>
        </el-form-item>
        <el-form-item label="座位列" prop="seatColumn">
          <el-input v-model="playForm.seatColumn"></el-input>
        </el-form-item>
        <el-form-item label="演出计划id" prop="moviePlanId">
          <el-input v-model="playForm.moviePlanId"></el-input>
        </el-form-item>
        <el-form-item label="订单id" prop="orderId">
          <el-input v-model="playForm.orderId"></el-input>
        </el-form-item>
        <el-form-item label="票状态" prop="ticketStatus">
          <el-input v-model="playForm.ticketStatus"></el-input>
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
          seatLine:0,
          seatColumn:0,
          moviePlanId:0,
          orderId:0,
          ticketStatus:0
        },
        rules: {
          seatLine: [
            { required: true, message: '请输入行号', trigger: 'blur' }
          ],
          seatColumn: [
            { required: true, message: '请输入列号', trigger: 'blur' }
          ],
          moviePlanId: [
            { required: true}],
          orderId: [
            { required: true, message: '请选择订单id', trigger: 'blur' }
          ],
          ticketStatus: [
            { required: true, message: '0或者1', trigger: 'blur' }
          ],

        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
            // console.log('获取输入的信息',this.playForm);
            //解构赋值
            let {seatLine,seatColumn,moviePlanId,orderId,ticketStatus}=this.playForm
            this.$api.addSeat(
              {
                seatLine,seatColumn,moviePlanId,orderId,ticketStatus
              }
            ).then(res =>{
              console.log(res.data);
              if(res.data.code===0){
                //更新http的列表数据
                this.$router.push('/admin/seat')
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