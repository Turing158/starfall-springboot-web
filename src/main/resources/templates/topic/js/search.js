function page(page_num,last_page,search) {
    if (page_num <= last_page && page_num >= 1) {
        window.location.href = "/topic/search?search="+search+"&page=" + page_num;
    } else {
        alert("求求别翻了，好不T_T");
    }
}