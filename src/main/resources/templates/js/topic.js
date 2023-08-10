let i = document.getElementById('topicContent').getElementsByTagName('tr').length;
let td = document.getElementById('topicDiv');
td.style.height = 50*(i-1)+3+"px";

function page(page_num,last_page,label) {
    if (page_num <= last_page && page_num >= 1) {
        if (label === null) {
            window.location.href = "topic?page=" + page_num;
        } else {
            window.location.href = "topic?label=" + label + "&page=" + page_num;
        }
    } else {
        alert("求求别翻了，好不T_T");
    }
}