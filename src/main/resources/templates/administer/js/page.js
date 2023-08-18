function start(){
    let input = document.querySelector('.pageHide').value;
    if(input !== ''){
        pageTransform(parseInt(input));
    }
}
function pageTransform(page){
    let square = document.querySelector('.square');
    let home = document.querySelector('.homePage');
    let user = document.querySelector('.userPage');
    let topic = document.querySelector('.topicPage');
    let notice = document.querySelector('.noticePage');
    let comment = document.querySelector('.commentPage');
    switch(page){
        case 1:
            square.style.top = '90px';
            home.style.scale='1';
            user.style.scale='0';
            topic.style.scale='0';
            notice.style.scale='0';
            comment.style.scale='0';
            break;
        case 2:
            square.style.top = '137px';
            home.style.scale='0';
            user.style.scale='1';
            topic.style.scale='0';
            notice.style.scale='0';
            comment.style.scale='0';
            console.log("q");
            break;
        case 3:
            square.style.top = '184px';
            home.style.scale='0';
            user.style.scale='0';
            topic.style.scale='1';
            notice.style.scale='0';
            comment.style.scale='0';
            break;
        case 4:
            square.style.top = '231px';
            home.style.scale='0';
            user.style.scale='0';
            topic.style.scale='0';
            notice.style.scale='1';
            comment.style.scale='0';
            break;
        case 5:
            square.style.top = '278px';
            home.style.scale='0';
            user.style.scale='0';
            topic.style.scale='0';
            notice.style.scale='0';
            comment.style.scale='1';
            break;
    }
}
function setPage(num){
    let ajax = new XMLHttpRequest();
    ajax.open('post','/administer/setPage',true);
    ajax.setRequestHeader("page",num);
    ajax.send(num);

}