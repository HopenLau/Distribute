<!--主页-->
<template>
  <div id="home">
    <Menu></Menu>
    <Header></Header>
    <Tags></Tags>
    <div class="content">
      <transition name="move" mode="out-in">
        <keep-alive :include="tagsList">
          <router-view></router-view>
        </keep-alive>
      </transition>
      <el-backtop target=".content"></el-backtop>
    </div>
  </div>
</template>

<script>
import Menu from "@/components/Menu.vue";
import Header from "@/components/Header.vue";
import Tags from "@/components/Tags.vue";
import transfer from '../assets/js/transfer.js';

export default {
  name: "Home",
  data() {
    return {
      tagsList: [{
        title: "首页",
        name: "首页"
      }]
    };
  },
  components: {
    Menu,
    Header,
    Tags
  },
  created() {
    // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
    transfer.$on('tags', msg => {
      let arr = [];
      for (let i = 0, len = msg.length; i < len; i++) {
        msg[i].name && arr.push(msg[i].name);
      }
      this.tagsList = arr;
    });
  }
};
</script>

<style scoped>
  #home {
    width: 100%;
    height: 100%;
  }

  .content {
    width: 88%;
    height: 94%;
    padding: 10px;
    overflow-y: auto;
    box-sizing: border-box;
    float: right;
    background-color: lightgrey;
  }
</style>