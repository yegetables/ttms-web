<template>
  <div class="home">
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div id="main" style="width: 500px;height:400px;background-color: #fff;border: 1px solid #ccc;"></div>
  </div>
</template>

<script>
  export default {
    data(){
      return{
        newdata:[]
      }
    },
    mounted(){
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
              let data=res.data.data.records
              for(let i=0;i<data.length;i++){
                let obj={value:0,name:""}
                obj.value=data[i].dayMoney
                obj.name=data[i].movieName
                this.newdata.push(obj)
              }
              console.log('newdata',this.newdata);
            }
        })
      this.circle()
    },
    methods:{
      circle(){
        var chartDom = document.getElementById('main');
        var myChart = this.$echarts.init(chartDom);
        var option;
        option = {
          title: {
          text: '票房',
          subtext: 'data',
          left: 'center'
          },
          tooltip: {
          trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              name: '电影',
              type: 'pie',
              radius: '50%',
              data:[
                {value:1900,name:'怦然心动'},
                {value:2000,name:'肖申克的救赎'},
                {value:1560,name:'泰坦尼克号'},
                {value:1006,name:'大话西游'},
                {value:1906,name:'熊出没'},
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        // option.series[0].data=this.newdata
        // console.log('opt',option);
        option && myChart.setOption(option);
      }       
    }
  }
</script>

<style scoped>
.home {
  margin: 20px;
}
</style>