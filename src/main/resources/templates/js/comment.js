function Check(textarea, maxLines, maxChar) {
    var lines = textarea.value.replace(/\r/g, '').split('\n'), lines_removed, char_removed, i;
    var tips = document.getElementById("comment_tips");
    var ac_tips = document.getElementById("ac_comment_top_tips");
    var button = document.getElementById("ac_submit");
    if(textarea.value.length < 10){
        tips.innerHTML = "小于10字不给发话！";
        tips.style.color = "darkred";
        button.type = "button";
    }
    if(textarea.value.length >= 10 && textarea.value.length <= 255){
        button.type = "submit";
        tips.innerHTML = "✓";
        tips.style.color = "darkgreen";
    }
    if(textarea.value.length === 255){
        tips.innerHTML = "最多255字,不给输啦";
        tips.style.color = "darkred";
    }
    if (maxLines && lines.length > maxLines) {
        lines = lines.slice(0, maxLines);
        lines_removed = 1;
        tips.innerHTML = "最多10行啊,别输啦T_T";
        tips.style.color = "darkred";
    }
    if (maxChar) {
        i = lines.length;
        while (i-- > 0) if (lines[i].length > maxChar) {
            lines[i] = lines[i].slice(0, maxChar);
            char_removed = 1;

        }
        if (char_removed || lines_removed) {
            textarea.value = lines.join('\n');
        }
        
    }
    ac_tips.innerHTML = lines.length+" 行 | "+textarea.value.length+" 字数"
}
function page(page_num,last_page,only_user){
    if(page_num <= last_page && page_num >= 1){
        if (only_user === null){
            window.location.href = "home?page="+page_num;
        }
        else{
            window.location.href = "home?only_user="+only_user+"&page="+page_num;
        }
    }
    else{
        alert("求求别翻了，好不T_T");
    }
}
function comment_all(){
    window.location.href = "home?page=1";
}
function change(){
    var img = document.getElementById("VerifyCode_img");
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
}
