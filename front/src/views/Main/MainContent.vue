<template>
  <div>
        <!--轮播图-->
        <div class="homePage">
            <div class="carousel">
                <div class="carousel-set">
                    <div class="carousel-content">
                        <img src="../../assets/lion.webp">
                    </div>
                    <div class="carousel-content">
                        <img src="../../assets/8.webp">
                    </div>
                    <div class="carousel-content">
                        <img src="../../assets/9.webp">
                    </div>
                </div>
            </div>
        </div>
        <div>
            <!--热映电影一览-->
            <div class="movieSty">
                <h2 style="color: #ef4238;">正在热映</h2>
                <ul>
                    <li v-for="item in hotfilms" :key="item.id">
                        <div class="pic">
                            <img width="150px" height="220px" :src="item.movieHead">
                            <div class="filmname">{{item.movieName}}</div>
                        </div>
                        <a href="#" style="text-decoration: none;">
                            <div class="btn" @click="buyTicket(item)">购票</div>
                        </a>
                    </li>
                </ul>
            </div>
            <!--今日票房-->
            <div class="rank">
                <h2 style="color: #ef4238;">今日票房</h2>
                <ul>
                    <li v-for="(item,index) in sortfilms" :key="item.id">
                        <i style="color: #ef4238;">{{index+1}}</i>
                        <span>{{item.movieName}}</span>
                        <i style="float: right; color:#ef4238;">{{item.dayMoney}}</i>
                    </li>
                </ul>
            </div>
            <!--即将上映-->
            <div class="movieSty">
                <h1 style="color: dodgerblue;">即将上映</h1>
                <ul>
                    <li v-for="item in prefilms" :key="item.id">
                        <div class="pic">
                            <img width="150px" height="220px" :src="item.movieHead">
                        </div>
                        <div class="btn2">预约</div>
                    </li>  
                </ul>
            </div>
            <!--最受期待-->
            <div class="rank">
                <h1 style="color: coral;">最受期待</h1>
                <ul>
                    <li v-for="(item,index) in wantfilms" :key="item.id">
                        <i style="color: gray;">{{index+1}}</i>
                        <span>{{item.movieName}}</span>
                        <i style="float: right; color:coral;">{{item.wantLook}}人想看</i>
                    </li>
                    
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
  import MyFooter from '../../components/MyFooter.vue'
  export default {
    data(){
        return {
            hotfilms:[],//热门电影
            prefilms:[],//预售电影
            sortfilms:[],//按照票房排行
            wantfilms:[]//按照想看值排行
        }
    },
    components:{
      MyFooter
    },
    created(){
        let data1={
            "page":{
                "current":1,
                "size":8
            }
        }
        this.$api.getPlayList(data1).then(res=>{
            if(res.data.code===0){
                this.hotfilms=res.data.data.records
                // console.log(this.hotfilms);
            }
        })
        let data2={
            "page":{
                "current":2,
                "size":8
            }
        }
        this.$api.getPlayList(data2).then(res=>{
            if(res.data.code===0){
                this.prefilms=res.data.data.records
                // console.log(this.hotfilms);
            }
        })
        let data3={
            "page":{
                "current":1,
                "size":5
            },
            "sortRuleType":{
                "sortRule":'day_money'
            }
        }
        this.$api.getPlayList(data3).then(res=>{
            if(res.data.code===0){
                this.sortfilms=res.data.data.records
                // console.log('日票房',this.sortfilms)
            }
        })
        var data4={
            "page":{
                "current":1,
                "size":5
            },
            "sortRuleType":{
                "sortRule":'want_look'
            }
        }
        this.$api.getPlayList(data4).then(res=>{
            if(res.data.code===0){
                this.wantfilms=res.data.data.records
                // console.log('想看',this.wantfilms)
            }
        })
    },
    methods:{
        buyTicket(item){
            this.$router.push({name:'Purchase',query:{film:item}})
        }
    }
  }
</script>

<style scoped>
        /*热映电影*/
        
        .movieSty {
            float: left;
            padding: 0;
            width: 725px;
            height: 600px;
            margin-top: 100px;
            margin-left: 150px;
        }
        
        .movieSty ul {
            list-style: none;
            padding: 0;
        }
        
        .movieSty ul li {
            float: left;
            width: 150px;
            height: 250px;
            margin-right: 25px;
            border: 1px solid rgb(208, 197, 197);
        }
        
        .pic {
            width: 150px;
            height: 220px;
        }
        
        .btn {
            width: 150px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            color: #ef4238;
        }
        
        .btn:hover {
            background-color: #ef4238;
            color: white;
        }
        
        .btn2 {
            width: 150px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            color: dodgerblue;
        }
        
        .btn2:hover {
            background-color: dodgerblue;
            color: white;
        }
        /*轮播图页头*/
        
        .homePage {
            margin-left: 200px;
            padding: 0;
            width: 1100px;
            height: 500px;
            /* background-color: black; */
        }
        
        .carousel {
            width: 1100px;
            height: 400px;
            overflow: hidden;
            position: relative;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        
        .carousel-set {
            width: 3900px;
            height: 400px;
        }
        
        .carousel-content {
            width: 1300px;
            height: 400px;
            float: left;
        }
        
        .carousel-content>img {
            width: 100%;
            height: 100%;
        }
        
        @keyframes playMovie {
            0% {
                margin-left: 0px;
            }
            10% {
                margin-left: 0px;
            }
            30% {
                margin-left: 0px;
            }
            40% {
                margin-left: -1300px;
            }
            50% {
                margin-left: -1300px;
            }
            70% {
                margin-left: -1300px;
            }
            80% {
                margin-left: -2600px;
            }
            90% {
                margin-left: -2600px;
            }
            100% {
                margin-left: -2600px;
            }
        }
        
        .carousel-set {
            animation: playMovie 15s linear infinite;
        }
        /*今日热映*/
        
        .rank {
            float: right;
            padding: 0;
            width: 400px;
            height: 400px;
            margin-right: 150px;
            margin-top: 100px;
        }
        
        .rank ul {
            padding: 0;
            list-style: none;
        }
        
        .rank ul li {
            margin-bottom: 10px;
            width: 350px;
        }
        
        .rank ul li i {
            font-size: 22px;
            text-align: center;
            line-height: 50px;
        }
        
        .rank ul li span {
            font-size: 20px;
            text-align: center;
            line-height: 50px;
        }
        .pic {
            position: relative;
        }
        .pic .filmname {
            position: absolute;
            bottom:5px;
            left:30px;
            color:#fff;
        }
</style>