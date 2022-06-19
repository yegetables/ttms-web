<template>
  <div class="purchase">
    <h3 class="header" style="color:#ef4238">购票页面</h3>
    <div class="detail">
      <div class="left">
        <img :src="$route.query.film.movieHead" alt="">
        <p>{{$route.query.film.movieName}}</p>
        <p>类型</p>
      </div>
      <div class="right">
        <div>
          日期：
          <el-select v-model="date" placeholder="请选择" @change="change(date)">
            <el-option
              v-for="item in dates"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
            </el-select>
        </div>
        <div>
          影厅类型：{{this.movieHall.movieType}}
        </div>
        <div>
          影厅名：{{this.movieHall.hallName}}
        </div>
        <div>
          行号：
          <el-select class="row" v-model="row" placeholder="请选择">
            <el-option
              v-for="item in rows"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <br>
          列号：
          <el-select class='col' v-model="col" placeholder="请选择">
            <el-option
              v-for="item in cols"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>

        </div>
        <div class="sum">
          合计:
          <span>{{planAndHall.ticketMoney}}元</span>
        </div>
        <div class="buy">
          <button @click="buy">购票</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { number } from 'echarts';
  export default {
    data(){
      return {
        dates: [],
        date: '',
        rows:[
          {
            value:1,
            label:1
          },
          {
            value:2,
            label:2
          },
          {
            value:3,
            label:3
          },
          {
            value:4,
            label:4
          },
          {
            value:5,
            label:5
          },
        ],
        row:'',
        cols:[
          {
            value:1,
            label:1
          },
          {
            value:2,
            label:2
          },
          {
            value:3,
            label:3
          },
          {
            value:4,
            label:4
          },
          {
            value:5,
            label:5
          },
        ],
        col:'',
        records:[],
        planAndHall:{},
        movieHall:{}
      }
      
    },
    methods:{
      // 选择演出时间之后触发的事件
      change(index)
      {
        console.log(this.records[index]);
        this.planAndHall=this.records[index]
        console.log( 'plan',this.planAndHall);
        let data={
          page: {
            current: 1, 
            size: 1 
          },
          movieHall:{
            id:this.planAndHall.hallId
          } 
        }
        this.$api.getHallList(data).then(
          res=>{
            if(res.data.code===0){
              this.movieHall=res.data.data.records[0]
              console.log('moviehall',this.movieHall);
              this.rows=[]
              this.cols=[]
              for(let i=0;i<this.movieHall.seatLine;i++){
                let obj={
                  value:i+1,
                  label:i+1
                }
                this.rows.push(obj)
              }
              for(let i=0;i<this.movieHall.seatColumn;i++){
                let obj={
                  value:i+1,
                  label:i+1
                }
                this.cols.push(obj)
              }
            }
          }
        )
      },
      buy(){
       let data={
         userOrderDto:{
           orderMoney:this.planAndHall.ticketMoney,
           planId:this.planAndHall.id
         },
         hallSeatDto:{
           seatLine:parseInt(this.row),
           seatColumn:parseInt(this.col)
         }
        }
        this.$api.buyTicket(data).then(
          res=>{
            // console.log(res);
            if(res.data.code===0){
              
              this.$message({
              message: '恭喜你，购票成功',
              type: 'success'
              });
              this.$router.push('/profile')
            }else if(res.data.code===-1){
              this.$message.error('购票失败，请重试座位已售罄')
            }
          }
        )
      }
    },
    created(){
      let movieId=this.$route.query.film.id
      let data={
          page: {
            current: 1, 
            size: 5 
          },
          moviePlan:{
            cinemaMovieId:movieId
          }
        }
        this.$api.getPlanList(data).then(res=>{
          if(res.data.code===0){
            // console.log(res);
            this.records=res.data.data.records
            for(let i=0;i<this.records.length;i++){
              let value=i
              let label=this.records[i].movieStartTime+' '+this.records[i].movieEndTime
              // console.log(value + '----'+label);
              this.dates.push({
                value,
                label
              })
            }
            // console.log('当前',this.dates);
          }
        })
    }
  }
</script>

<style scoped>
.purchase {
  height: 450px;
  background: url(../../assets/back3.png) no-repeat;
  background-size: 100%;
}
.row {
  margin: 20px 0;
}
.col {
  margin-bottom: 10px;
}
.header {
  margin:32px 680px;
}
.detail {
  display: flex;
  width: 800px;
  margin:10px auto;
}
.left {
  width: 150px;
  text-align: center;
}
.left img{
  width: 50%;
  border: 1px solid #ccc;
}
.detail div {
  flex:1;
}

.sum span {
  font-size: 18px;
  color:#ef4238;
}
.buy  button{
  color:#fff;
  width: 100px;
  height: 40px;
  background-color: #ef4238;
}
</style>