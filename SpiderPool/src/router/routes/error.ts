//错误页面路由处理

export default [
    {
        path: '/403',
        name: '无权限访问',
        component: () => import('@/views/error/403.vue'),
        meta: {
            isPublic: true, // 公开路由，无需验证权限
            role: [],// 所有角色均可访问
        }
    },
    {
        path: '/404',
        name: '未找到该页面',
        component: () => import('@/views/error/404.vue'),
        meta: {
            isPublic: true, // 公开路由，无需验证权限
            role: [],// 所有角色均可访问
        }
    },
    {
        path: '/:pathMatch(.*)*', // 匹配所有未定义的路径（含多级嵌套路径）
        redirect: '/404',         // 自动重定向到你的404页面
    }
]