<template>
  <div>
    <div class="subnav">
      <ul class="navbar">
        <li><a>正在热映</a></li>
        <li><a>正在热映</a></li>
        <li><a>正在热映</a></li>
      </ul>
    </div>
    <div class="container">
      <div class="tag-panel">
        <ul class="tags-lines">
          <li class="tags-line">
            类型：
            <el-radio-group v-model="radio1" size="mini">
              <el-radio-button label="全部"></el-radio-button>
              <el-radio-button label="爱情"></el-radio-button>
              <el-radio-button label="喜剧"></el-radio-button>
              <el-radio-button label="动画"></el-radio-button>
              <el-radio-button label="剧情"></el-radio-button>
            </el-radio-group>
          </li>
          <li class="tags-line">
            地区:
            <el-radio-group v-model="radio2" size="mini">
              <el-radio-button label="全部"></el-radio-button>
              <el-radio-button label="中国大陆"></el-radio-button>
              <el-radio-button label="美国"></el-radio-button>
              <el-radio-button label="中国香港"></el-radio-button>
            </el-radio-group>
          </li>
        </ul>
      </div>

      <div class="movies-panel">
        <div class="sorter">
          <el-radio-group v-model="radio" @change="Change(radio)">
            <el-radio :label="1">按热门排序</el-radio>
            <el-radio :label="2">按时间排序</el-radio>
            <el-radio :label="3">按评价排序</el-radio>
          </el-radio-group>
        </div>
      </div>
      <!-- @click="goPage(item) -->
      <div class="movies-list">
         <div v-for="item in films" :key="item.id" class="movie-item" @click="goPage(item.id)" >
          <img :src="item.movieHead" alt="">
          <p>{{item.movieName}}</p>
        </div> 
      </div>
    </div>
    <MyFooter></MyFooter>
  </div>
</template>

<script>
  import MyFooter from '../../components/MyFooter.vue'
  export default {
  components: { MyFooter },
  data(){
    return {
      radio: 1,
      radio1:'全部',
      radio2:'全部',
      films:[]
    }
  },
  methods:{
    goPage(id){
      // console.log(id);
      this.$router.push({name:'FilmDetail',query:{filmId:id}})
    },
    Change(value){
      // console.log(value);
      if(value===2){
        var data2={
            "page":{
                "current":1,
                "size":16
            },
            "sortRuleType":{
              "sortRule":'movie_start'
            }
      }
      this.$api.getPlayList(data2).then(res=>{
        if(res.data.code===0){
          this.films=res.data.data.records
        }
      })
      }else if(value===3){
        var data3={
            "page":{
                "current":1,
                "size":16
            },
            "sortRuleType":{
              "sortRule":'movie_score'
            }
      }
      this.$api.getPlayList(data3).then(res=>{
        if(res.data.code===0){
          this.films=res.data.data.records
        }
      })
      }else {
        var data1={
            "page":{
                "current":1,
                "size":16
            },
            "sortRuleType":{
              "sortRule":'movie_money'
            }
      }
      this.$api.getPlayList(data1).then(res=>{
        if(res.data.code===0){
          this.films=res.data.data.records
        }
      })
      }
    }
  },
  created(){
    var data1={
            "page":{
                "current":1,
                "size":16
            },
            "sortRuleType":{
              "sortRule":'movie_money'
            }
    }
    this.$api.getPlayList(data1).then(res=>{
      if(res.data.code===0){
          this.films=res.data.data.records
        }
    })
  }
  }
</script>

<style scoped>
.subnav {
  text-align: center;
  height: 60px;
  margin:0 auto;
  background-color:#ccc;
}
.subnav .navbar {
  display: inline-block;
  overflow: hidden;
}
.subnav .navbar li {
  float: left;
}
.subnav .navbar li a:hover{
  color: #ef4238;
}
.subnav .navbar li a {
    display: block;
    font-size: 16px;
    color: #999;
    height: 60px;
    line-height: 60px;
    padding: 0 40px;
}

/* classify */
.container {
  width: 1200px;
  margin:0 auto;
}
.tag-panel {
  margin:10px 0;
  border:1px solid #ccc
}
.tag-panel {
  padding: 10px;
}
.sorter {
  margin:15px 0;
}
.movies-list {
  display: flex;
  flex-wrap:wrap;
  justify-content:space-between;
}
.movie-item{
flex:1;
border: 1px solid #efefef;
margin-left: 30px;
margin-top:20px;
}
.movie-item img {
  width: 218px;
  height: 300px;
}
.movie-item p {
  text-align: center;
}

</style>