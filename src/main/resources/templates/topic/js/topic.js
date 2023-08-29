function like(num){
    let windows = document.querySelector(".windows");
    document.querySelector(".windowsTitle").innerHTML = "!StarFall提示您！";
    let windowsContent = document.querySelector(".windowsContent");
    switch (num){
        case 0:
            windows.style.display = "block";
            windowsContent.innerHTML = "请登录后再点赞哦！";
            break;
        case 1:
            window.location.href = "/topic/like";
            break;
        case 2:
            window.location.href = "/topic/dislike";
            break;
        case 3:
            window.location.href = "/topic/cancelLike";
            break;
    }
}
function showAll(num){
    window.location.href = '/topic/html?html='+num;
}

function page(page_num,last_page,user,html) {
    if (page_num <= last_page && page_num >= 1) {
        if (user === null) {
            window.location.href = "/topic/html?html="+html+"&page=" + page_num;
        } else {
            window.location.href = "/topic/html?html="+html+"&user=" + user + "&page=" + page_num;
        }
    } else {
        alert("求求别翻了，好不T_T");
    }
}