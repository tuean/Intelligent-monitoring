<template>
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
        <el-tab-pane label="项目监控" name="first">
            <el-row :gutter="20">
                <el-col :span="16">
                    <baidu-map name="baidu-map" class="bm-view" center="上海">
                        <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
                        <bm-marker
                            v-for="(item, index) in locationList"
                            :dragging="false"
                            animation="BMAP_ANIMATION_BOUNCE"
                            :position="{ lng: item.longitude, lat: item.latitude }"
                            :icon="getLocationIconByStatus(item.status)"
                            :key="index"
                        >
                            <bm-label
                                :content="item.name"
                                :labelStyle="{ color: 'red', fontSize: '16px' }"
                                :offset="{ width: -18, height: 30 }"
                            />
                        </bm-marker>
                    </baidu-map>
                </el-col>
                <el-col :span="8">
                    <div class="schart-box">
                        <!-- <div class="content-title">柱状图</div> -->
                        <schart class="schart" canvasId="bar1" :options="options1"></schart>
                    </div>

                    <div class="schart-box">
                        <!-- <div class="content-title">柱状图</div> -->
                        <schart class="schart" canvasId="bar2" :options="options2"></schart>
                    </div>
                </el-col>
            </el-row>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import { get } from '../../utils/request';
import redImg from '../../assets/img/location-red.png';
import greenImg from '../../assets/img/location-green.png';
import grayImg from '../../assets/img/location-gray.png';
import Schart from 'vue-schart';

export default {
    components: {
        Schart
    },
    data() {
        return {
            activeName: 'first',
            warningMap: {},
            equipMap: {},
            locationList: [],
            countMap: {},
            options1: {},
            options2: {}
        };
    },
    created() {
        this.init();
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        },
        init() {
            get('/monitor', null)
                .then(res => {
                    if (res.code !== 0) {
                        this.$message.error(res.message);
                        return;
                    }
                    this.warningMap = res.body.warningMap;
                    this.equipMap = res.body.equipMap;
                    this.locationList = res.body.locationList;
                    this.countMap = res.body.countMap;
                    this.options1 = {
                        type: 'bar',
                        title: {
                            text: '状态信息'
                        },
                        bgColor: '#fbfbfb',
                        labels: ['总数', '在线', '离线', '预警'],
                        datasets: [
                            {
                                label: '数量',
                                fillColor: 'rgba(241, 49, 74, 0.5)',
                                data: [this.countMap.total, this.countMap.online, this.countMap.offline, this.countMap.warning]
                            }
                        ]
                    };

                    let labels = [];
                    let dataset = [];
                    if (this.equipMap != null) {
                        for (var key in this.equipMap) {
                            labels.push(key);
                            dataset.push(this.equipMap[key]);
                        }
                    }

                    this.options2 = {
                        type: 'bar',
                        title: {
                            text: '城市分布'
                        },
                        bgColor: '#fbfbfb',
                        labels: labels,
                        datasets: [
                            {
                                label: '数量',
                                fillColor: 'rgba(241, 49, 74, 0.5)',
                                data: dataset
                            }
                        ]
                    };
                })
                .catch(err => {
                    console.log(err);
                    this.$message.error('获取项目数据失败');
                });
        },
        getLocationIconByStatus(status) {
            // 正常
            if (status === 0) {
                return { url: greenImg, size: { width: 300, height: 157 } };
            }
            if (status === 1) {
                return { url: grayImg, size: { width: 300, height: 157 } };
            }
            if (status === 2) {
                return { url: redImg, size: { width: 300, height: 157 } };
            }
        }
    }
};
</script>

<style scoped>
.bm-view {
    width: 100%;
    height: 500px;
}
.schart-box {
    display: inline-block;
    margin-top: 20px;
}
</style>
