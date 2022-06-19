<template>
  <div>
    <div class="subnav">
        <ul class="navbar">
          <li><a class="active">热映口碑榜</a></li>
          <li><a>最受期待榜</a></li>
          <li><a>国内票房榜</a></li>
          <li><a>北美票房榜</a></li>
          <li><a>TOP100榜</a></li>
        </ul>
    </div>
    
    <div id="app" class="container">
            <div class="wrapper">
                <div class="main">
                    <dl class="board-wrapper">
                        <dd v-for="(item,index) in sortfilms" :key="item.id">
                            <i class="board-index board-index-1">{{index+1}}</i>
                            <a class="image-link" href="" :title="item.movieName" data-act="boarditem-click" data-val="movieId:">
                                <img class="board-img" :alt="item.movieName" :src="item.movieHead">
                            </a>                           
                            <div class="board-item-main">
                                <div class="board-item-content">
                                    <!--电影内容标题演员上映时间-->
                                    <div class="movie-item-info">
                                        <p class="name">{{item.movieName}}</p>
                                        <p class="star">类型{{item.movieType}}</p>
                                        <p class="releasetime">上映时间{{item.movieStart}}</p>
                                    </div>
                                    <!--评分-->
                                    <div class="movie-item-number score-num">
                                        <p class="score">
                                            <i class="integer">{{item.movieScore}}</i>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </dd>
                    </dl>
                </div>
            </div>
      </div>
      <MyFooter></MyFooter>
  </div>
</template>

<script>
import MyFooter from '../../components/MyFooter.vue'
  export default {
    components:{
    MyFooter
  },
  data(){
    return {
      sortfilms:[],
      count:0
    }
  },
  created(){
      var data3={
            "page":{
                "current":1,
                "size":10
            },
            "sortRuleType":{
                "sortRule":'movie_score'
            }
        }
        this.$api.getPlayList(data3).then(res=>{
            if(res.data.code===0){
                this.sortfilms=res.data.data.records
                // console.log(this.sortfilms)
            }
        })
  }
}
</script>

<style scoped>
    .subnav {
    background-color: #47464a;
    height: 60px;
    width: 100%;
    min-width: 1200px;
    text-align: center;
    }    
    .subnav ul li{
        float: left;
        display: inline-block;
    }
    .subnav .navbar {
    display: inline-block;
    overflow: hidden;
    }
    .subnav li, .subnav ul {
    margin: 0;
    padding: 0;
    list-style: none;
    }
    .wrapper {
    overflow: hidden;
    width: 1200px;
    margin: 0 auto;
    }
    .main {
    width: 950px;
    margin: 0 auto;
    margin-top: 0px;
    margin-top: 70px;
    }
    .board-wrapper {
    margin-top: 40px;
    }
    .board-wrapper dd {
    margin-bottom: 30px;
    font-size: 0;
    position: relative;
    overflow: hidden;
    }
    .board-index {
    display: inline-block;
    width: 50px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    background: #f7f7f7;
    font-size: 18px;
    color: #999;
    font-weight: 700;
    position: absolute;
    left: 0;
    top: 85px;
    }
    .board-index-1{
        background-color: rgb(243, 60, 60);
        color: #fff;
    }
    .board-index-2,.board-index-3{
        background-color: #ffb400;
        color: #fff;
    }
    dd{
        display: block;
    }

    dd, dl, h4, li , ul {
    padding: 0;
    margin: 0;
    font-size: 0;
    }
    .board-wrapper .image-link {
    display: inline-block;
    width: 160px;
    height: 220px;
    margin-left: 80px;
    position: relative;
    float: left;
    }
    .board-img {
    vertical-align: middle;
    position: absolute;
    left: 0;
    top: 0;
    }
    img{
        border-style: none;
    }
    .board-item-main{
        display: inline-block;
    }
    .movie-item-info {
    position: absolute;
    top:10px;
    left: 80px;
    width: 440px;
    vertical-align: middle;
    font-size: 16px;
    }
    .board-item-content {
        position: relative;
    }
    .board-item-content .name{
        font-size: 26px;
    }
    .board-item-content .releasetime{
        color: #999;
    }
    .board-item-content {
    font-size: 0;
    line-height: 1;
    display: inline-block;
    vertical-align: middle;
    width: 680px;
    }
    .subnav .navbar li .active {
    color: #ef4238;
    position: relative;
    cursor: default;
    }
    .subnav .navbar li a {
    display: block;
    font-size: 16px;
    color: #999;
    height: 60px;
    line-height: 60px;
    padding: 0 40px;
    }
    .subnav .navbar li a:hover:not(.active) {
        color:azure;
    }
    a{
        text-decoration: none;
        background-color: transparent;
    }
    .score-num .score {
    padding-right: 4px;
    }
    .integer{
        float: right;
        display: block;
        font-weight: 700;
        font-size: 60px;
        color: rgb(241, 182, 73);
    }

</style>