<!--离港前端软件升级系统子页面-版本日志信息-->
<template>
    <div id="box">
        <el-row class="body" v-show="pageSign == 0">
            <div class="condition">
                <!--<el-button icon="el-icon-search" class="el-button&#45;&#45;primary" size="small" @click="queryLog">查 询</el-button>-->
            </div>
            <!--新增版本日志信息，点击跳转到二级页面-->
            <div class="subBtn">
                <el-button class="el-button--success" size="small" @click="addLog">新增日志信息</el-button>
            </div>
            <!--显示页面表格，连接数据库，显示版本日志信息页面表格数据-->
            <el-table border stripe :data="logData" empty-text style="height: 800px">
                <!-- 写死查询表格列-->
                <el-table-column fixed fit align="center" width="150" prop="who" label="版本更新人员"></el-table-column>
                <el-table-column fixed fit align="center" prop="what" label="操作描述"></el-table-column>
                <el-table-column fixed fit align="center" prop="date" label="更新时间"></el-table-column>
                <el-table-column fixed fit align="center" width="200" label="操作">
                    <template slot-scope="scope">
                        <!--日志信息子页面编辑信息按钮-->
                        <el-button type="success" size="small" @click="editLog(scope.row)">编辑</el-button>
                        <!--日志信息子页面删除信息按钮-->
                        <el-button type="danger" size="small" @click="deleteLog(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-row>
            <!--新增日志信息二级页面-->
            <el-row v-show="pageSign == 1">
                <el-col :span="24">
                    <div class="pageEdit">
                        <!--新增日志信息二级页面返回版本日志信息子页按钮-->
                        <el-button circle @click="backBtn">
                            <i style="cursor:pointer;" class="el-icon-back"></i>
                        </el-button>
                        <!--新增日志信息二级页面要输入的内容-->
                        <el-form :model="logForm" :ref="logForm">
                            <el-form-item label="版本操作人员" prop="who">
                                <el-input v-model="logForm.who"></el-input>
                            </el-form-item>
                            <el-form-item label="版本描述" prop="what">
                                <el-input v-model="logForm.what"></el-input>
                            </el-form-item>
                            <!--新增日志信息二级页面保存按钮-->
                            <el-button class="editBtn" type="primary" @click="save">保 存</el-button>
                        </el-form>
                    </div>
                </el-col>
            </el-row>
        </div>
</template>

<script>
    // 引入axios
    import axios from 'axios';
    export default {
        name: "Log",
        // 连接数据库后，返回的数据对应字段名，存放到数组中
        data() {
            return {
                logForm: {
                    id: '',
                    who: '',
                    what: '',
                    date: ''
                },
                queryForm: {},
                hostName: this.$store.state.host,
                logData: [],
                pageSign: 0, //0是查询页面，1是新增或编辑页面
                addOrEditSign: 0, //0是新增，1是编辑
                currentPage: 1,
                pageSize: 10,
                total: 0
            }
        },
        created() {
            this.queryLog();
        },
        methods: {
            // 查询日志信息方法，连接后端，查询数据库日志表信息，显示在网页中
            queryLog() {
                const that = this;
                // 连接数据库,发送get请求
                axios.get(this.hostName + 'log', {}, {
                    headers: {
                        'content-type': 'application/json',
                        "Authorization": this.$store.state.Authorization  //token获取
                    }
                }).then(function (response) {
                    that.logData = response.data.data;
                    //连接失败，控制台报出错误信息
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 新增日志信息方法
            addLog() {
                this.pageSign = 1;
                this.addOrEditSign = 0;
                let obj = {
                    id: '',
                    who: '',
                    what: '',
                    date: ''
                };
                this.logForm = obj;
            },
            // 编辑日志信息方法
            editLog(row) {
                this.pageSign = 1;
                this.addOrEditSign = 1;
                this.logForm = row;
            },
            // 删除日志信息方法
            deleteLog(id) {
                const that = this;
                this.$confirm('是否删除该用户?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // 连接数据库，发送delete请求
                    axios.delete(this.hostName + 'log/' + id, {}, {
                        headers: {
                            'content-type': 'application/json',
                            "Authorization": this.$store.state.Authorization  //token获取
                        }
                        //删除信息成功，返回日志信息子页面
                    }).then(function () {
                        that.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        that.queryLog();
                        //删除信息失败，控制台报出错误信息
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },
            // 新增日志信息二级页面保存方法
            save() {
                const that = this;
                // pageSign：1是新增或编辑页面
                if (this.pageSign == 1) {
                    // addOrEditSign：0为新增页面，1为编辑页面
                    if (this.addOrEditSign == 0) {
                        // 连接数据库,发送post请求
                        axios.post(this.hostName + 'log', {
                            //id:that.logForm.id,
                            who: that.logForm.who,
                            what: that.logForm.what
                        }, {
                            headers: {
                                'content-type': 'application/json',
                                "Authorization": this.$store.state.Authorization  //token获取
                            }
                            //新增信息成功，返回日志信息子页面
                        }).then(function () {
                            that.$message({
                                message: '新增成功',
                                type: 'success'
                            });
                            that.queryLog();
                            //新增失败，控制台报出错误信息
                        }).catch(function (error) {
                            console.log(error);
                        });
                        // 编辑修改日志页面表格信息，连接数据库表，更改表中修改的信息，同步到前端主页面表格中
                    } else if (this.addOrEditSign == 1) {
                        // 连接数据库,发送put请求
                        axios.put(this.hostName + 'log', {
                            id: that.logForm.id,
                            who: that.logForm.who,
                            what: that.logForm.what
                        }, {
                            headers: {
                                'content-type': 'application/json',
                                "Authorization": this.$store.state.Authorization  //token获取
                            }
                            //修改信息成功，返回日志信息子页面
                        }).then(function () {
                            that.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                            that.queryLog();
                            //修改信息失败，控制台报出错误信息
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                }
                this.backBtn();
            },
            // 新增日志信息二级页面返回子页方法
            backBtn() {
                this.pageSign = 0;
                this.queryLog();
            }
        }
    }
</script>

<style scoped>
    #box {
        width: 100%;
        height: 100%;
        background-color: white;
    }
    .condition
    {
        float: left;
        position: relative;
        top: 5px;
        left: 20px;

    }
    .subBtn
    {
        float: right;
        position: relative;
        right: 20px;
        margin-top: 5px;
        margin-bottom: 10px;
    }
    .body
    {
        margin-top: 10px;
        width: 100%;
        height: 800px;
        text-align: center;
    }
</style>