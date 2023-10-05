let ketContent = document.querySelectorAll(".keyContent");
let key = document.querySelector(".key").value;
for (let i = 0; i < ketContent.length; i++) {
    let el = ketContent[i].textContent;
    let els = el.split(key);
    let newEl = "";
    for (let j = 0; j < els.length; j++) {
        let content = els[j] + "<em>" + key + "</em>"
        if(j === els.length - 1){
            content = els[j];
        }
        newEl = newEl + content;
    }
    ketContent[i].innerHTML = newEl;
}

function page(page_num,last_page,search) {
    if (page_num <= last_page && page_num >= 1) {
        window.location.href = "/topic/search?search="+search+"&page=" + page_num;
    } else {
        alert("求求别翻了，好不T_T");
    }
}