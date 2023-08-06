let ajax = new XMLHttpRequest();
let user = document.querySelector(".user_input")
let pwd = document.querySelector(".pass_input")
let email = document.querySelector(".email_input")
let tips = document.querySelector(".tips");
function checkUser(){
    if(user.value.length >= 3){
        ajax.open("POST", "/checkUser", true);
        ajax.setRequestHeader("user", user.value);
        ajax.send(user.value);
        ajax.onreadystatechange = function () {
            if(ajax.readyState === 4 && ajax.status === 200){
                let data = ajax.responseText;
                if(data === "exist"){
                    tips.style.color = "darkred";
                    tips.innerHTML = "用户名已存在";
                }
                else{
                    tips.style.color = "darkgreen";
                    tips.innerHTML = "用户名可用";
                }
            }
            else{
                tips.style.color = "darkred";
                tips.innerHTML = "!!!服务器异常!!!";
            }
        }
    }
    else{
        tips.style.color = "darkred";
        tips.innerHTML = "用户名长度不得小于3";
    }
}
function checkPwd() {
    if(pwd.value.length > 5){
        tips.style.color = "darkgreen";
        tips.innerHTML = "密码可用";
    }
    else{
        tips.style.color = "darkred";
        tips.innerHTML = "密码长度不得小于6";
    }
}

function checkEmail() {
    ajax.open("POST", "/checkEmail", true);
    ajax.setRequestHeader("email", email.value);
    ajax.send(email.value);
    ajax.onreadystatechange = function (){
        if(email.value.match(/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/)){
            if(ajax.readyState === 4 && ajax.status === 200){
                let data = ajax.responseText;
                if(data === "exist"){
                    tips.style.color = "darkred";
                    tips.innerHTML = "邮箱已注册";
                }
                else{
                    tips.style.color = "darkgreen";
                    tips.innerHTML = "邮箱可用";
                }
            }
            else{
                tips.style.color = "darkred";
                tips.innerHTML = "!!!服务器异常!!!";
            }
        }
        else{
            tips.style.color = "darkred";
            tips.innerHTML = "邮箱格式错误";
        }
    }

}

function Change() {
    var img = document.getElementById("img_code");
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
}