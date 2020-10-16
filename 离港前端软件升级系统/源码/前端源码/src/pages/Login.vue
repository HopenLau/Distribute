<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title"></div>
            <el-form :model="param" ref="login" label-width="0px" class="ms-content">
                <!--用户名输入框-->
                <el-form-item prop="userId">
                    <el-input v-model="param.userId" placeholder="用户名"  @keydown.native="keyUpFun($event)">
                        <el-button slot="prepend" icon="el-icon-user-solid"></el-button>
                    </el-input>
                </el-form-item>
                <!--密码输入框-->
                <el-form-item prop="userPwd">
                    <el-input
                        type="password"
                        ref="password"
                        placeholder="密码"
                        v-model="param.userPwd"
                        @keyup.enter.native="submitForm()"
                    >
                        <el-button slot="prepend" icon="el-icon-lock"></el-button>
                    </el-input>
                </el-form-item>
                <!--登录页面中点击登录按钮-->
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
                <p class="login-tips"></p>
            </el-form>
        </div>
    </div>
</template>

<script>
//引入axios
import axios from "axios";
export default {
    data: function() {
        return {
            param: {
                userId: '',
                userPwd: '',
            }
        };
    },
    methods: {
        //登录方法
        submitForm() {
            //判定是否输入账号和密码
            if (this.param.userId !== "" && this.param.userPwd !== "") {
                const hostName = this.$store.state.host;
                //连接数据库，发送post请求
                axios.post( hostName + 'login', {
                    userId: this.param.userId,
                    userPwd: this.param.userPwd
                }).then(res =>{
                    //登录成功，跳转到主页面
                    if(res.data.resultcode == 0){
                        this.$store.dispatch('userMsgActionFun', res.data);
                        localStorage.setItem('token', res.data.token);
                        this.$store.dispatch('changeLoginActionFun', res.data.token);
                        this.$message.success('登录成功');
                        this.$router.push('/');
                    }
                    //登录失败，显示报错信息
                    else{
                        this.$message.error(res.data.resultmsg);
                    }
                });
                localStorage.setItem('ms_username', this.param.username);
            }
            //未输入账号或密码，提示信息
            else {
                this.$message.error('请输入账号和密码');
                return false;
            }
        },
        keyUpFun(ev){
            if(ev.keyCode == 9) {
                ev.preventDefault();
                this.$refs.password.focus()
            }
        }
    },
};
</script>

<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../../src/assets/images/plan.png);
    background-size: cover;
}
.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 24px;
    color: #454648;
    border-bottom: 1px solid #ddd;

}
.ms-login {
    position: absolute;
    left: 50%;
    top: 60%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}
.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #fff;
}
</style>