// 弃用js轮播图，该成bootstrap轮播
// var i = 1;
// window.onloadstart=ppt_start()
// function ppt_start(){
//     change_dot(i);
//     ppt_time = setInterval("ppt_right()",3000);
      
// }
// function start(){
//     ppt_start();
     
// }
// function stop(){
//     clearInterval(ppt_time);
// }
// function ppt_left(){
//     i--;
//     if(i<=0){
//         i=4;
//     }
//     change_img();
//     change_dot(i);
// }
// function ppt_right(){
//     i++;
//     if(i>=5){
//         i=1;
//     }
//     change_img();
//     change_dot(i);
// }
// function change_dot(i){
//     var a = document.getElementById("pd1");
//     var b = document.getElementById("pd2");
//     var c = document.getElementById("pd3");
//     var d = document.getElementById("pd4");
//     switch(i){
//         case 1:
//             a.style.backgroundColor="rgb(202, 202, 202)";
//             b.style.backgroundColor="rgb(34, 34, 34)";
//             c.style.backgroundColor="rgb(34, 34, 34)";
//             d.style.backgroundColor="rgb(34, 34, 34)";
//             break;
//         case 2:
//             a.style.backgroundColor="rgb(34, 34, 34)";
//             b.style.backgroundColor="rgb(202, 202, 202)";
//             c.style.backgroundColor="rgb(34, 34, 34)";
//             d.style.backgroundColor="rgb(34, 34, 34)";
//             break;
//         case 3:
//             a.style.backgroundColor="rgb(34, 34, 34)";
//             b.style.backgroundColor="rgb(34, 34, 34)";
//             c.style.backgroundColor="rgb(202, 202, 202)";
//             d.style.backgroundColor="rgb(34, 34, 34)";
//             break;
//         case 4:
//             a.style.backgroundColor="rgb(34, 34, 34)";
//             b.style.backgroundColor="rgb(34, 34, 34)";
//             c.style.backgroundColor="rgb(34, 34, 34)";
//             d.style.backgroundColor="rgb(202, 202, 202)";
//             break;
//     }
// }
// function click_dot1(){
//     i=1;
//     change_img();
//     change_dot(i);
// }
// function click_dot2(){
//     i=2;
//     change_img();
//     change_dot(i);
// }
// function click_dot3(){
//     i=3;
//     change_img();
//     change_dot(i);
// }
// function click_dot4(){
//     i=4;
//     change_img();
//     change_dot(i);
// }
// function change_img(){
//     var a = document.getElementById("ppt");
//     a.style.backgroundImage="url(./img/top/top"+i+".png)";
// }