const input = document.querySelector('.code-hidden');
    const items=document.querySelectorAll('.code-item');
    const reg = /^[a-z0-9]{0,6}$/;
    let tmp = '';
    input.addEventListener('focus',()=>{
        input.setSelectionRange(input.value.length-1,input.value.length);
        const val = input.value;
        if(!val){
            items[0].classList.add('active');
            return;
        }
        if(val.length < items.length){
            items[val.length].classList.add('active');
        }
        if(val.length == items.length){
            items[val.length - 1].classList.add('active');
        }
    });
    input.addEventListener('blur',()=>{
        items.forEach(item=>{
            item.classList.remove('active');
        });
    });
    input.addEventListener('input',(e)=>{
        items.forEach(item=>{
            item.textContent='';
            item.classList.remove('active');
        });
        const val = e.target.value;
        if(reg.test(val)){
            tmp = val;
        }
        else{
            input.value = tmp;
        }
        const arr = input.value.split('');
        if(arr.length){
            items[0].classList.add('active');
        }
        arr.forEach((item,index)=>{
            items[index].textContent = item;
            items[index].classList.add('active');
            if(items[index + 1]){
                items[index].classList.add('active');

            }
            if(index == items.length - 1){
                items[index].classList.add('active');
            }
        })
    });