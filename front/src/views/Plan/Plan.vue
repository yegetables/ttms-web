<template>
  <div class="plan">
    <!-- 搜索区域 -->
    <div class="header">
      <el-input @change="searchInp" v-model="input" placeholder="请输入内容"></el-input>
      <el-button type="primary">根据id查询</el-button>
      <el-button type="primary">
        <router-link to="/admin/add-plan" style="color:#fff">添加</router-link>
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
      prop="id"
      label="ID">
    </el-table-column>
    <el-table-column
      prop="movieStartTime" show-overflow-tooltip
      label="电影开始时间">
    </el-table-column>
    <el-table-column
      prop="movieEndTime" show-overflow-tooltip
      label="电影结束时间">
    </el-table-column>
    <el-table-column
      prop="planDate"
      label="演出日期">
    </el-table-column>
    <el-table-column
      prop="cinemaMovieId"
      label="电影id">
    </el-table-column>
    <el-table-column
      prop="hallId"
      label="演出厅id">
    </el-table-column>
    <el-table-column
      prop="ticketMoney"
      label="票价">
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
        total:5,
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
          moviePlan:{
            id:parseInt(val)
          }
        }
        this.$api.searchPlan(data).then(res=>{
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
        this.$confirm('此操作将永久删除该演出计划, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 请求接口
            this.$api.deletePlan({
              idList:row.id
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
        this.$api.getPlanList(params).then(res=>{
        console.log(res.data)
        if(res.data.code===0){
        this.tableData=res.data.data.records
        this.total=res.data.data.total
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
.plan {
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