<template>
  <div class="user">
    <!-- 搜索区域 -->
    <div class="header">
      <el-input @change="searchInp" v-model="input" placeholder="请输入内容"></el-input>
      <el-button type="primary">按照用户名查询</el-button>
      <el-button type="primary">
        <router-link to="/admin/add-user" style="color:#fff">添加</router-link>
      </el-button>

    </div>
    <!-- :row-class-name="tableRowClassName" -->
    <!-- 表格区域 -->
    <div class="wrapper">
    <el-table
    :data="tableData"
    style="width: 100%"
    >
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      prop="uid"
      label="ID">
    </el-table-column>
    <el-table-column
      prop="username"
      label="用户名">
    </el-table-column>
    <el-table-column
      prop="email" show-overflow-tooltip
      label="邮箱">
    </el-table-column>
    <el-table-column show-overflow-tooltip
      prop="phoneNum"
      label="手机号"
      width="150">
    </el-table-column>
    <el-table-column
      prop="age"
      label="年龄">
    </el-table-column>
    <el-table-column
      prop="gender"
      label="性别" width="100">
    </el-table-column>
    <el-table-column
      prop="address"
      label="操作" width="200">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

    </div>
    <!-- 分页操作 -->
    <MyPagination :total="total" :pageSize="pageSize" @changePage="changePage" :currentPage="currentPage"></MyPagination>

  </div>
</template>

<script>
import MyPagination from '../../components/MyPagination.vue'
  export default {
    components:{
      MyPagination
    }
    ,
    data(){
      return {
        input:'',
        tableData:[
        ],
        total:0,
        pageSize:5,
        currentPage:1//选中高亮
      }
    },
    methods:{
      //搜索查询数据
      searchInp(val){
        if(!val){
          this.http(1);
          return;
        }
        let data={
          page: {
            current: 1, 
            size: 1 
          },
          users:{
            username:val
          }
        }
        this.$api.searchUser(data).then(res=>{
          this.currentPage=1
          if(res.data.code===0){
            this.tableData=res.data.data.records;
            this.total=res.data.data.total
          }else{
            this.tableData=[]
          }
        })
      },
      // 分页操作
      changePage(num){
        this.currentPage=num
        this.http(num)
      },
      //编辑操作
      handleEdit(){

      },
      //删除操作
      handleDelete(index,row){
        console.log('delete'+index,row);
        this.$confirm('此操作将永久删除用户数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 请求接口
            this.$api.deleteUser({
              idList:row.uid
            }).then(res=>{
              console.log('删除',res.data);
              if(res.data.code === 0){
                this.$message({
                type: 'success',
                message: '删除成功!'
                });
                //视图更新
                this.http(1)
              }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      }
      ,
      http(pagenum){
        let params={
          page: {
            current: pagenum, 
            size: 5 
          }
        }
        this.$api.getAllUsers(params).then(res=>{
        // console.log(res.data)
        if(res.data.code===0){
        this.tableData=res.data.data.records
        this.total=res.data.data.total
        // this.pageSize=res.data.pageSize
      }
      })
      }
    },
    created(){
      this.http(1)
    }
  }
</script>

<style scoped>
.user {
  margin: 15px;
}
.header {
  margin-bottom: 20px;
  display: flex;
}
button {
    margin-left: 10px;
}
</style>