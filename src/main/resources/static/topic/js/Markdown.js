let htmlContend = document.getElementById("contentMd").innerText;
    console.log(htmlContend);
    let mark = marked.parse(htmlContend,{ breaks: true });
    console.log(mark);
    document.getElementById("contentMd").innerHTML = mark;
    