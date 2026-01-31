//登录相关路由

export default [
     {
        path:'/login',
        name:'登录页面',
        component:()=>import('@/views/login/LoginView.vue'),
        meta:{
            isPublic:true, // 公开路由，无需验证权限
            role:[] ,// 所有角色均可访问
        }
    }
]