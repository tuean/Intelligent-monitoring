<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">可视化智能管控系统</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.username" placeholder="请输入您的账号">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="请输入密码" v-model="param.password" @keyup.enter.native="submitForm()">
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
                <p class="login-tips">小提示：登录失败请联系管理员</p>
            </el-form>
        </div>
    </div>
</template>

<script>
import { post, get } from '../../utils/request';
import { setUserInfo, getUserInfo } from '../../utils/mUtils'
 
export default {
    data: function() {
        return {
            param: {
                username: '',
                password: ''
            },
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
            }
        };
    },
    created() {
        let old_user_info = getUserInfo();
        if (old_user_info != null && old_user_info.token != null) {
            get("/token/check").then(res => {
                this.$message.success('已登录，自动跳转到首页');
                this.$router.push('/');
            })
        }
    },
    methods: {
        submitForm() {
            this.$refs.login.validate(valid => {
                if (valid) {
                    let data = {
                        account: this.param.username,
                        pwd: this.param.password
                    };
                    post('/login', data).then(res => {
                        if (res.code != 0) {
                            this.$message.error(res.message);
                            return;
                        }
                        this.$message.success('登录成功');
                        setUserInfo(res.body);
                        localStorage.setItem('ms_username', res.body.name);
                        this.$router.push('/');
                    }).catch(err => {
                        this.$message.error("登录失败");
                    });
                } else {
                    this.$message.error('请输入账号和密码');
                    console.log('error submit!!');
                    return false;
                }
            });
        }
    }
};
</script>

<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../../assets/img/login_bk2.png);
    background-size: 100%;
}
.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #fff;
    border-bottom: 1px solid #ddd;
}
.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}
.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #fff;
}
</style>
