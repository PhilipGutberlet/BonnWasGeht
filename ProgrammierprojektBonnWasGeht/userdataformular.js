let button = document.getElementById("losghets");
button.addEventListener("click", machWas());

function machWas(event){
        event.preventDefault();
        let weekday=document.getElementById("weekday");
        console.log(weekday.value);
}

/*function saveUserData(){
    event.preventDefault();
    let weekday = document.getElementById('weekday').ariaValueMax;
    console.log(weekday)
    
}*/

function getWeekday(){
    return sessionStorage.getItem('weekday');
}


