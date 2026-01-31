//全局通用页面路由


export default [
    {
        path: '/',
        component: () => import('@/layouts/MainLayout.vue'),
        redirect: '/index',
        children: [
            {
                path: '/index',
                name: '主页',
                component: () => import('@/views/common/IndexView.vue'),
                meta: {
                    isPublic: true, // 公开路由，无需验证权限
                    role: [],// 所有角色均可访问
                }
            }
        ]
    }
]