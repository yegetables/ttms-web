export default {
  namespaced: true,
  state: {
    userinfo: {
      phoneNum: '',
      token: ''
    }
  },
  mutations: {
    //设置用户信息
    setUser(state, payload) {
      state.userinfo = payload
    },
    //清空
    clearUser(state) {
      state.userinfo = {
        phoneNum: '',
        token: ''
      }
    }
  },
  actions: {

  },
  getters: {

  }
}