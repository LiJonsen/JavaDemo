const HOST_URL = "http://localhost:8080";
// 七牛云访问路径
window.$QINIU_HOST = "http://static.touchfish.cn/test_dubbo/";
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
        return axios.create({
            baseURL: HOST_URL,
            timeout: 15000,
            headers: {'Content-Type': 'application/json;charset=utf-8'}
        });
    }
    return null;
}

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