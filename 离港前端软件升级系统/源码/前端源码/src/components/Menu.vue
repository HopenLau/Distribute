<template>
    <div id="menu">
        <el-menu class="el-menu-vertical-demo" :collapse="isCollapse"
                 active-text-color="#20a0ff"
                 background-color="#324157"
                 text-color="#bfcbd9">
            <!--数据循环生成导航菜单栏-->
            <el-menu-item v-for="m in menuList" :index="m.index" :key="m.index" @click="btnClick(m.title)">
                <i :class="m.icon"></i>
                <span slot="title">{{m.title}}</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    import transfer from '../assets/js/transfer.js';
    export default {
        name: "Menu",
        data() {
            return {
                isCollapse: this.$store.state.menuCollapse,
                menuList: [{
                    icon: "el-icon-document",
                    path: "/version",
                    title: "版本管理",
                },
                    {
                        icon: "el-icon-document",
                        path: "/counter",
                        title:'柜台信息'
                    },
                    {
                        icon: "el-icon-document",
                        path: "/log",
                        title:'日志信息'
                    }
                ]
            }
        },
        methods: {
            btnClick(title) {
                // eslint-disable-next-line no-unused-vars
                this.menuList.forEach((m, index) => {
                    if (m.title === title) {
                        transfer.$emit('menu', m);
                    }
                });
                this.$store.dispatch('menuNameActionFun', title);
            }

        }
    }
</script>

<style scoped>
#menu {
    width: 12%;
    height: 100%;
    display: block;
    position: absolute;
    overflow: hidden;
}

#menu > ul {
    width: 100%;
    height: 100%;
}

.el-menu-item {
    text-align: left;
}
</style>