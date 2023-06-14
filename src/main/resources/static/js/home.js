var a = 0;
let num = 0;
c = window.setInterval(function(){},1000);
function home_change(i){
    var u = document.getElementById("home_ul");
    if(i>num || 21.35*a < 854*i){
        window.clearInterval(c);
        c = window.setInterval(function(){
            if(a>=120 || 21.35*a == 854*i){
                window.clearInterval(c);
                c= null;
            }
            u.style.left = -21.35*a+"px";
            a++;
        },10)  
    }
    if(i<num || 21.35*a > 854*i){
        window.clearInterval(c);
        c = window.setInterval(function(){
            if(a<=0 || 21.35*a == 854*i){
                window.clearInterval(c);
                c= null;
            }
            u.style.left = -21.35*a+"px";
            a--;
        },10)  
    }
    active(i)
    num = i;
}
function active(b){
    var lj = document.getElementsByTagName("img");
    for (let i = 4; i < lj.length; i++) {
        lj.item(i).className = "";
        
    }
    lj.item(b+13).className = "active";
}
