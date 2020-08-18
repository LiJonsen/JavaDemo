var app = new Vue({
    el: '#app',
    data: {
        logout_flag:true,
        username: "",
        token:"",
        columns: [
            {
                title: '用户名',
                key: 'username',
                align:'center'
            },
            {
                title: '邮箱',
                key: 'email',
                align:'center'
            },
            {
                title: '账号激活状态',
                key: 'isActive',
                align:'center'
            }
        ],
        datas: [],
        webSiteMsg:[
            {value: 0, name: '首页访问量'},
            {value:0, name: '已激活账号'},
            {value:0, name: '登录统计'},
            {value:0, name: '注册统计'},
        ],
        myChart:null,
        // 指定图表的配置项和数据
        option:{
            title: {
                text: '站点信息统计',
                subtext: '对站点一些基本访问信息进行统计',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['首页访问量', '已激活账号', '登录统计', '注册统计']
            },
            series: [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data:this.webSiteMsg,
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        },
        page:{
            current:1,
            pageSize:10,
            total:0,
        },
    },
    created: function () {
        let user = window.localStorage.getItem("userMsg");
        if(user){
            try{
                let msg = JSON.parse(user);
                this.username = msg.user;
                this.token = msg.token;
            }catch (err) {

            }
        }
        if(!this.username || !this.token){
            window.location.href = "/";
            return;
        }
        this.getUserList();
        this.getWebSiteMsg();
        this.$nextTick(()=>{
            window.onresize = ()=>{
                this.myChart.resize();
            }
            this.initEcharts();
        })
    },
    methods:{
        getWebSiteMsg(){
            let obj = {action:'getSiteMessage',token:this.token,username:this.username};
            this.send_request('post','home',obj).then(res=>{
                if(res.code == 200){
                    this.webSiteMsg[0].value=res.data.access_count;
                    this.webSiteMsg[1].value=res.data.active_count;
                    this.webSiteMsg[2].value=res.data.login_count;
                    this.webSiteMsg[3].value=res.data.user_count;
                    this.option.series[0].data = this.webSiteMsg;
                    this.myChart.setOption(this.option);
                }else if(res.code == 501){
                    // 登录token过期
                    this.logout();
                }
            })
        },
        getUserList(){
            let obj = {
                action:'getUserList',
                token:this.token,
                username:this.username,
                current:this.page.current,
                pageSize:this.page.pageSize
            };

            this.send_request('post','home',obj).then(res=>{
                if(res.code == 200 ){
                    this.datas = res.data.list;
                    this.page.total = res.data.total;
                    if(Array.isArray(this.datas)){
                        this.datas.map(item=>{
                            item.isActive = item.isActive==1?'已激活':'未激活';
                        })
                    }
                }else if(res.code == 501){
                    // 登录token过期
                    this.logout();
                }

            })
        },
        changePage(current){
            this.page.current = current;
            this.getUserList();
        },
        changePageSize(pageSize){
            this.page.pageSize = pageSize;
            this.getUserList();
        },
        logout(falg){
            if(this.logout_flag || falg){
                let timer = 0;
                if(!falg){
                    timer = 1500;
                    this.logout_flag = false;
                    this.$Message.info("登陆信息已过期，即将跳转到登录页面！");
                }

                if(this.username){
                    this.send_request('post','login',{action:'logout',username:this.username});
                }
                setTimeout(()=>{
                    window.localStorage.setItem("userMsg","");
                    window.location.href = "/";
                },timer)
            }

        },
        send_request(method = 'get', url, data) {
            return new Promise((resolve, reject) => {
                $.ajax({
                    type: method,
                    url: url,
                    data: JSON.stringify(data),
                    headers: {
                        "Content-Type": "application/json;charset=UTF-8"
                    },
                    success: (res) => {
                        resolve(res);
                    },
                    error: err => {
                        console.log(err)
                        reject(err);
                    }
                })
            })
        },
        initEcharts(){
            this.myChart = echarts.init(document.getElementById('EchartsMap'));


            // 使用刚指定的配置项和数据显示图表。
            this.myChart.setOption(this.option);
        },

    }

});