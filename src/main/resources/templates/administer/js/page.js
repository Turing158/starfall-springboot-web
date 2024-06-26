function start(){
    // 此部分为接收之前点击过的页面代码===================================================
    let input = document.querySelector('.pageHide').value;
    if(input !== ''){
        pageTransform(parseInt(input));
    }
    // =================================================================================
    getOnline();
}

function getOnline(){
    let ajax = new XMLHttpRequest();
    let onlineCount = document.querySelector('.onlineCount');
    ajax.open('get','/administer/getOnline',true);
    ajax.send();
    ajax.onreadystatechange = function(){
        if(ajax.readyState === 4 && ajax.status === 200){
            onlineCount.innerHTML = ajax.responseText;
        }
    }
}
function pageTransform(page){
    let square = document.querySelector('.square');
    let home = document.querySelector('.homePage');
    let user = document.querySelector('.userPage');
    let topic = document.querySelector('.topicPage');
    let notice = document.querySelector('.noticePage');
    let comment = document.querySelector('.commentPage');
    let like = document.querySelector('.likePage');
    switch(page){
        case 1:
            square.style.top = '90px';
            home.style.scale='1';
            user.style.scale='0';
            topic.style.scale='0';
            notice.style.scale='0';
            comment.style.scale='0';
            like.style.scale='0';
            break;
        case 2:
            square.style.top = '137px';
            home.style.scale='0';
            user.style.scale='1';
            topic.style.scale='0';
            notice.style.scale='0';
            comment.style.scale='0';
            like.style.scale='0';
            break;
        case 3:
            square.style.top = '184px';
            home.style.scale='0';
            user.style.scale='0';
            topic.style.scale='1';
            notice.style.scale='0';
            comment.style.scale='0';
            like.style.scale='0';
            break;
        case 4:
            square.style.top = '231px';
            home.style.scale='0';
            user.style.scale='0';
            topic.style.scale='0';
            notice.style.scale='1';
            comment.style.scale='0';
            like.style.scale='0';
            break;
        case 5:
            square.style.top = '278px';
            home.style.scale='0';
            user.style.scale='0';
            topic.style.scale='0';
            notice.style.scale='0';
            comment.style.scale='1';
            like.style.scale='0';
            break;
        case 6:
            square.style.top = '325px';
            home.style.scale='0';
            user.style.scale='0';
            topic.style.scale='0';
            notice.style.scale='0';
            comment.style.scale='0';
            like.style.scale='1';
            break;
    }
}
function setPage(num){
    let ajax = new XMLHttpRequest();
    ajax.open('post','/administer/setPage',true);
    ajax.setRequestHeader("page",num);
    ajax.send(num);

}

function Page(pageNum,lastPage,page){
    let pageStr = '';
    switch (page){
        case 1:
            pageStr = 'U';
            break;
        case 2:
            pageStr = 'T';
            break;
        case 3:
            pageStr = 'N';
            break;
        case 4:
            pageStr = 'C';
            break;
        case 5:
            pageStr = 'G';
            break;
    }
    if(pageNum <= 0){
        alert('已经是第一页了');
    }
    else if(pageNum > lastPage){
        alert('已经是最后一页了');
    }
    else{
        window.location.href= '/administer/html?page'+pageStr+'=' + pageNum;
    }
}
function href(href){
    window.location.href = href;
}

function hideTips(){
    let tips = document.querySelector('.tips');
    let ajax = new XMLHttpRequest();
    tips.style.display = 'none';
    ajax.open('get','/administer/clearTips',true);
    ajax.send();
}