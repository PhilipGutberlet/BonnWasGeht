
function saveUserData(){   
    let weekday = document.getElementById('weekday').value;
    localStorage.setItem('weekday', weekday);
    
}

function loadsectionpage(){
    document.getElementById('varweekday').innerHTML = localStorage.getItem('weekday');
}


function postEventData(){
    
    let form = new FormData();

    form.append('title', document.getElementById('title').value);
    /*    for(var i=0; i < document.getElementById('fileinput').get[0].files.length(); i++){
            form.append('files[]', $('.fileinput').get(0).files[i]);
    }*/
    form.append('shortdescription', document.getElementById('shortdescription').value);
    form.append('description', document.getElementById('description').value);
    form.append('startdatum', document.getElementById('startdatum').value);
    form.append('enddatum', document.getElementById('enddatum').value);
    form.append('opendAt', document.getElementById('opendAt').value);
    form.append('closedAt', document.getElementById('closedAt').value);


    for (var pair of form.entries()) {
        console.log(pair[0]+ ', ' + pair[1]); 
    }

    
    $.ajax(
        {
            type: 'POST',
            url:'http://localhost:8080/events/addEvent',
            dataType: 'json',
            data: form,
            crossDomain: true,
            contentType: false,
            processData: false,
            succes: function(data){
                console.log(data)
            }
        }
    );
    
}

function getEventList(){
    $ajax(
        {
            type: 'GET',
            url:'http://localhost:8080',
            dataType: 'json',
            succes: function(data){

            }
        }
    )
}
