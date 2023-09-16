
function transformToMd(){
    let htmlContend = document.querySelector('textarea').value;
    let mark = marked.parse(htmlContend,{ breaks: true });
    document.getElementById("contentMd").innerHTML = mark;
}