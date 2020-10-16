import Vue from 'vue';
import Vuex from 'vuex';
import VuexAlong from 'vuex-along';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        //这里放全局参数
        menuName: "版本管理",
        user: {},
        versionId: "",
        host: "api/",
        // 存储token
        Authorization: ''
    },
    mutations: {
        //这里是set方法
        menuNameFun(state, data) {
            state.menuName = data;
        },
        userFun(state, data) {
            state.user = data;
        },
        versionIdFun(state, data) {
            state.versionId = data;
        },
        // 修改token，并将token存入localStorage
        changeLoginFun (state, token) {
            state.Authorization = 'Bearer ' + token;
        }
    },
    actions: {
        menuNameActionFun(context, args) {
            context.commit('menuNameFun', args);
        },
        userActionFun(context, args) {
            context.commit('userFun', args);
        },
        versionIdActionFun(context, args) {
            context.commit('versionIdFun', args);
        },
        changeLoginActionFun(context, args) {
            context.commit('changeLoginFun', args);
        }
    },
    modules: {
        //这里是我自己理解的是为了给全局变量分组，所以需要写提前声明其他store文件，然后引入这里

    },
    plugins: [VuexAlong()],

});
