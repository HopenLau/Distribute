<!--离港前端软件升级系统主页面-->
<template>
    <div id="user">
        <el-row v-show="pageSign == 0">
            <div class="pageQuery">
                <div class="condition">
                    <!--<el-form v-model="queryForm">
                        <el-input v-model="queryForm.userId" type="text" placeholder="请输入登录帐号"></el-input>
                        <el-input v-model="queryForm.userName" type="text" placeholder="请输入用户名称"></el-input>
                        <el-button icon="el-icon-search" class="el-button&#45;&#45;primary" size="small" @click="queryVersion">查 询</el-button>
                    </el-form>-->
                </div>
                <!--新增版本信息按钮-->
                <div class="subBtn">
                    <el-button class="el-button--success" size="small" @click="addVersion">新 增 版 本</el-button>
                </div>
            </div>
            <div class="pageList">
                <!--页面表格信息，连接数据库，显示后端数据库表格信息-->
                <div class="table">
                    <el-table border stripe :data="versionData" empty-text>
                        <!-- 写死查询表格列-->
                        <el-table-column fixed fit align="center" prop="name" label="版本名称"></el-table-column>
                        <el-table-column fixed fit align="center" prop="number" label="版本号"></el-table-column>
                        <el-table-column fixed fit align="center" prop="des" label="详细描述"></el-table-column>
                        <el-table-column fixed fit align="center" prop="date" label="更新时间"></el-table-column>
                        <el-table-column fixed fit align="center" label="操作">
                            <template slot-scope="scope">
                                <!--版本主页面编辑信息按钮-->
                                <el-button type="success" size="small" @click="editVersion(scope.row)">编辑</el-button>
                                <!--版本主页面删除信息按钮-->
                                <el-button type="danger" size="small" @click="deleteVersion(scope.row.id)">删除</el-button>
                                <!--跳转版本文件信息子页面按钮-->
                                <el-button type="warning" size="small" @click="jumpVersionFile(scope.row.id)">文件信息</el-button>
                                <!--版本主页面下发版本文件按钮-->
                                <el-button type="primary" size="small" @click="issueVersion(scope.row.number)">版本下发</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <!--<el-pagination @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="currentPage"
                               :page-sizes="[10, 20, 30, 40, 50]"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total">
                </el-pagination>-->
            </div>
        </el-row>
        <!--主页面-二级新增版本信息页面-->
        <el-row v-show="pageSign == 1">
            <el-col :span="24">
                <div class="pageEdit">
                    <!--新增版本信息二级页面返回主页按钮-->
                    <el-button circle @click="backBtn">
                        <i style="cursor:pointer;" class="el-icon-back"></i>
                    </el-button>
                    <!--新增版本信息二级页面需要输入的内容-->
                    <el-form :model="versionForm" :ref="versionForm">
                        <el-form-item label="版本名称" prop="name">
                            <el-input v-model="versionForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="版本号" prop="number">
                            <el-input :disabled="isDisabled" v-model="versionForm.number"></el-input>
                        </el-form-item>
                        <el-form-item label="详细描述" prop="des">
                            <el-input v-model="versionForm.des"></el-input>
                        </el-form-item>
                        <!--新增版本信息二级页面保存按钮-->
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
        name: "Version",
        // 连接数据库后，返回的数据对应字段名，存放到数组中
        data() {
            return {
                versionForm: {
                    id: '',
                    name: '',
                    number: '',
                    des: '',
                    date:''
                },
                queryForm: {},
                hostName: this.$store.state.host,
                versionData: [],
                isDisabled: 0, //0是可编辑，1是不可编辑
                pageSign: 0, //0是查询页面，1是新增或编辑页面
                addOrEditSign: 0, //0是新增，1是编辑
                currentPage: 1,
                pageSize: 10,
                total: 0
            }
        },
        created() {
            this.queryVersion();
        },
        methods: {
            // 查询数据库表方法
            queryVersion() {
                const that = this;
                //连接数据库，发送get请求
                axios.get(this.hostName + 'version', {
                }, {
                    headers: {
                        'content-type': 'application/json',
                        "Authorization": this.$store.state.Authorization  //token获取
                    }
                }).then(function (response) {
                    that.versionData = response.data.data;
                    //连接数据库失败，控制台报出错误信息
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 新增版本信息方法
            addVersion() {
                this.pageSign = 1;
                this.addOrEditSign = 0;
                this.isDisabled = 0;
                let obj = {
                    id: '',
                    name: '',
                    number: '',
                    des: '',
                    date: ''
                };
                this.versionForm = obj;
            },
            // 编辑页面数据表格信息方法
            editVersion(row) {
                this.pageSign = 1;
                this.addOrEditSign = 1;
                this.isDisabled = 1;
                this.versionForm = row;
            },
            // 删除页面数据表格信息方法
            deleteVersion(id) {
                const that = this;
                this.$confirm('是否删除该用户?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //连接数据库，发送delete请求
                    axios.delete(this.hostName + 'version/' + id, {
                    }, {
                        headers: {
                            'content-type': 'application/json',
                            "Authorization": this.$store.state.Authorization  //token获取
                        }
                        //删除版本信息成功，返回版本主页面
                    }).then(function () {
                        that.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        that.queryVersion();
                        //删除版本信息失败，控制台报出错误信息
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },
            // 新增版本信息保存方法，连接后端数据库，并向数据库表中存放信息，再显示到页面中
            save() {
                const that = this;
                // pageSign：1是新增或编辑页面
                if(this.pageSign == 1) {
                    // addOrEditSign：0为新增页面，1为编辑页面
                    if(this.addOrEditSign == 0) {
                        //连接数据库，发送post请求
                        axios.post(this.hostName + 'version', {
                            name: that.versionForm.name,
                            number: that.versionForm.number,
                            des: that.versionForm.des,
                        }, {
                            headers: {
                                'content-type': 'application/json',
                                "Authorization": this.$store.state.Authorization  //token获取
                            }
                            //新增版本信息成功，返回版本主页面
                        }).then(function () {
                            that.$message({
                                message: '新增成功',
                                type: 'success'
                            });
                            that.queryVersion();
                            //新增版本信息失败，控制台报出错误信息
                        }).catch(function (error) {
                            console.log(error);
                        });
                        // 编辑修改主页面表格信息，连接数据库表，更改表中修改的信息，同步到前端主页面表格中
                    } else if(this.addOrEditSign == 1) {
                        //连接数据库，发送put请求
                        axios.put(this.hostName + 'version', {
                            name: that.versionForm.name,
                            number: that.versionForm.number,
                            des: that.versionForm.des,
                        }, {
                            headers: {
                                'content-type': 'application/json',
                                "Authorization": this.$store.state.Authorization  //token获取
                            }
                            //修改版本文件信息成功，返回版本主页面
                        }).then(function () {
                            that.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                            //修改版本文件信息失败，控制台报出错误信息
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                }
                this.backBtn();
            },
            // 主页面-二级页面返回按钮方法
            backBtn() {
                this.pageSign = 0;
                this.queryVersion();
            },
            // 主页面跳转到子页面-版本文件信息页面-方法
            jumpVersionFile(id) {
                this.$store.dispatch('versionIdActionFun', id);
                this.$router.push({
                    path:'/versionFile',
                    query:{versionId: id}
                });
            },
            // 主页面下发版本文件方法
            issueVersion(number) {
                var that = this;
                this.$confirm('是否下发版本?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //连接数据库，发送get请求
                    axios.get(this.hostName + 'deploy/' + number, {
                    }, {
                        headers: {
                            'content-type': 'application/json',
                            "Authorization": this.$store.state.Authorization  //token获取
                        }
                        //下发版本文件成功
                    }).then(function(res) {
                        if(res.data.resultcode === 0) {
                            that.$message({
                                message: '下发成功',
                                type: 'success'
                            });
                            //版本下发失败
                        } else {
                            that.$message.error(res.data.resultmsg);
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            }
        }
    }
</script>

<style scoped>
#user {
    width: 100%;
    height: 100%;
    background-color: white;
}
</style>