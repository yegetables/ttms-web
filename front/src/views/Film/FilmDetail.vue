<template>
  <div>
    <div class="banner">
      <div class="left">
        <img :src="film.movieHead" alt="">
      </div>
      <div class="right">
        <div class="brief">
          <h1>{{film.movieName}}</h1>
          <ul>
            <li>{{film.movieType}}</li>
            <li>{{film.movieArea}}/{{film.movieMinute}}分钟</li>
            <li>{{film.movieStart}}上映</li>
          </ul>
        </div>
        <div class="action">
          <div class="top">
            <a href="#">想看</a>
            <a href="#">评分</a>
          </div>
          <a href="#" class="buy" @click="buyTicket(film)">特惠购票</a>
        </div>
        <div class="status">
          <div>
            <p>猫眼口碑</p>
            <div>
              {{film.movieScore}}
            </div>
          </div>
          <div>
            <p>累计票房</p>
            <div>
              {{film.movieMoney}}
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="detail">
      <h2>剧情介绍</h2>
      <p>
        {{film.movieBrief}}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      film: {}
    }
  },
  methods:{
    buyTicket(item){
        this.$router.push({name:'Purchase',query:{film:item}})
    }
  },
  created() {
    let filmId = this.$route.query.filmId
    // console.log(filmId);
    let data = {
      page: {
        current: 1,
        size: 1
      },
      movie:{
        id: filmId
      }
    }
    this.$api.getPlayList(data).then(
      res=>{
        if(res.data.code===0){
          // console.log(res.data.data.records);
          this.film=res.data.data.records[0]
        }
      }
    )
  }
}
</script>

<style scoped>
.banner {
  width: 100%;
  height: 350px;
  background: url(../../assets/back2.jpg) no-repeat;
  background-size: 100%;
}

.left {
  margin-left: 200px;
  margin-top: 60px;
  float: left;
  width: 200px;
}

img {
  width: 100%;
}

.right {
  width: 800px;
  position: relative;
  color: #fff;
  float: left;
  margin: 50px;
}

.brief {
  margin-bottom: 30px;
}

.brief h1 {
  margin-bottom: 20px;
}

.brief ul li {
  margin-bottom: 5px;
}

.status {
  position: absolute;
  top: 150px;
  left: 250px;
}

.status {
  font-size: 24px;
}

.status p {
  font-size: 14px;
}

.top a {
  cursor: pointer;
  display: inline-block;
  width: 100px;
  height: 25px;
  background-color: #7d8961;
  margin-right: 10px;
  padding-top: 11px;
  text-align: center;
  font-size: 14px;
  color: #fff;
  border-radius: 2px;
}

.buy {
  cursor: pointer;
  display: inline-block;
  color: #fff;
  background-color: #dad265;
  margin-top: 10px;
  width: 210px;
  height: 40px;
  font-size: 16px;
  line-height: 40px;
  text-align: center;
  border-radius: 2px;
  padding: 0;
}

.detail {
  width: 1150px;
  margin: 20px auto;
}

.detail h2 {
  font-weight: 400;
  margin-bottom: 20px;
}
</style>