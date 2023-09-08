function pageChange(page_num,last_page,user) {
    if (page_num <= last_page && page_num >= 1) {
        window.location.href = "personal?user="+user+"&page=" + page_num;
    } else {
        alert("求求别翻了，好不T_T");
    }
}