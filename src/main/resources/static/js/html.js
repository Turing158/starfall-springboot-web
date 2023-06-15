$(document).ready(function(){
    $("#ul_home").click(function(){
        $(".home").show();
        $(".works").hide();
        $(".video").hide();
        $(".comment").hide();
        change_home();

    });
    $("#ul_works").click(function(){
        $(".home").hide();
        $(".works").show();
        $(".video").hide();
        $(".comment").hide();
        change_works();

    });
    $("#ul_video").click(function(){
        $(".home").hide();
        $(".works").hide();
        $(".video").show();
        $(".comment").hide();
        change_video();
    });
    $("#ul_comment").click(function(){
        $(".home").hide();
        $(".works").hide();
        $(".video").hide();
        $(".comment").show();
        change_comment();
    });
});
function change_home(){
    var a = document.getElementById("ul_home");
    var b = document.getElementById("ul_works");
    var c = document.getElementById("ul_video");
    var d = document.getElementById("ul_comment");
    a.className = "active";
    b.className = "";
    c.className = "";
    d.className = "";
    document.documentElement.style.setProperty(
        "--body_height",
        "1000px"
    );
}
function change_works(){
    var a = document.getElementById("ul_home");
    var b = document.getElementById("ul_works");
    var c = document.getElementById("ul_video");
    var d = document.getElementById("ul_comment");
    a.className = "";
    b.className = "active";
    c.className = "";
    d.className = "";
    document.documentElement.style.setProperty(
        "--body_height",
        "1000px"
    );
}
function change_video(){
    var a = document.getElementById("ul_home");
    var b = document.getElementById("ul_works");
    var c = document.getElementById("ul_video");
    var d = document.getElementById("ul_comment");
    a.className = "";
    b.className = "";
    c.className = "active";
    d.className = "";
    document.documentElement.style.setProperty(
        "--body_height",
        "1000px"
    );
}
function change_comment(){
    var a = document.getElementById("ul_home");
    var b = document.getElementById("ul_works");
    var c = document.getElementById("ul_video");
    var d = document.getElementById("ul_comment");
    a.className = "";
    b.className = "";
    c.className = "";
    d.className = "active";
    document.documentElement.style.setProperty(
        "--body_height",
        "2000px"
    );
}
var count = 0;
function return_top(){
    window.scrollBy(0, -100);
    count = setTimeout("return_top()", 10);
    if (document.documentElement.scrollTop <= 1) {
        clearTimeout(count);
    }
}
//这个播放公告动画会卡顿
// function notice_start(noticeLenght){
//     var start_notice = 0;
//     var notice = document.getElementById("notice");
//     window.notice_interval = setInterval(function(){
//         notice.style = 'left:'+start_notice+'px;width:'+noticeLenght*1000+2000+'px;'
//         start_notice -= 5;
//         if(Math.abs(start_notice) >= noticeLenght*1000+600){
//             start_notice = 0;
//         }
//     },50);

// }
//此方法采用 requestAnimationFrame() 解决卡顿问题
let start_notice = 0;
function notice_start(){
    // if(start_notice < -500){
    requestAnimationFrame(notice_start);
    // }
    let noticeLength = document.getElementById("NoticeLength").value*1200+4000;
    let notice = document.getElementById("notice");
    notice.style = 'left:'+start_notice+'px;width:'+noticeLength+'px;';
    start_notice -= 1;
    if(Math.abs(start_notice) >= noticeLength-2000){
        start_notice = 0;
    }
}