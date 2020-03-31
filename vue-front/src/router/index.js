import Vue from 'vue'
import Router from 'vue-router'

import myFirstVue from '@/views/myFirstVue.vue'
import userLoginVue from '@/views/userLoginCheck.vue'

Vue.use(Router);

// route是一个对象，多个route组成一个routes
const routes = [
  {
    path: '/',
    name: 'myFirstVue',
    component: myFirstVue
  },
  {
    path: '/loginCheck',
    name: '454',
    component: userLoginVue
  }];

// 创建router对路由进行管理，由构造函数 new Router()创建，接收routes参数
const router = new Router({
  routes: routes // 简写：routes
});

//路由拦截器，to代表要去的路由指向，from是指从哪个路由跳转过来的，next是判断操作，如果为空，表示放行
router.beforeEach((to, from, next) => {
  console.log(to, " _to - from_ ", from);
  if (localStorage.getItem("token") || to.path === "/loginCheck") {   //如果有token或者当前为登录页放行
    console.log("this is if");
    next();
  } else {    //如果不满足判断条件，跳转指定页面
    console.log("this is else");
    next({
      path: '/loginCheck'
    });
  }
  next();
});

export default router;
