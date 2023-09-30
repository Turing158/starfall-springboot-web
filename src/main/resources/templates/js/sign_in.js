let date = Date().toString().split(' ');
let month = checkMonth(date[1]);
let day = date[2];
let year = date[3];
document.querySelector('.date').innerHTML = year+'-'+month+'-'+day;
document.querySelector('.status0').innerHTML = day+' | 签 到';
document.querySelector('.status1').innerHTML = day+' |已 签 到';


let noSign = document.querySelector('#noSign');
let alreadySign = document.querySelector('#alreadySign');
let ajax = new XMLHttpRequest();
alreadySign.style.display = 'none';
ajax.open('GET', '/signIn/isSignInToday', true);
ajax.send();
ajax.onreadystatechange = function(){
    if (ajax.readyState === 4 && ajax.status === 200){
        if (ajax.responseText === 'true'){
            noSign.style.display = 'none';
            alreadySign.style.display = 'flex';
        }
    }
}
function checkMonth(month){
    switch(month){
        case 'Jan': return '01';
        case 'Feb': return '02';
        case 'Mar': return '03';
        case 'Apr': return '04';
        case 'May': return '05';
        case 'Jun': return '06';
        case 'Jul': return '07';
        case 'Aug': return '08';
        case 'Sep': return '09';
        case 'Oct': return '10';
        case 'Nov': return '11';
        case 'Dec': return '12';
    }
}
function sign(){
    window.location.href = "/signIn/sign";
}