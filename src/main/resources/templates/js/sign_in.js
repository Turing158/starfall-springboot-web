let date = Date().toString().split(' ');
let month = checkMonth(date[1]);
let day = date[2];
let year = date[3];
document.querySelector('.date').innerHTML = year+'-'+month+'-'+day;
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