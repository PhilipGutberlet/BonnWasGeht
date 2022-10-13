
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

$(document).ready(getEventList);
function getEventList(){
    $.ajax(
        {
            type: 'GET',
            url:'http://localhost:8080/events/listevents',
            dataType: 'json',
            crossDomain: true,
            error: function(){console.log("Error")} ,
            success: function(data){
                //loop through Arraylist and get each Event
                $.each(data, function(index, item){
                    const{title, shortdescription, description, startdatum, enddatum, opendAt, closedAt} = item;
                    var eventlist = document.getElementById('events'); //root

                    var event = document.createElement("div");
                    event.className = 'eventslot';
                
                    var eventimage = document.createElement("img");
                    eventimage.src = "images/sectionrestaurant.png";
                    eventimage.alt = "event";
                    event.appendChild(eventimage);
                
                    var titelu = document.createElement("div");
                    titelu.className='titelu';
                    var titelspan = document.createElement("span");
                    titelspan.innerHTML = title;
                    var br = document.createElement("br");
                    var desciption = document.createElement("p");
                    desciption.innerHTML = shortdescription;
                    titelu.appendChild(titelspan);
                    titelu.appendChild(br);
                    titelu.appendChild(desciption);
                    event.appendChild(titelu);
                
                    var zeiten = document.createElement("div");
                    zeiten.className = 'zeiten';
                    var opentime = document.createElement("p");
                    opentime.innerHTML = opendAt + "-" + closedAt;
                    zeiten.appendChild(opentime);
                    var period = document.createElement("p");
                    period.innerHTML = "bis " + enddatum;
                    zeiten.appendChild(period);
                    event.appendChild(zeiten);
                
                
                    eventlist.appendChild(event);
                });
            }         
        }
    )
}
