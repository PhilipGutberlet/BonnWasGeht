
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
    //for(var i=0; i < document.getElementById('fileinput').files.length(); i++){
    //    form.append('files[]', document.getElementById('fileinput').files[i]);
    //}
    form.append('shortdescription', document.getElementById('shortdescription').value);
    form.append('description', document.getElementById('description').value);
    form.append('startdatum', document.getElementById('startdatum').value);
    form.append('enddatum', document.getElementById('enddatum').value);
    form.append('opendAt', document.getElementById('opendAt').value);
    form.append('closedAt', document.getElementById('closedAt').value);
    form.append('picture', document.getElementById('fileinput').files[0]);
    form.append('section', document.getElementById('section').value);

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

//$(document).ready(getEventList);
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
                    const{eventID, title, picturename, shortdescription, description, startdatum, enddatum, opendAt, closedAt, bytearray} = item;
                  
                    var eventlist = document.getElementById('events'); //root

                    var link = document.createElement("a");
                    link.href = "eventside.html?EventID="+eventID;

                    var event = document.createElement("div");
                    event.className = 'eventslot';
                
                    var eventimage = document.createElement("img");
                                      
                    eventimage.src = 'data:image/png;base64,'+bytearray;
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
                
                    link.appendChild(event);
                    eventlist.appendChild(link);
                });
            }         
        }
    )
}

function getUrlParam(EventID) {
    var url_string = window.location;
    var url = new URL(url_string);
    var c = url.searchParams.get(EventID);
    return c;
}

function getEvent(){
    $.ajax(
        {
            type: 'GET',
            url:'http://localhost:8080/events/getEvent',
            dataType: 'json',
            data: {'EventID': getUrlParam("EventID")},
            crossDomain: true,
            error: function(){console.log("Error")} ,
            success: function(data){
                const{title, picturename, description, bytearray} = data;
                var presentedevent = document.getElementById('presentedevent');   
                
                var eventname = document.createElement("div");
                eventname.className = 'eventname';
                var eventtitle = document.createElement("div");
                eventtitle.className = 'css-3d-text-red evs';
                eventtitle.innerHTML=title;
                eventname.appendChild(eventtitle);
                presentedevent.appendChild(eventname);

                var picturebar = document.createElement("div");
                picturebar.className = 'eventpicturebar';
                var image = document.createElement("img");
                image.src = 'data:image/png;base64,'+bytearray;
                image.alt = "eventbar";
                picturebar.appendChild(image);
                
                var image1 = document.createElement("img");
                image1.src = 'data:image/png;base64,'+bytearray;
                image1.alt = "eventbar";
                picturebar.appendChild(image1);

                var image2 = document.createElement("img");
                image2.src = 'data:image/png;base64,'+bytearray;
                image2.alt = "eventbar";
                picturebar.appendChild(image2);

                var image3 = document.createElement("img");
                image3.src = 'data:image/png;base64,'+bytearray;
                image3.alt = "eventbar";
                picturebar.appendChild(image3);
                presentedevent.appendChild(picturebar);

                var div = document.createElement("div");
                var eventdescription = document.createElement("div");
                eventdescription.className = 'eventdescription';
                eventdescription.innerHTML=description;
                div.appendChild(eventdescription);
                presentedevent.appendChild(div);

                var buttoncon = document.createElement("div");
                buttoncon.className = 'buttoncon';
                var href = document.createElement("a");
                href.href = "eventlist.html";
                buttoncon.appendChild(href);
                var submit = document.createElement("input");
                submit.type = "submit";
                submit.value = "Zurück";
                href.appendChild(submit);
                presentedevent.appendChild(buttoncon);
            }         
        }
    )
}
