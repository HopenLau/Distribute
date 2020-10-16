import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);
//主页右侧显示列表栏，点击跳转到子页方法
const routes = [
    {
        path: "/",
        name: "home",
        component: () =>
            import("../pages/Home.vue"),
        meta: {hidden: true, title: "首页"},

        children: [{
            path: '/version',
            component: () => import('../pages/Version.vue'),
            meta: {
                title: '版本管理'
            },
        },
            {
                path: '/versionFile',
                component: () => import('../pages/versionFile'),
                meta: {
                    title: '文件信息'
                }
            },
            {
                path: '/log',
                component: () => import('../pages/log.vue'),
                meta: {
                    title: '日志信息'
                }
            },
            {
                path: '/counter',
                component: () => import('../pages/counter.vue'),
                meta: {
                    title: '柜台信息'
                }
            },
            {
                path: '/version',
                component: () => import('../pages/Version.vue'),
                meta: {
                    title: '返回主页'
                }
            },
            {
                path:'/versionFile',
                component:() =>import('../pages/versionFile.vue'),
                meta:{
                    title:'保存'
                }
            }
        ]
    },
    {
        path: '/login',
        component: () => import('../pages/Login.vue'),
        meta: {
            title: '登录页面'
        }
    }
]

const router = new VueRouter({
  routes
});

export default router;