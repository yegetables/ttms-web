<template>
  <div class="add-play">
    <!-- 面包屑 -->
    <div class="title">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path:'/admin/play'}">剧目管理</el-breadcrumb-item>
        <el-breadcrumb-item>添加剧目</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!-- 表单数据 -->
    <div class="myform">
      <el-form :model="playForm" :rules="rules" ref="playForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="电影名称" prop="movieName">
          <el-input v-model="playForm.movieName"></el-input>
        </el-form-item>
        <el-form-item label="电影状态" prop="movieStatus">
          <el-input v-model="playForm.movieStatus"></el-input>
        </el-form-item>
        <el-form-item label="电影上映时间" prop="movieStart">
          <el-date-picker
          v-model="playForm.movieStart"
            type="datetime"
            value-format="yyyy-MM-dd hh:mm:ss"
           placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="电影时长" prop="movieMinute">
          <el-input v-model="playForm.movieMinute"></el-input>
        </el-form-item>
        <el-form-item label="电影评分" prop="movieScore">
          <el-input v-model="playForm.movieScore"></el-input>
        </el-form-item>
        <el-form-item label="电影money" prop="movieMoney">
          <el-input v-model="playForm.movieMoney"></el-input>
        </el-form-item>
        <el-form-item label="电影产地" prop="movieArea">
          <el-input v-model="playForm.movieArea"></el-input>
        </el-form-item>
        <el-form-item label="电影图片" prop="movieHead">
          <el-input v-model="playForm.movieHead"></el-input>
        </el-form-item>
        <el-form-item label="日收入" prop="dayMoney">
          <el-input v-model="playForm.dayMoney"></el-input>
        </el-form-item>
        <el-form-item label="想看" prop="wantLook">
          <el-input v-model="playForm.wantLook"></el-input>
        </el-form-item>
        <el-form-item label="电影类型" prop="movieType">
          <el-checkbox-group v-model="playForm.movieType">
            <el-checkbox label="喜剧" name="movieType"></el-checkbox>
            <el-checkbox label="爱情" name="movieType"></el-checkbox>
            <el-checkbox label="青春" name="movieType"></el-checkbox>
            <el-checkbox label="剧情" name="movieType"></el-checkbox>
            <el-checkbox label="动画" name="movieType"></el-checkbox>
            <el-checkbox label="恐怖" name="movieType"></el-checkbox>
            <el-checkbox label="武侠" name="movieType"></el-checkbox>
            <el-checkbox label="科幻" name="movieType"></el-checkbox>

          </el-checkbox-group>
        </el-form-item>
  
        <el-form-item label="电影简介" prop="movieBrief">
          <el-input type="textarea" v-model="playForm.movieBrief"></el-input>
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
import { number } from 'echarts';
  export default {
    data() {
      return {
        playForm: {
          movieName: '',
          movieStatus: 0,
          movieStart: '',
          movieMinute: 0,
          movieBrief: '',
          movieScore:0.0,
          movieMoney: 0,
          movieArea: '',
          movieHead: '',
          dayMoney: 0.0,
          wantLook: 0,
          movieType: []
        },
        rules: {
          movieName: [
            { required: true, message: '请输入电影名称', trigger: 'blur' },
            { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
          ],
          movieStatus: [
            { required: true, message: '请选择movieStatus', trigger: 'blur' }
          ],
          movieStart: [
            { required: true, message: '请选择movieStart', trigger: 'blur' }
          ],
          movieMinute: [
            { required: true, message: '请选择电影时长', trigger: 'blur' }
          ],

        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
            //解构赋值
            let {movieName,movieStatus,movieStart,movieMinute,movieBrief,movieScore,movieMoney,movieArea,movieHead,dayMoney,wantLook,movieType}=this.playForm
            console.log(this.playForm);
            var movieTypeStr=''
            for(let i=0;i<movieType.length;i++){
              if(i===0){
                movieTypeStr+=movieType[i]
              }else {
                movieTypeStr+='/'
                movieTypeStr+=movieType[i]
              }
            }
            console.log('type',movieTypeStr);
            this.$api.addMovies(
              {
                movieName,movieStatus,movieStart,movieMinute,movieBrief,movieScore,movieMoney,movieArea,movieHead,dayMoney,wantLook,
                movieType:movieTypeStr
              }
            ).then(res =>{
              console.log(res.data);
              if(res.data.code===0){
                //更新http的列表数据
                this.$router.push('/admin/play')
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
      },
      http(pagenum){
        let params={
          page: {
            current: pagenum, 
            size: 5 
          }
        }
        this.$api.getPlayList(params).then(res=>{
        // console.log(res.data)
        if(res.data.code===0){
        this.tableData=res.data.data.records
        this.total=res.data.data.total
        }
      })
      }
    }
  }
</script>

<style scoped>
.add-play {
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