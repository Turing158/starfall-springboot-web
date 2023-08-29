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
            windows.style.display = "block";
            windowsContent.innerHTML = "点过啦点过啦！刷不了赞的啦！";
            break;
        case 4:
            windows.style.display = "block";
            windowsContent.innerHTML = "踩过啦踩过啦！你是有多讨厌这篇！";
            break;
    }

}