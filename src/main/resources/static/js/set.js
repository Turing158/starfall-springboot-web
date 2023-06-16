$(document).ready(function(){
    $(".ul_me").click(function(){
        // $("#i_square").hide();
        $("#information").hide();
        // $("#p_square").hide();
        $("#password").hide();
        // $("#me_square").show();
        $("#me").show();
        // $("#h_square").hide();
        $("#head_page").hide();
    });
    $(".ul_inf").click(function(){
        // $("#i_square").show();
        $("#information").show();
        // $("#p_square").hide();
        $("#password").hide();
        // $("#me_square").hide();
        $("#me").hide();
        // $("#h_square").hide();
        $("#head_page").hide();
    });
    $(".ul_pass").click(function(){
        // $("#i_square").hide();
        $("#information").hide();
        // $("#p_square").show();
        $("#password").show();
        // $("#me_square").hide();
        $("#me").hide();
        // $("#h_square").hide();
        $("#head_page").hide();
    });
    $(".head_set").click(function(){
        // $("#i_square").hide();
        $("#information").hide();
        // $("#p_square").hide();
        $("#password").hide();
        // $("#me_square").hide();
        $("#me").hide();
        // $("#h_square").show();
        $("#head_page").show();
    });
});
function moveSqurt(i){
    var s = document.getElementById("square");
    switch(i){
        case 1:s.style.top = 125+'px';break;
        case 2:s.style.top = 190+'px';break;
        case 3:s.style.top = 250+'px';break;
        case 4:s.style.top = 45+'px';break;
    }

}
// function display_me(){
//     var a = document.getElementById("i_square");
//     var b = document.getElementById("information");
//     var c = document.getElementById("p_square");
//     var d = document.getElementById("password");
//     var e = document.getElementById("me_square");
//     var f = document.getElementById("me");
//     a.style.display = "none";
//     b.style.display = "none";
//     c.style.display = "none";
//     d.style.display = "none";
//     e.style.display = "block";
//     f.style.display = "block";
// }
// function display_information(){
//     var a = document.getElementById("i_square");
//     var b = document.getElementById("information");
//     var c = document.getElementById("p_square");
//     var d = document.getElementById("password");
//     var e = document.getElementById("me_square");
//     var f = document.getElementById("me");
//     a.style.display = "block";
//     b.style.display = "block";
//     c.style.display = "none";
//     d.style.display = "none";
//     e.style.display = "none";
//     f.style.display = "none";
// }
// function display_password(){
//     var a = document.getElementById("i_square");
//     var b = document.getElementById("information");
//     var c = document.getElementById("p_square");
//     var d = document.getElementById("password");
//     var e = document.getElementById("me_square");
//     var f = document.getElementById("me");
//     a.style.display = "none";
//     b.style.display = "none";
//     c.style.display = "block";
//     d.style.display = "block";
//     e.style.display = "none";
//     f.style.display = "none";
// }
function check_password_length(){
    var new_p = document.getElementById("new_password").value;
    var tips = document.getElementById("tips_password");
    if(new_p.length < 6){
        tips.innerHTML = new_p.length+" 密码不得小于6个字符";
        tips.style.color = "rgb(255, 125, 125)";
    }
    else{
        tips.innerHTML = new_p.length+" ✔";
        tips.style.color = "rgb(120, 255, 138)";
    }
}
function check_password_equal(){
    var new_p = document.getElementById("new_password").value;
    var again_p = document.getElementById("again_password").value;
    var tips = document.getElementById("tips_again_password");
    var submit = document.getElementById("confirm_password");
    if(again_p === new_p && again_p != ""){
        tips.innerHTML = "✔";
        tips.style.color = "rgb(120, 255, 138)";
        submit.type = "submit";
    }
    else{
        tips.innerHTML = "× 两次密码不一样";
        tips.style.color = "rgb(255, 125, 125)";
        submit.type = "button";
    }
}
function seti_Change(){
    var img = document.getElementById("seti_img_code")
    //设置时间戳
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
}
function setp_Change(){
    var img = document.getElementById("setp_img_code")
    //设置时间戳
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
}



let fileInput = document.getElementById('file');
let preview1 = document.getElementById('img_preview1');
let preview2 = document.getElementById('img_preview2');
let preview3 = document.getElementById('img_preview3');
let submit = document.getElementById("submit_head");
// 监听change事件:
window.onload=function(){
    fileInput.addEventListener('change', function() {
        // 清除背景图片:
        preview1.style.backgroundImage = '';
        preview2.style.backgroundImage = '';
        preview3.style.backgroundImage = '';
        if (!fileInput.value) {
            submit.type="button";
            fileInput.value = "";
            info.innerHTML = '没有选择文件';
            return;
        }
        let file = fileInput.files[0];
        let size = file.size;
        if (size >= 5 * 1024 * 1024) {
            submit.type="button";
            fileInput.value = "";
            alert('文件大小超出限制(5MB)');
            info.innerHTML = '文件大小超出限制(5MB)';
            return false;
        }
        // 获取File信息:
        info.innerHTML = `文件名称:  ${file.name}<br>文件大小: ${(file.size/1024/1024).toFixed(2)}MB`;
        if (!['image/jpeg', 'image/png', 'image/gif'].includes(file.type)) {
            submit.type="button";
            fileInput.value = "";
            alert('不是有效的图片文件!');
            return;
        }
        submit.type="submit";
        // 读取文件:
        let reader = new FileReader();
        reader.onload = function(e) {
            let data = e.target.result;
            preview1.src = data;
            preview2.src = data;
            preview3.src = data;

        };
        // 以DataURL的形式读取文件:
        reader.readAsDataURL(file);

    });
}


