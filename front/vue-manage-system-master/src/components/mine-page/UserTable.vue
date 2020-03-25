<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item> <i class="el-icon-lx-cascades"></i> 基础表格 </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!-- <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>
                <el-select v-model="query.address" placeholder="地址" class="handle-select mr10">
                    <el-option key="1" label="广东省" value="广东省"></el-option>
                    <el-option key="2" label="湖南省" value="湖南省"></el-option>
                </el-select> -->
                <el-input v-model="query.name" placeholder="用户名" class="handle-input mr10" @keyup.enter.native="handleSearch"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="success" icon="el-icon-circle-plus-outline" @click="handleNew">新建用户</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="account" label="账号" align="center" width="100"></el-table-column>
                <el-table-column prop="name" label="用户名" align="center" width="100"></el-table-column>
                <el-table-column prop="roles" label="角色" align="center" width="80"></el-table-column>
                <el-table-column prop="remark" label="备注" align="center"></el-table-column>
                <!-- <el-table-column prop="statusValue" label="账号状态" align="center"></el-table-column> -->
                <!-- <el-table-column label="账户余额">
                    <template slot-scope="scope">￥{{scope.row.money}}</template>
                </el-table-column> -->
                <!-- <el-table-column label="头像(查看大图)" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.thumb"
                            :preview-src-list="[scope.row.thumb]"
                        ></el-image>
                    </template>
                </el-table-column> -->
                <!-- <el-table-column prop="address" label="地址"></el-table-column> -->
                <el-table-column label="状态" align="center" width="80">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.status === 0 ? 'success' : scope.row.state === 1 ? 'danger' : ''">{{
                            scope.row.statusValue
                        }}</el-tag>
                    </template>
                </el-table-column>

                <el-table-column prop="createAt" label="注册时间" align="center"></el-table-column>
                <el-table-column prop="modifyAt" label="修改时间" align="center"></el-table-column>

                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)"
                            >删除</el-button
                        >
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :rules="rules" :model="form" label-width="70px">
                <el-form-item label="用户名" prop="name">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="账号" prop="account">
                    <el-input v-model="form.account"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="form.pwd"></el-input>
                </el-form-item>
                <el-form-item label="角色" prop="role">
                    <el-select v-model="form.role" placeholder="请选择" @change="selectRole" v-bind:disabled="roleDisabled">
                        <el-option v-for="item in roleMap" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-select v-model="form.status" placeholder="请选择" @change="selectStatus" v-bind:disabled="roleDisabled">
                        <el-option v-for="item in statusMap" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="form.remark"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { fetchData } from '../../api/index';
import { post, del } from '../../utils/request';

export default {
    name: 'basetable',
    data() {
        return {
            statusMap: [
                {
                    value: 0,
                    label: '正常'
                },
                {
                    value: 1,
                    label: '失效'
                }
            ],
            roleMap: [
                {
                    value: 0,
                    label: '管理员'
                },
                {
                    value: 1,
                    label: '普通用户'
                }
            ],
            query: {
                address: '',
                name: '',
                pageIndex: 1,
                pageSize: 10
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1,
            roleDisabled: false,
            rules: {
                name: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                account: [{ required: true, message: '请输入账号', trigger: 'blur' }],
                role: [{ required: true, message: '请选择角色', trigger: 'blur' }],
                status: [{ required: true, message: '请选择账号状态', trigger: 'blur' }]
            }
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            // fetchData(this.query).then(res => {
            //     console.log(res);
            //     this.tableData = res.list;
            //     this.pageTotal = res.pageTotal || 50;
            // });
            let param = {
                account: this.query.name,
                pageSize: this.query.pageSize,
                pageNo: this.query.pageIndex
            };
            post('/user/list', param)
                .then(res => {
                    if (res.code != 0) {
                        this.$message.error(res.message);
                        return;
                    }
                    this.tableData = res.body;
                    this.pageTotal = res.total;
                })
                .catch(error => {
                    this.$message.error('获取表格数据失败');
                });
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    del('/user?id=' + row.id, null).then(res => {
                        if (res.code != 0) {
                            this.$message.error(res.message);
                            return;
                        }
                        this.$message.success('删除成功');
                        // 重新从后端加载
                        this.getData();
                        // 直接前端删除当前行
                        // this.tableData.splice(index, 1);
                    });
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let str = '';
            this.delList = this.delList.concat(this.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += this.multipleSelection[i].name + ' ';
            }
            this.$message.error(`删除了${str}`);
            this.multipleSelection = [];
        },
        // 新建操作
        handleNew(index, row) {
            this.idx = index;
            this.form = {};
            this.editVisible = true;
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    this.editVisible = false;
                    let data = {
                        id: this.form.id,
                        name: this.form.name,
                        account: this.form.account,
                        role: this.form.role,
                        status: this.form.status,
                        remark: this.form.remark,
                        pwd: this.form.pwd
                    };

                    // debugger;

                    post('/user', data)
                        .then(res => {
                            if (res.code != 0) {
                                this.$message.error(res.message);
                                return;
                            }
                            this.$message.success('保存成功');
                            this.getData();
                            // this.$set(this.tableData, this.idx, this.form);
                        })
                        .catch(error => {
                            this.$message.error('保存失败');
                        });
                    // this.$message.success(`修改第 ${this.idx + 1} 行成功`);
                } else {
                    this.$message.warning("请完整填写");
                }
            });
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        },
        // select变更操作
        selectRole(value) {
            let obj = {};
            obj = this.roleMap.find(item => {
                return item.value === value;
            });
        },
        selectStatus(value) {
            let obj = {};
            obj = this.statusMap.find(item => {
                return item.value === value;
            });
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
