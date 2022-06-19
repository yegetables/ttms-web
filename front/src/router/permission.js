import router from './index'
//获取vuex数据
import store from '../store/index'

// 路由拦截
router.beforeEach((to, from, next) => {
  // console.log('---to---', to);
  //判断是否需要登录
  if (to.matched.some(ele => ele.meta.isLogin)) {
    //判断用户是否已经登录
    // let token = store.state.loginModule.userinfo.token;
    // if (token) {
    //   next()
    // } else {
    //   next('/login')
    // }
    next('/login')
  } else {//不需要登录
    next()
  }
})