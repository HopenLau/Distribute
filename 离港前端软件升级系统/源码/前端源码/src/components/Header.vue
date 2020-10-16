<!--主页头部-->
<template>
    <div id="header">
        <!--首页-->
        <div class="logo">
            <span class="mainTag">首页</span>
            <span class="splitLine">/</span>
            <span class="secondTag">{{$store.state.menuName}}</span>
        </div>
        <div class="header-right">
            <div class="header-user-con">
                <!-- 用户头像 -->
                <div class="user-avator">
                    <img v-show="imgShow == 0" src="../assets/images/cat.jpg" @click="changeImg" />
                    <img v-show="imgShow == 1" src="../assets/images/dog.jpg" @click="changeImg" />
                </div>
                <!-- 用户名下拉菜单 -->
                <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{userName}}
                        <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item divided command="loginout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Header",
        data() {
            return {
                userName: '超级管理员',
                menuName: '版本管理',
                imgShow: 0
            };
        },
        methods: {
            // 用户名下拉菜单选择事件
            handleCommand(command) {
                if (command == 'loginout') {
                    localStorage.setItem('token', '');
                    this.$router.push('login');
                }
            },
            changeImg() {
                this.imgShow = (this.imgShow === 0) ? 1 : 0;
            }

        }
    }
</script>

<style scoped>
#header {
    width: 88%;
    margin-left: 12%;
    display: block;
    box-sizing: border-box;
    height: 70px;
    font-size: 22px;
    border-bottom: 1px solid #ebebeb;
    background-color: white;
}
#header .logo {
    padding-left: 1%;
    float: left;
    line-height: 70px;
    display: block;
    font-size: 16px;
}
#header .logo .mainTag {
    color: #606266;
}
#header .logo .splitLine {
    margin: 0 10px;
    color: #a1a1a1;
}
#header .logo .secondTag {
    color: #a1a1a1;
}
.header-right {
    float: right;
    padding-right: 50px;
}
.header-user-con {
    display: flex;
    height: 70px;
    align-items: center;
}
.user-name {
    margin-left: 10px;
 }
.user-avator {
    margin-left: 20px;
}
.user-avator img {
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    cursor: pointer;
}
</style>