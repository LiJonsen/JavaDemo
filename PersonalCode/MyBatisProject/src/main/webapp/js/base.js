var app_path = "/mm";
var app_url = "http://localhost:8080/mm";
var host_url = "http://localhost:8080/";
var base = {
	"isDis": [{
			"id": 0,
			"value": "启用"
		},
		{
			"id": 1,
			"value": "禁用"
		}
	],
	"isShow": [{
			"id": 0,
			"value": "是"
		},
		{
			"id": 1,
			"value": "否"
		}
	],
	"difficulty": [{
			"id": 1,
			"value": "简单"
		},
		{
			"id": 2,
			"value": "一般"
		},
		{
			"id": 3,
			"value": "困难"
		}
	],
	"questionsType": [{
			"id": 1,
			"value": "单选"
		},
		{
			"id": 2,
			"value": "多选"
		},
		{
			"id": 5,
			"value": "简答"
		}
	],
	"selectData": [{
			"id": 1,
			"value": "A"
		},
		{
			"id": 2,
			"value": "B"
		},
		{
			"id": 3,
			"value": "C"
		},
		{
			"id": 4,
			"value": "D"
		},
		{
			"id": 5,
			"value": "E"
		},
		{
			"id": 6,
			"value": "F"
		}
	],
	"reiviewStatus": [{
			"id": 0,
			"value": "待审核"
		},
		{
			"id": 1,
			"value": "已审核"
		},
		{
			"id": 2,
			"value": "已拒绝"
		}
	],
	"publishStatus": [{
			"id": 0,
			"value": "待发布"
		},
		{
			"id": 1,
			"value": "已发布"
		},
		{
			"id": 2,
			"value": "已下架"
		}
	]
}

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
			baseURL: host_url,
			timeout: 15000,
			headers: {'Content-Type': 'application/json;charset=utf-8'}
		});
	}
	return null;
}

// 获取用户信息
var $getAccountInfo = function(){
	let item = window.localStorage.getItem("AccountInfo");
	if(!item){
		window.location.href = "/login.html";
		return false;
	}
	try{
		return JSON.parse(item);
	}catch (e) {
		window.localStorage.setItem("AccountInfo","");
		window.location.href = "/login.html";
	}
}
// 公用请求函数
var $request = function({url,data,method='get'}){
	return new Promise((resolve,reject)=>{
		instance({
			method: method,
			url: url+".do",
			data: data,
		}).then(res=>{
			resolve(res.data);
		}).catch(err=>{
			console.log("Request Error: ",err)
			reject(err);
		});
	})
}
