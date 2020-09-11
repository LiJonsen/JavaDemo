const HOST_URL = "http://localhost:8081";
// 七牛云访问路径
window.$QINIU_HOST = "http://static.touchfish.cn/test_dubbo/";
// 1. 提交体检预约验证码
window.$SEND_CODE_TYPE_ORDER = "order";
// 2. 登录验证码
window.$SEND_CODE_TYPE_LOGIN = "login";
// 3. 找回密码验证码
window.$SEND_CODE_TYPE_FIND_PWD = "forgetPwd";
var instance;
try{
    instance = initAxios();
}catch (e) {
    let i_timer = setTimeout(()=>{
        instance = initAxios();
    },500);
}

function initAxios(){
    if(axios){
        // axios.defaults.withCredentials=true;
        // axios.defaults.crossDomain=true;
        return axios.create({
            baseURL: HOST_URL,
            timeout: 15000,
            headers: {'Content-Type': 'application/json;charset=utf-8'}
        });
    }
    return null;
}

/**
 * 移动端ajax请求函数
 * @param method
 * @param data
 * @param url
 * @returns {Promise<unknown>}
 */
window.$request = function({method='get',data,url}){
    return new Promise((resolve,reject)=>{
        instance({
            method: method,
            url: url,
            data: data,
        }).then(res=>{
            resolve(res.data);
        }).catch(err=>{
            console.log("Request Error: ",err)
            reject(err);
        });
    })
}


