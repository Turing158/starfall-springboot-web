let i = 0;
timer = setInterval(function (){
    switch (i) {
        case 0:
            document.title = "登录+|StarFall|";
            break;
        case 1:
            document.title = "登录×|starfall|";
            break;
        case 2:
            document.title = "登录+|Starfall|";
            break;
        case 3:
            document.title = "登录×|sTarfall|";
            break;
        case 4:
            document.title = "登录+|stArfall|";
            break;
        case 5:
            document.title = "登录×|staRfall|";
            break;
        case 6:
            document.title = "登录+|starFall|";
            break;
        case 7:
            document.title = "登录×|starfAll|";
            break;
        case 8:
            document.title = "登录+|starfaLl|";
            break;
        case 9:
            document.title = "登录×|starfalL|";
            break;
        case 10:
            document.title = "登录+|STARfall|";
            break;
        case 11:
            document.title = "登录×|starFALL|";
            break;
        case 12:
            document.title = "登录+|starfall|";
            break;
    }
    i++;
    if (i >= 13){
        i = 0;
    }
},500);