<!--离港前端软件升级系统子页面-柜台信息-->
<template>
    <div id="box">
        <el-row class="body" v-show="pageSign == 0">
            <div class="condition">
                <!--<el-button icon="el-icon-search" class="el-button&#45;&#45;primary" size="small" @click="queryCounter">查 询</el-button>-->
            </div>
            <!--新增柜台信息按钮-->
            <div class="subBtn">
                <el-button class="el-button--success" size="small" @click="addCounter">新增柜台信息</el-button>
            </div>
            <!--显示前端页面表格，连接数据库，显示柜台信息页面表格数据-->
            <el-table border stripe :data="CounterData" empty-text style="height: 800px">
                <!-- 写死查询表格列-->
                <el-table-column fixed fit align="center" width="100" prop="cId" label="柜台号"></el-table-column>
                <el-table-column fixed fit align="center" prop="name" label="柜台名称"></el-table-column>
                <el-table-column fixed fit align="center" prop="cIp" label="ip地址"></el-table-column>
                <el-table-column fixed fit align="center" width="300" label="操作">
                    <template slot-scope="scope">
                        <!--柜台信息子页面编辑信息按钮-->
                        <el-button type="success" size="small" @click="editCounter(scope.row)">编辑</el-button>
                        <!--柜台信息子页面删除信息按钮-->
                        <el-button type="danger" size="small" @click="deleteCounter(scope.row.id)">删除</el-button>
                        <!--柜台信息子页面参数修改信息按钮-->
                        <el-button type="success" size="small" @click="editParams(scope.row.cId)">参数修改</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--编辑修改柜台信息数据，并保存-->
            <el-dialog title="参数修改"
                       :visible.sync="dialogVisible"
                       width="30%">
                <el-form :model="configForm" :ref="configForm">
                    <el-form-item label="参数" prop="versionId">
                        <el-input type="textarea" rows="27" v-model="configForm.content"></el-input>
                    </el-form-item>
                    <!--编辑修改柜台信息后，保存按钮-->
                    <el-button class="editBtn" type="primary" @click="submitConfig">保 存</el-button>
                </el-form>
            </el-dialog>
        </el-row>
        <!--新增柜台信息二级页面-->
        <el-row v-show="pageSign == 1">
            <el-col :span="24">
                <div class="pageEdit">
                    <!--新增柜台信息二级页面返回柜台信息子页按钮-->
                    <el-button circle @click="backBtn">
                        <i style="cursor:pointer;" class="el-icon-back"></i>
                    </el-button>
                    <!--新增柜台信息二级页面要输入的内容-->
                    <el-form :model="CounterForm" :ref="CounterForm">
                        <el-form-item label="柜台号" prop="cId">
                            <el-input :disabled="isDisabled" v-model="CounterForm.cId"></el-input>
                        </el-form-item>
                        <el-form-item label="柜台名称" prop="name">
                            <el-input v-model="CounterForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="ip地址" prop="cIp">
                            <el-input v-model="CounterForm.cIp"></el-input>
                        </el-form-item>
                        <!--新增柜台信息二级页面保存按钮-->
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
        name: "Counter",
        // 连接数据库后，返回的数据对应字段名，存放到数组中
        data() {
            return {
                CounterForm: {
                    id: '',
                    cId: '',
                    name: '',
                    cIp: '',
                    //version: ''
                },
                queryForm: {},
                CounterData: [],
                configForm: {},
                isDisabled: 0, //0是可编辑，1是不可编辑
                pageSign: 0, //0是查询页面，1是新增或编辑页面
                addOrEditSign: 0, //0是新增，1是编辑
                currentPage: 1,
                pageSize: 10,
                total: 0,
                hostName: this.$store.state.host,
                dialogVisible: false
            }
        },
        created() {
            this.queryCounter();
        },
        methods: {
            // 查询柜台信息方法
            queryCounter() {
                const that = this;
                // 连接后端数据库,发送get请求
                axios.get(this.hostName + 'counter', {}, {
                    headers: {
                        'content-type': 'application/json',
                        "Authorization": this.$store.state.Authorization  //token获取
                    }
                }).then(function (response) {
                    that.CounterData = response.data.data;
                    //连接失败，控制台报出错误信息
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 新增柜台信息方法
            addCounter() {
                this.pageSign = 1;
                this.addOrEditSign = 0;
                this.isDisabled = 0;
                let obj = {
                    id: '',
                    cId: '',
                    name: '',
                    cIp: '',
                    //version: ''
                };
                this.CounterForm = obj;
            },
            // 编辑柜台信息方法
            editCounter(row) {
                this.pageSign = 1;
                this.addOrEditSign = 1;
                this.isDisabled = 1;
                this.CounterForm = row;
            },
            // 删除柜台信息方法
            deleteCounter(id) {
                const that = this;
                this.$confirm('是否删除该用户?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // 连接数据库,发送delete请求
                    axios.delete(this.hostName + 'counter/' + id, {}, {
                        headers: {
                            'content-type': 'application/json',
                            "Authorization": this.$store.state.Authorization  //token获取
                        }
                        //删除信息成功，返回柜台信息子页面
                    }).then(function () {
                        that.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        that.queryCounter();
                        //删除信息失败，控制台报出错误信息
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },
            // 新增柜台信息二级页面保存方法
            save() {
                const that = this;
                // pageSign：1是新增或编辑页面
                if (this.pageSign == 1) {
                    // addOrEditSign：0为新增页面，1为编辑页面
                    if (this.addOrEditSign == 0) {
                        // 连接数据库,发送post请求
                        axios.post(this.hostName + 'counter', {
                            cId: that.CounterForm.cId,
                            name: that.CounterForm.name,
                            cIp: that.CounterForm.cIp,
                        }, {
                            headers: {
                                'content-type': 'application/json',
                                "Authorization": this.$store.state.Authorization  //token获取
                            }
                            //新增信息成功，返回柜台信息子页面
                        }).then(function () {
                            that.$message({
                                message: '新增成功',
                                type: 'success'
                            });
                            that.queryCounter();
                            //新增信息失败，控制台报出错误信息
                        }).catch(function (error) {
                            console.log(error);
                        });
                        // 编辑修改柜台页面表格信息，连接数据库表，更改表中修改的信息，同步到前端主页面表格中
                    } else if (this.addOrEditSign == 1) {
                        // 连接数据库，发送put请求
                        axios.put(this.hostName + 'counter', {
                            id: that.CounterForm.id,
                            cId: that.CounterForm.cId,
                            name: that.CounterForm.name,
                            cIp: that.CounterForm.cIp,
                        }, {
                            headers: {
                                'content-type': 'application/json',
                                "Authorization": this.$store.state.Authorization  //token获取
                            }
                            //修改信息成功，返回柜台信息子页面
                        }).then(function () {
                            that.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                            that.queryCounter();
                            //修改信息失败，控制台报出错误信息
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                }
                this.backBtn();
            },
            // 新增柜台信息二级页面返回子页面方法
            backBtn() {
                this.pageSign = 0;
                this.queryCounter();
            },
            // 参数修改方法
            editParams(cId) {
                this.dialogVisible = 1;
                const that = this;
                //连接数据库，发送get请求
                axios.get(this.hostName + 'config', {cId: cId}, {
                    headers: {
                        'content-type': 'application/json',
                        "Authorization": this.$store.state.Authorization  //token获取
                    }
                    //参数修改的信息，传入到obj中
                }).then(function (response) {
                    let obj = {
                        cId: cId,
                      content: response.data.data
                    };
                    that.configForm = obj;
                    //参数修改失败，控制台报出错误信息
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 编辑柜台信息后保存方法
            submitConfig() {
                const that = this;
                //连接数据库，发送put请求
                axios.put(this.hostName + 'config/' + this.configForm.cId, {content: this.configForm.content}, {
                    headers: {
                        'content-type': 'application/json',
                        "Authorization": this.$store.state.Authorization  //token获取
                    }
                    //参数修改成功，返回柜台信息子页面
                }).then(function () {
                    that.$message({
                        message: '参数修改成功',
                        type: 'success'
                    });
                  that.dialogVisible = false;
                    that.queryCounter();
                    //参数修改失败，控制台报出错误信息
                }).catch(function (error) {
                    console.log(error);
                });
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