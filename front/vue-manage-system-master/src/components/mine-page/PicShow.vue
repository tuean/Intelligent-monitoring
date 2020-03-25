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
                    <el-row :gutter="20" >
                    <el-col :span="6" v-for="x in srcMap" :key="x.id">
                        <div class="demo-image__placeholder">
                            <div class="block img-container">
                                <el-image :src="x.src"></el-image>
                                <span class="demonstration">{{x.des}}</span>
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
            srcMap: [{
                id: 1,
                src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
                des: "moren"
            },{
                id: 2,
                src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg"
            }]
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
            console.log(value);
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
