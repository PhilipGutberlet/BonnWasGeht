
function saveUserData(){   
    let weekday = document.getElementById('weekday').value;
    localStorage.setItem('weekday', weekday);
    
}

function loadsectionpage(){
    document.getElementById('varweekday').innerHTML = localStorage.getItem('weekday');
}


function postEventData(){
    $.ajax(
        {
            type: 'POST',
            url:'nocjkeintevorhadnen',
            dataType: 'json',
            data: {
                title: document.getElementById('title').value,
                picture:,
                shortdescription: document.getElementById('shortdescription'),
                description: document.getElementById('description').value,
                startdatum: document.getElementById('startdatum').value,
                enddatum: document.getElementById('enddatum').value,
                opendAt: document.getElementById('opendAt').value,
                closedAt: document.getElementById('closedAt').value
            }
        }
    )
}

function getEventList(){
    $ajax(
        {
            type: 'GET',
            url:'nocjkeintevorhadnen',
            dataType: 'json',
            succes: function(data){
                
            }
        }
    )
}
