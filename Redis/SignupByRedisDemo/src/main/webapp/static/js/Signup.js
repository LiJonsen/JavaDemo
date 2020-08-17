var app = new Vue({
    el: '#app',
    data: {
        username: "",
        password: "",
        email: "",
        signIn:{
            username:"",
            password:""
        },
        loadingStatus:false

    },
    created: function () {
        let activeRes = this.getCookie("activeCode");
        let user = window.localStorage.getItem("userMsg");

        try {
            // 激活成功
            if (activeRes) {
                let arr = activeRes.split("&");
                if (arr[1] == 200) {
                    this.signIn.username = arr[0];
                    this.showToast(arr[0]+"用户激活成功！");
                }
            }

            let msg = JSON.parse(user);
            if(msg.token && msg.user){
                window.location.href = "/home.html";
            }
        } catch (err) {
        }
    },
    methods: {
        getCookie(cname) {
            let name = cname + "=";
            let ca = document.cookie.split(';');
            for (let i = 0; i < ca.length; i++) {
                let c = ca[i].trim();
                if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
            }
            return "";
        },
        validateData(data){
            console.log(data);
            if(!data.username){
                this.showToast("请输入用户名！", 'red');
                return false;
            }
            if(!data.password){
                this.showToast("请输入密码！", 'red');
                return false;
            }

            if("email" in data && !data.email){
                this.showToast("请输入邮箱！", 'red');
                return false;
            }

            return true;
        },
        submitSignUp(e) {
            e.preventDefault();
            let obj = {
                action: "signUp",
                username: this.username,
                password: MD5(this.password),
                email: this.email
            }
            // 校验参数
            if(!this.validateData(obj)){
                return;
            }
            this.loadingStatus = true;
            this.send_request('post', 'login', obj).then(res => {
                this.loadingStatus = false;

                if (res.code == 200) {
                    this.showToast("注册成功，请前往邮箱激活账号！");
                    this.changeType('signIn');
                    setTimeout(()=>{
                        this.username = "";
                        this.password = "";
                        this.email = "";
                    },1000);
                    return;
                }
                if(res.code == 500){
                    this.showToast(res.msg, 'red');
                    return;
                }
                this.showToast("注册失败！", 'red');
            });
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
        submitSignIn(e) {
            e.preventDefault();
            let obj = {
                action: "signIn",
                username:this.signIn.username,
                password:MD5(this.signIn.password),
            }
            // 校验参数
            if(!this.validateData(obj)){
                return;
            }
            this.loadingStatus = true;

            this.send_request('post','login',obj).then(res=>{
                this.loadingStatus = false;

                if(res.code == 200){
                    let msg = {
                        user:obj.username,
                        token:res.data
                    }
                    window.localStorage.setItem("userMsg",JSON.stringify(msg));
                    window.location.href = "/home.html";
                }else{
                    this.showToast("账号或密码错误！", 'red');
                }
            })

        },
        showToast(msg, bgColor = 'green') {
            $.toast({
                text: msg,
                bgColor: bgColor,
                hideAfter: 3000,
                position: "top-center"
            })
        },
        changeType(type) {
            if (type == 'signUp') {
                $("#container")[0].classList.add("right-panel-active");
            } else {
                $("#container")[0].classList.remove("right-panel-active");
            }
        }
    }
})