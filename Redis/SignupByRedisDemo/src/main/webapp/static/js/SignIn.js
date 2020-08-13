var app = new Vue({
    el: '#app',
    data: {
        username: "",
        token:""
    },
    created: function () {
        let user = window.sessionStorage.getItem("userMsg");
        if(user){
            try{
                let msg = JSON.parse(user);
                this.username = msg.user;
                this.token = msg.token;
            }catch (err) {

            }
        }
    }
});