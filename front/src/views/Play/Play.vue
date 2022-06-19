<template>
  <div class="play">
    <!-- 搜索区域 -->
    <div class="header">
      <el-input @change="searchInp" v-model="input" placeholder="请输入内容"></el-input>
      <el-button  type="primary">查询</el-button>
      <el-button type="primary">
        <router-link to="/admin/add-play" style="color:#fff">添加</router-link>
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
      label="ID"
      width="50">
    </el-table-column>
    <el-table-column
      prop="movieName"
      label="电影名称"
      width="100">
    </el-table-column>
    <el-table-column
      prop="movieStart" show-overflow-tooltip
      label="上映时间"
      width="100" sortable>
    </el-table-column>
    <el-table-column show-overflow-tooltip
      prop="movieBrief"
      label="电影简介"
      width="150">
    </el-table-column>
    <el-table-column
      prop="movieArea"
      label="电影产地">
    </el-table-column>
    <el-table-column
      prop="movieScore"
      label="评分" width="100" sortable>
    </el-table-column>
    <el-table-column
      prop="movieType" show-overflow-tooltip
      label="电影类型">
    </el-table-column>
    <el-table-column
      prop="movieMinute"
      label="电影时长" width="80">
    </el-table-column>
    <el-table-column
      prop="movieHead" show-overflow-tooltip
      label="电影图片">
    </el-table-column>
    <el-table-column
      prop="dayMoney" show-overflow-tooltip
      label="日营业额">
    </el-table-column>
    <el-table-column
      prop="wantLook"
      label="想看">
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
          movie:{
            id:parseInt(val)
          }
        }
        this.$api.getSearch(data).then(res=>{
          this.currentPage=1
          if(res.data.code===0){
            this.tableData=res.data.data.records;
            this.total=res.data.total
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
        this.$confirm('此操作将永久删除该电影数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 请求接口
            this.$api.deleteMovie({
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
        this.$api.getPlayList(params).then(res=>{
        // console.log(res.data)
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
.play {
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