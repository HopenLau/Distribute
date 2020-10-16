<!--离港前端软件升级系统子页面-版本文件信息-->
<template slot-scope="scope">
    <div id="box">
        <div class="condition">
            <!--版本文件信息子页返回主页按钮-->
            <el-button type="warning" size="small" @click="backBtnVersion">
                <i style="cursor:pointer;" class="el-icon-back" title="返回主页">返回主页</i>
            </el-button>
            <!--<el-button icon="el-icon-search" class="el-button&#45;&#45;primary" size="small" @click="queryVersionFile">查 询</el-button>-->
        </div>
        <!--子页跳转到二级页面上传文件按钮-->
        <div class="subBtn">
            <el-button class="el-button--success" size="small" @click="addVersion_file">上传文件</el-button>
        </div>
        <!--显示页面表格，连接数据库，显示版本文件信息页面表格数据-->
        <el-table border stripe :data="version_fileData" empty-text style="height: 800px">
            <!-- 写死查询表格列-->
            <el-table-column fixed fit align="center" width="100" prop="versionId" label="版本号"></el-table-column>
            <el-table-column fixed fit align="center" prop="fName" label="版本名称"></el-table-column>
            <el-table-column fixed fit align="center" prop="fSize" label="版本描述"></el-table-column>
            <el-table-column fixed fit align="center" width="100" label="操作">
                <template slot-scope="scope">
                    <!--版本文件信息子页面删除信息按钮-->
                    <el-button type="danger" size="small" @click="deleteVersion_file(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--版本文件信息子页面-二级上传文件页面-->
        <el-dialog title="文件上传"
                :visible.sync="dialogVisible"
                width="30%">
            <el-form :model="version_fileForm" :ref="version_fileForm">
                <!--<el-form-item label="序号" prop="versionId">
                    <el-input v-model="version_fileForm.id"></el-input>
                </el-form-item>
                <el-form-item label="版本号" prop="versionId">
                    <el-input v-model="version_fileForm.versionId"></el-input>
                </el-form-item>
                <el-form-item label="版本描述" prop="fSize">
                    <el-input v-model="version_fileForm.fSize"></el-input>
                </el-form-item>-->

                <!--版本文件上传-->
                <el-form-item label="文件上传">
                    <el-upload
                            ref="upload"
                            class="uploadFile"
                            name="uploadFile"
                            :action="hostName + 'versionFile'"
                            multiple
                            :auto-upload="false"
                            :file-list="fileList"
                            :on-success="uploadSuccess"
                            :on-remove="fileRemove"
                            :data="version_fileForm">
<!--                        选择文件按钮，弹出选择文件窗口-->
                        <el-button size="small" type="primary">选择文件</el-button>
                    </el-upload>
                </el-form-item>
                <!--文件上传页面保存按钮-->
                <el-button class="editBtn" type="primary" @click="submitUpload">保 存</el-button>
            </el-form>
        </el-dialog>
        </div>
</template>

<script>
    // 引入axios
    import axios from 'axios';
    export default {
        name: "Version_file",
        // 连接数据库后，返回的数据对应字段名，存放到数组中
        data() {
            return{
                version_fileForm: {
                    id: '',
                    versionId: this.$route.query.versionId,
                    fName: '',
                    fSize: ''
                },
                queryForm: {},
                version_fileData: [],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                fileList: [],
                hostName: this.$store.state.host,
                dialogVisible: false
            }
        },
        created() {
            this.queryVersion_file();
            this.getParams();
        },
        methods: {
            getParams() {
                const theVersionId = this.$route.query.versionId;
                console.log(theVersionId);
            },
            // 查询版本文件信息方法，连接后端数据库，查询数据库版本文件信息，显示在网页中
            queryVersion_file() {
                const that = this;
                //连接数据库，发送get请求
                axios.get(this.hostName + 'versionFile?versionId=' + this.$route.query.versionId, {}, {
                    headers: {
                        'content-type': 'application/json',
                        "Authorization": this.$store.state.Authorization  //token获取
                    }
                }).then(function (response) {
                    that.version_fileData = response.data.data;
                    //连接失败，控制台报出错误信息
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // 增加版本文件信息方法
            addVersion_file() {
                this.dialogVisible = true;
                let obj = {
                    //id: '',
                    versionId: this.$route.query.versionId
                    // fName: '',
                    // fSize: ''
                };
                this.version_fileForm = obj;
            },
            // 删除版本文件信息方法
            deleteVersion_file(id) {
                const that = this;
                this.$confirm('是否删除该用户?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //连接数据库，发送delete请求
                    axios.delete(this.hostName + 'versionFile/' + id, {}, {
                        headers: {
                            'content-type': 'application/json',
                            "Authorization": this.$store.state.Authorization  //token获取
                        }
                        //删除信息成功，返回版本文件信息子页面
                    }).then(function () {
                        that.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        that.queryVersion_file();
                        //删除信息失败，控制台报出错误信息
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },
            // 新增版本信息后保存方法
            submitUpload() {
                this.$refs.upload.submit();
            },
            // 版本文件信息子页返回主页方法
            backBtnVersion() {
                this.$router.push('/version');
            },
            // 子页上传文件方法成功后更新后端数据
            uploadSuccess(response) {
                //文件上传成功
                if (response.resultcode == 0) {
                    this.$message({
                        message: '上传文件成功',
                        type: 'success'
                    });
                    this.fileRemove();
                    this.dialogVisible = false;
                    this.queryVersion_file();
                } else {
                    this.$message.error(response.resultmsg);
                }
            },
            // 文件删除
            fileRemove() {
                this.fileList = [];
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
    .head
    {
        width: 100%;
        height: 400px;
        text-align: center;
        background-image: url("../assets/images/plan.png");
        background-size: cover;
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