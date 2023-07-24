function checkLength(){
    let password = document.querySelector(".pass_input").value;
    let info = document.querySelector("p");
    if(password.length < 6){
        info.innerHTML = "你输入的密码长度不足6位";
    }
    else{
        checkRepeat();
    }
}
function checkRepeat(){
    let submit = document.querySelector(".submit");
    let password1 = document.querySelector(".pass_input").value;
    let password2 = document.querySelector(".confirmPass_input").value;
    let info = document.querySelector("p");
    if(password1 == password2){
        info.innerHTML = "✔";
        submit.innerHTML = "<input type='submit' value='确认' class='confirm'>";
    }
    else{
        info.innerHTML = "你输入的密码与第一次输入不同";
        submit.innerHTML = "";
    }
}