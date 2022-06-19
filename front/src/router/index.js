import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout/index'
import Login from '../views/Login/Login'
import Home from '../views/Home/index'
// 前台
import Main from '../views/Main/Main'
import MainContent from '../views/Main/MainContent'
// 前台异步
const Profile = () => import('../views/Profile/index')
const List = () => import('../views/List/List')
const Film = () => import('../views/Film/Film')
const Login2 = () => import('../views/Login/Login2')
const Register = () => import('../views/Register/Register')
const Purchase = () => import('../views/Film/Purchase')
const MyOrder=()=>import('../views/Profile/MyOrder')
const Base=()=>import('../views/Profile/Base')


// 后台管理异步
const Plan = () => import('../views/Plan/Plan');
const Play = () => import('../views/Play/Play');
const Hall = () => import('../views/Hall/Hall');
const Seat = () => import('../views/Seat/Seat');
const User = () => import('../views/User/User');
const Ticket = () => import('../views/Ticket/Ticket');
const TicketList = () => import('../views/Ticket/TicketList/TicketList');
const TicketBack = () => import('../views/Ticket/TicketBack/TicketBack');


//子级路由
const FilmDetail = () => import('../views/Film/FilmDetail');
const AddPlay = () => import('../views/Play/AddPlay');
const AddSeat = () => import('../views/Seat/AddSeat');
const AddPlan = () => import('../views/Plan/AddPlan');
const AddHall = () => import('../views/Hall/AddHall');
const AddUser = () => import('../views/User/AddUser');

Vue.use(VueRouter)

const routes = [
  {
    path: '/admin',
    component: Layout,
    //路由元信息
    meta: {
      isLogin: true
    },
    children: [
      {
        path: '/admin/',
        name: 'Home',
        component: Home
      },
      {
        path: '/admin/plan',
        name: 'Plan',
        component: Plan
      },
      {
        path: '/admin/add-seat',
        name: 'AddSeat',
        component: AddSeat
      },
      {
        path: '/admin/add-hall',
        name: 'AddHall',
        component: AddHall
      },
      {
        path: '/admin/add-play',
        name: 'AddPlay',
        component: AddPlay
      },
      {
        path: '/admin/add-plan',
        name: 'AddPlan',
        component: AddPlan
      },
      {
        path: '/admin/add-user',
        name: 'AddUser',
        component: AddUser
      },
      {
        path: '/admin/play',
        name: 'Play',
        component: Play
      },
      {
        path: '/admin/hall',
        name: 'Hall',
        component: Hall
      },
      {
        path: '/admin/user',
        name: 'User',
        component: User
      },
      {
        path: '/admin/ticket',
        name: 'Ticket',
        component: Ticket,
        redirect: '/admin/ticket/ticket-list',
        children: [
          {
            path: 'ticket-list',
            component: TicketList
          },
          {
            path: 'ticket-back',
            component: TicketBack
          }
        ]
      },
      {
        path: '/admin/seat',
        name: 'Seat',
        component: Seat
      }

    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
    name: 'Main',
    component: Main,
    children: [
      {
        path: '/',
        name: 'MainContent',
        component: MainContent
      },
      {
        path: '/list',
        name: 'List',
        component: List
      },
      {
        path: '/login2',
        name: 'Login2',
        component: Login2
      },
      {
        path: '/film',
        name: 'Film',
        component: Film,
        children: [
        ]
      },
      {
        path: '/detail',
        name: 'FilmDetail',
        component: FilmDetail,
      },
      {
        path: '/purchase',
        name: 'Purchase',
        component: Purchase,
      },
      {
        path: '/register',
        name: 'Register',
        component: Register
      },
      {
        path: '/profile',
        name: 'Profile',
        component: Profile,
        redirect:'/profile/myorder',
        children:[
          {
            path:'/profile/myorder',
            name:'myorder',
            component:MyOrder
          },
          {
            path:'/profile/base',
            name:'Base',
            component:Base
          }
        ]
      }
    ]
  }
]

const router = new VueRouter({
  routes
})


export default router
