//请求的方法
import axios from 'axios'
import base from './base'
// axios.defaults.baseURL = 'http://124.71.197.6:8080'
axios.defaults.baseURL = 'http://124.71.197.6:80'
// axios.defaults.baseURL = 'http://192.168.43.225:8080'

const api = {
  //买票
  buyTicket(data) {
    return axios.post('/userOrder/new/buyTicket', data)
  },
  //获取手机验证码
  getCode(data) {
    return axios.post('/login/codePhoneSend', data)
  },
  //注册
  register(data){
    return axios.post('/register',data)
  },
  //获取演出厅列表
  getHallList(data) {
    return axios.post('/movieHall', data)
  },
  //删除演出厅
  deleteHall(params) {
    return axios.delete('/movieHall',{
      params
    })
  },
  // 查找演出厅
  searchHall(data){
    return axios.post('/movieHall',data)
  },
  //添加座位
  addHall(data){
    return axios.post('/movieHall/new',data)
  },
  // 获取座位数据
  getAllSeat(data) {
    return axios.post('/hallSeat', data
    )
  },
  //删除座位
  deleteSeat(params) {
    return axios.delete('/hallSeat', {
      params
    })
  },
  //查找座位
  searchSeat(data){
    return axios.post('/hallSeat',data)
  },
  //添加座位
  addSeat(data){
    return axios.post('/hallSeat/new',data)
  },

  // 获取用户列表
  getAllUsers(params) {
    return axios.post('/users', params)
  },
  //根据id删除用户信息
  deleteUser(params) {
    return axios.delete('/users', { params })
  },
  //查询用户信息
  searchUser(data){
    return axios.post('/users',data)
  },

  //根据获取用户信息
  getUserInfo() {
    return axios.get('/users/getUserInfo')
  },
  //添加用户
  addUser(data){
    return axios.post('/users/new',data)
  },
  // 根据id查询影厅
  hallId(params) {
    return axios.post('/movieHall',
      params
    )
  },
  // 根据密码登录
  //"phoneNum" "password"
  byPassword(json) {
    return axios.post(base.byPassword, json, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
  },
  // 获取订单列表
  getTicketList(params) {
    return axios.post('/userOrder', params)
  },
  //获取我的订单列表
  getOrder(){
    return axios.get('/userOrder/getUserOrders')
  },
  //删除我的订单信息
  deleteOrder(params){
    return axios.delete('/userOrder/returnTicket',{
      params
    })
  },
  //删除订单
  deleteTicket(params){
    return axios.delete('/userOrder',{
      params
    })
  },
  //获取演出计划列表
  getPlanList(params) {
    return axios.post('/moviePlan',
      params,
    )
  },
  // 删除演出计划
  deletePlan(params) {
    return axios.delete('/moviePlan', {
      params
    })
  },
  // 增加演出计划
  addPlan(data){
    return axios.post('/moviePlan/new',data)
  },
  // 查找演出计划
  searchPlan(data){
    return axios.post('/moviePlan',data)
  },

  //获取电影列表
  getPlayList(params) {
    return axios.post('/movie',
      params,
    )
  },
  //按照规则查找电影
  bySort(data){
    return axios.post('/movie/sort',data)
  },
  //搜索电影数据数据
  getSearch(data) {
    return axios.post('/movie',data)
  },
  // 删除电影按照电影名
  deleteMovie(params) {
    return axios.delete('/movie', {
      params
    })
  },
  // 添加电影
  addMovies(data) {
    return axios.post('/movie/new', data)
  },
}


axios.interceptors.request.use(
  config => {
    if (localStorage.getItem('user') == null) {
      // window.location.href = '/loginPage'
    } else {

      config.headers['token'] = JSON.parse(localStorage.getItem('user')).token
    }

    return config
  })
export default api