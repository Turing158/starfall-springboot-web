document.onreadystatechange = function () {
    let state = document.readyState;
    let load = document.querySelector('.load');
    if(state === 'complete'){
        load.style.display = "none";
    }

}