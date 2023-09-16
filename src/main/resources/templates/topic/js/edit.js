let textContent = document.querySelector('.textContent');
let show = Vue.createApp({
        data(){
            return {
                flag:false,
                content:textContent.textContent
            }
        }
}).mount('.editTd');
let tips = document.querySelector('.grayTips');
let tipsContent = document.querySelector('.tipsContent').textContent;
tips.style.display = 'none';
if(tipsContent.length !== 0){
    tips.style.display = 'flex';
}
function h(num){
    let select = window.getSelection();
    let str = '';
    for(let i = 0;i < num;i++){
        str += '#';
    }
    str += ' '+select.toString();
    replaceText(str);
}
function b(){
    let select = window.getSelection();
    let str = '**'+select.toString()+'**';
    replaceText(str);
}
function i(){
    let select = window.getSelection();
    let str = '*'+select.toString()+'*';
    replaceText(str);
}
function code(){
    let select = window.getSelection();
    let str = '```\n'+select.toString()+'\n```';
    replaceText(str);
}
function line(){
    let str = '\n* * *\n';
    replaceText(str);
}
function del(){
    let select = window.getSelection();
    let str = '~~'+select.toString()+'~~';
    replaceText(str);
}
function a(){
    let select = window.getSelection();
    let str = '';
    if(select.toString().length == 0){
        str = '[请输入](href)';
    }
    else{
        str = '['+select.toString()+'](href)';
    }
    replaceText(str);
}
function blockquote(){
    let select = window.getSelection();
    let str = '> '+select.toString();
    replaceText(str);
}
function mark(){
    let select = window.getSelection();
    let str = '`'+select.toString()+'`';
    replaceText(str);
}
function center(){
    let select = window.getSelection();
    let str = '<center>'+select.toString()+'</center>';
    replaceText(str);
}
function color(c){
    let select = window.getSelection();
    let str = '<font color="'+c+'">'+select.toString()+'</font>';
    replaceText(str);
}
function fontFace(f){
    let select = window.getSelection();
    let str = '<font face="'+f+'">'+select.toString()+'</font>';
    replaceText(str);
}
function fontSize(s){
    let select = window.getSelection();
    let str = '<font size="'+s+'">'+select.toString()+'</font>';
    replaceText(str);
}
function picture(){
    let window = document.querySelector('.windows');
    window.style.display = 'block';
}
function selectPicture(){
    let href = document.querySelector('.pictureHref').value;
    let str = '![可输入文字]('+href+')';
    replaceText(str);
    let window = document.querySelector('.windows');
    window.style.display = 'none';
}
function table(){

}
function cleartips(num){
    let ajax = new XMLHttpRequest();
    let tips = document.querySelector('.grayTips');
    tips.style.display = 'none';

    if(num === 1){
        ajax.open('GET','/set/clearTips');
    }
    else{
        ajax.open('GET','/topic/clearTips');
    }
    ajax.send();
}
function replaceText(str){
    let text = document.querySelector('textarea');
    let start = text.selectionStart;
    let end = text.selectionEnd;
    let value = text.value;
    let newValue = value.substring(0,start) + str + value.substring(end);
    text.value = newValue;
}
function href(str){
    window.location.href = str;
}
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
