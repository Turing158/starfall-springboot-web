$(document).ready(function(){
    $('input').focus();
    $('.code-box').click(function(){
        $('input').focus();
    });
});
function login(){
    window.location.href = "/login";
}
function Gologin(){
    let i = 3
    gotime = setInterval(function () {
        i--;
        document.getElementById("sec").innerHTML = i.toString();
    },1000)
    jump = setInterval(function(){
        window.location.href = "/login";
    },3000);
}
// function update_big(){
//     document.getElementById("code-hidden").value.toUpperCase();
// }