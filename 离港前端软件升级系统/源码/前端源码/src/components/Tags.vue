<template>
    <div id="tags">
        <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
            <el-tab-pane :key="item.name" v-for="item in editableTabs"
                :label="item.title" :name="item.name">
                <router-link :to="item.path" class="tags-li-title"></router-link>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import transfer from '../assets/js/transfer.js';
    export default {
        name: "Tags",
        data() {
            return {
                editableTabs: [{
                    title: "版本管理",
                    path: "/version",
                    name: "版本管理"
                }],
                editableTabsValue: "版本管理",
                addMenuPath: ""
            }
        },
        mounted() {
            const that = this;
            transfer.$on('menu', function(m) {
                that.addMenuPath = m.path;
                that.addTab(m.title);
            });
        },
        watch: {
            'editableTabsValue' : function() {
                let tabs = this.editableTabs;
                let newTabName = this.editableTabsValue;
                // eslint-disable-next-line no-unused-vars
                tabs.forEach((tab, index) => {
                    if (tab.name === newTabName) {
                        this.$store.dispatch('menuNameActionFun', newTabName);
                        this.$router.push(tab.path);
                    }
                });
            }
        },
        methods: {
            //添加导航栏中页面显示框方法
            addTab(newTabName) {
                let tabs = this.editableTabs;
                let bol = true;
                let path = '';
                // eslint-disable-next-line no-unused-vars
                tabs.forEach((tab, index) => {
                    if (tab.name === newTabName) {
                        bol = false;
                        path = tab.path;
                    }
                });
                if(bol) {
                    path = this.addMenuPath;
                    this.editableTabs.push({
                        title: newTabName,
                        path: path,
                        name: newTabName
                    });
                }
                this.editableTabsValue = newTabName;
                this.$store.dispatch('menuNameActionFun', newTabName);
                this.$router.push(path);
                transfer.$emit('tags', this.editableTabs);
            },
            //移除导航栏中页面显示框方法
            removeTab(targetName) {
                let tabs = this.editableTabs;
                let activeName = this.editableTabsValue;
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                            } else {
                                activeName = "版本管理";
                                this.addMenuPath = '/version';
                            }
                        }
                    });
                }
                this.editableTabsValue = activeName;
                this.editableTabs = tabs.filter(tab => tab.name !== targetName);
                this.addTab(activeName);
            }
        },
        created() {
            this.addTab(this.editableTabs[0].title);
        }
    }
</script>

<style scoped>
#tags {
    width: 88%;
    margin-left: 12%;
    display: block;
}

.el-tabs {
    background-color: white;
    height: 100%;
}
</style>