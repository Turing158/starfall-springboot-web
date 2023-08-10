let htmlContend = document.getElementById("contentMd").innerText;
    console.log(htmlContend);
    let mark = marked.parse(htmlContend,{ breaks: true });
    console.log(mark);
    document.getElementById("contentMd").innerHTML = mark;
function page(page_num,last_page,user,html) {
    if (page_num <= last_page && page_num >= 1) {
        if (user === null) {
            window.location.href = "html?html="+html+"&page=" + page_num;
        } else {
            window.location.href = "html?html="+html+"&user=" + user + "&page=" + page_num;
        }
    } else {
        alert("求求别翻了，好不T_T");
    }
}
function showAll(html){
    window.location.href = "html?html="+html;
}