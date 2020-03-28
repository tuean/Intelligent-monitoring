import axios from 'axios';
import { getToken, clearLocalStorage } from './mUtils';

const service = axios.create({
    // process.env.NODE_ENV === 'development' 来判断是否开发环境
    // easy-mock服务挂了，暂时不使用了
    // baseURL: 'https://www.easy-mock.com/mock/592501a391470c0ac1fab128',
    timeout: 5000
});

service.defaults.baseURL = 'http://47.103.61.148:7777';

service.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);

service.interceptors.response.use(
    
    response => {
        if (response.status === 200) {
            if (response.data != null && response.data.code === 999) {
                clearLocalStorage();
                window.location.href = '/'
            } else {
                return response;
            }
            
        } else {
            Promise.reject();
        }
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);

export default service;

export function get(url) {
    let config = {
        headers: {
            'Content-Type': 'application/json',
            'x-auth-token': getToken()
        }
    };
    return new Promise((resolve, reject) => {
        service
            .get(url, config)
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                reject(err);
            });
    });
}

export function post(url, params) {
    let config = {
        headers: {
            'Content-Type': 'application/json',
            'x-auth-token': getToken()
        }
    };
    return new Promise((resolve, reject) => {
        service
            .post(url, params, config)
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                // if (err.response.data.code != null) {
                // 	_this.$message.error(err.response.data.message)
                // } else {
                // 	_this.$message.error('请求出错：'+res)
                // }
                reject(err);
            });
    });
}

export function del(url) {
    let config = {
        headers: {
            'Content-Type': 'application/json',
            'x-auth-token': getToken()
        }
    };
    return new Promise((resolve, reject) => {
        service
            .delete(url, config)
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                reject(err);
            });
    });
}

export function uploadFile(url, formData, type) {
    return new Promise((resolve, reject) => {
        service
            .post(url, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'x-auth-token': getToken(),
                    type: type
                }
            })
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                reject(err);
            });
    });
}

export function download(url) {
    let config = {
        headers: {
            'Content-Type': 'application/json',
            'x-auth-token': getToken()
        },
        responseType: 'blob'
    };
    return new Promise((resolve, reject) => {
        service
            .get(url, config)
            .then(res => {
                let disposition = res.headers['content-disposition'];
                let fileName = disposition.substring(disposition.indexOf('filename=') + 9, disposition.length);
                fileName = decodeURI(fileName);
                const blob = res.data;
                const reader = new FileReader();
                reader.readAsDataURL(blob);
                reader.onload = e => {
                    const a = document.createElement('a');
                    a.download = fileName;
                    a.href = e.target.result;
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                };
            })
            .catch(err => {
                reject(err);
            });
    });
}

export function downloadWithParam(url, data) {
    let config = {
        headers: {
            'Content-Type': 'application/json',
            'x-auth-token': getToken()
        },
        responseType: 'blob'
    };
    return new Promise((resolve, reject) => {
        service
            .post(url, data, config)
            .then(res => {
                let disposition = res.headers['content-disposition'];
                let fileName = disposition.substring(disposition.indexOf('filename=') + 9, disposition.length);
                fileName = decodeURI(fileName);
                const blob = res.data;
                const reader = new FileReader();
                reader.readAsDataURL(blob);
                reader.onload = e => {
                    const a = document.createElement('a');
                    a.download = fileName;
                    a.href = e.target.result;
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                };
            })
            .catch(err => {
                reject(err);
            });
    });
}

export function exportFile(url, data) {
    let config = {
        headers: {
            'Content-Type': 'application/json',
            'x-auth-token': getToken()
        },
        responseType: 'blob'
    };

    return new Promise((resolve, reject) => {
        service
            .post(url, data, config)
            .then(res => {
                let disposition = res.headers['content-disposition'];
                let fileName = disposition.substring(disposition.indexOf('filename=') + 9, disposition.length);
                fileName = decodeURI(fileName);
                const blob = res.data;
                const reader = new FileReader();
                reader.readAsDataURL(blob);
                reader.onload = e => {
                    const a = document.createElement('a');
                    a.download = fileName;
                    a.href = e.target.result;
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                };
            })
            .catch(err => {
                reject(err);
            });
    });
}
