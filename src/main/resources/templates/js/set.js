let page = document.querySelector('.pageValue').value;
let pageNum = Number(page);
let tips = document.querySelector('.grayTips');
let tipsContent = document.querySelector('.tipsContent').textContent;
if(tipsContent !== ""){
    tips.style.display = "flex";
}
else{
    tips.style.display = "none";
}
showPage(1);
if(pageNum >= 1 && pageNum <= 4){
    showPage(pageNum);
}
function moveSquare(i){
    let s = document.getElementById("square");
    switch(i){
        case 1:s.style.top = 110+'px';break;
        case 2:s.style.top = 170+'px';break;
        case 3:s.style.top = 230+'px';break;
        case 4:s.style.top = 40+'px';break;
    }

}
$(document).ready(function(){
    $(".ul_me").click(function (){
        showPage(1);
        pageSave(1);
        });
    $(".ul_inf").click(function(){
        showPage(2);
        pageSave(2);
    });
    $(".ul_pass").click(function(){
        showPage(3);
        pageSave(3);
    });
    $(".head_set").click(function(){
        showPage(4);
        pageSave(4);
    });
});
function showPage(num){
    $("#information").hide();
    $("#password").hide();
    $("#me").hide();
    $("#head_page").hide();
    switch (num) {
        case 1:$("#me").show();break;
        case 2:$("#information").show();seti_Change();break;
        case 3:$("#password").show();setp_Change();break;
        case 4:$("#head_page").show();break;
    }
    switch (num) {
        case 1:moveSquare(1);break;
        case 2:moveSquare(2);break;
        case 3:moveSquare(3);break;
        case 4:moveSquare(4);break;
    }
}

function check_password_length(){
    var new_p = document.getElementById("new_password").value;
    var tips = document.getElementById("tips_password");
    if(new_p.length < 6){
        tips.innerHTML = new_p.length+" 密码不得小于6个字符";
        tips.style.color = "darkred";
        return false;
    }
    else{
        tips.innerHTML = new_p.length+" ✔";
        tips.style.color = "green";
        return true;
    }
}
function check_password_equal(){
    var new_p = document.getElementById("new_password").value;
    var again_p = document.getElementById("again_password").value;
    var tips = document.getElementById("tips_again_password");
    var submit = document.getElementById("confirm_password");
    if(again_p === new_p && again_p != ""){
        tips.innerHTML = "✔";
        tips.style.color = "green";
        if(check_password_length()){
            submit.type = "submit";
        }
    }
    else{
        tips.innerHTML = "× 两次密码不一样";
        tips.style.color = "darkred";
        submit.type = "button";
    }
}
function seti_Change(){
    var img = document.getElementById("seti_img_code");
    var input = document.getElementById("varifyInputI");
    //设置时间戳
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
    input.focus();
}
function setp_Change(){
    var img = document.getElementById("setp_img_code");
    var input = document.getElementById("varifyInputP");
    //设置时间戳
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
    input.focus();
}

function pageSave(num){
    let ajax = new XMLHttpRequest();
    ajax.open("post","/set/savePage");
    ajax.setRequestHeader("page",num);
    ajax.send(num);
}

let fileInput = document.getElementById('file');
let preview1 = document.getElementById('img_preview1');
let preview2 = document.getElementById('img_preview2');
let preview3 = document.getElementById('img_preview3');
let submit = document.getElementById("submitHead");
let info = document.getElementById('info');
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
function closeTips(){
    let tips = document.querySelector('.grayTips');
    tips.style.display = "none";
    let ajax = new XMLHttpRequest();
    ajax.open("post","/set/clearTips");
    ajax.send();
}

function pageChange(page_num,last_page) {
    if (page_num <= last_page && page_num >= 1) {
        window.location.href = "set?page=" + page_num;
    } else {
        alert("求求别翻了，好不T_T");
    }
}