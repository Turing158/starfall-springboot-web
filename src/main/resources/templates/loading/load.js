document.onreadystatechange = function () {
    let state = document.readyState;
    let html =  document.querySelector('html');
    let load = document.querySelector('.load');
    html.style.overflow = "hidden";
    if(state === 'complete'){
        load.style.display = "none";
        html.style.overflowY = "auto";
    }
}