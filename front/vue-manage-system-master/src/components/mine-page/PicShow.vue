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

                <div class="photo-container">
                    <el-row :gutter="20">
                        <el-col :span="6" v-for="x in srcMap" :key="x.id">
                            <div class="demo-image__placeholder">
                                <div class="block img-container">
                                    <el-image :src="x.path">
                                        <div slot="error" class="image-slot">
                                            <i class="el-icon-picture-outline"></i>
                                        </div>
                                    </el-image>
                                    <span class="demonstration" v-if="x.createAt != null">拍摄时间：{{ x.createAt }}</span>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </div>
        </div>
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
            srcMap: []
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
