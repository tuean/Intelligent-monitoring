<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item> <i class="el-icon-lx-cascades"></i> 抓拍数据 </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!-- <el-input v-model="query.name" placeholder="用户名" class="handle-input mr10" @keyup.enter.native="handleSearch"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="success" icon="el-icon-circle-plus-outline" @click="handleNew">新建用户</el-button> -->
                <label>设备选择：</label>
                <el-cascader v-model="value" :options="options" @change="handleChange"></el-cascader>

                <el-button type="primary" @click="picNow">抓拍</el-button>

                <div class="photo-container">
                    <el-row :gutter="20">
                        <el-col :span="6" v-for="x in srcMap" :key="x.id">
                            <div class="demo-image__placeholder">
                                <div class="block img-container">
                                    <el-image :src="'data:image/jpeg;base64,' + x.base64">
                                        <div slot="error" class="image-slot">
                                            <i class="el-icon-picture-outline"></i>
                                        </div>
                                    </el-image>
                                    <br />
                                    <span class="demonstration" v-if="x.createAt != null">拍摄时间：{{ x.createAt }}</span>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </div>
        </div>

        <!-- 设备信息查看 -->
        <el-dialog title="抓拍信息" :visible.sync="visible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="设备代码">
                    <el-input :disabled="true" v-model="devCode"></el-input>
                </el-form-item>
                <el-form-item label="立即抓拍">
                    <el-switch v-model="form.shotImmediately"></el-switch>
                </el-form-item>
                <el-form-item label="抓拍频率">
                    <el-input placeholder="请输入内容" v-model="form.everyNumber" class="input-with-select">
                        <el-select v-model="form.unit" style="width:60px" slot="prepend" placeholder="请选择">
                            <el-option label="秒" value="s"></el-option>
                            <el-option label="分" value="m"></el-option>
                            <el-option label="时" value="h"></el-option>
                        </el-select>
                    </el-input>
                </el-form-item>

                <el-form-item label="指定时间">
                    <el-input v-model="form.on" placeholder="hh:mm:ss"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="save">保 存</el-button>
                <el-button @click="visible = false">关 闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { post, get } from '../../utils/request';
let id = 0;

export default {
    data() {
        return {
            props: {},
            value: null,
            options: [],
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
            srcMap: [],
            devCode: '',
            visible: false,
            form: {
                unit: 's'
            }
        };
    },
    created() {
        get('/equip/group')
            .then(res => {
                if (res.code !== 0) {
                    this.$message.error(res.message);
                    return;
                }
                this.options = res.body;
            })
            .catch(err => {
                this.console.error('获取设备信息失败');
            });
    },
    methods: {
        handleChange(value) {
            // console.log(value);
            if (value.length < 3) {
                this.$message.warning('系统异常');
            }
            let data = {
                id: value[2]
            };
            this.devCode = value[2];
            post('/pic/list', data)
                .then(res => {
                    this.srcMap = res.body;
                    if (this.srcMap == null || this.srcMap.length < 1) {
                        this.$message.warning('当前设备下没有照片');
                    }
                })
                .catch(err => {
                    this.$message.error('获取图片失败');
                });
        },
        picNow() {
            console.log(this.devCode);
            if (this.devCode == null || this.devCode == '') {
                this.$message.warning('您还没有选择设备');
                return;
            }

            // post('/pic/now?devCode=' + this.devCode, null).then(res => {
            //     this.$message.success("设置成功")
            // }).catch(err => {
            //     this.$message.success("设置失败")
            // })


            this.visible = true;
        },
        save() {
            console.log('save');
            // if (this.devCode == null || this.devCode == '') {
            //     this.$message.warning("您还没有选择设备")
            //     return;
            // }

            // post('/pic/now?devCode=' + this.devCode, null)
            //     .then(res => {
            //         this.$message.success('设置成功');
            //     })
            //     .catch(err => {
            //         this.$message.success('设置失败');
            //     });

            let data = {
                ...this.form,
                every: this.form.everyNumber + "." + this.form.unit,
                devCode: this.devCode
            }
            post('/shot', data).then(res => {
                if (res.code !== 0 ){
                    this.$message.error(res.messaeg)
                    return
                }
                this.$message.success("保存成功")
                this.visible = false;
            }).catch(err => {
                console.log(err)
                this.$Message.error("保存失败")
            })
        }
    }
};
</script>

<style scoped>
.photo-container {
    padding-top: 8px;
}

.img-container {
    text-align: center;
}

.demonstration {
    font-size: 12px;
}
</style>
