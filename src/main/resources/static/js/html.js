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
