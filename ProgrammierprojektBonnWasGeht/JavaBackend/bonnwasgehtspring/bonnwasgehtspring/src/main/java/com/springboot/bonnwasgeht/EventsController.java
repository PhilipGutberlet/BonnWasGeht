package com.springboot.bonnwasgeht;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventsController{

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(value="/addEvent",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public Response addEvent(@ModelAttribute Event event, @RequestParam MultipartFile picture) throws IOException {
        FileStorageSystem.storeMultiPartFile(picture);
        event.setPicturename(picture.getOriginalFilename());
        EventList.addEventToDB(event);
        Response response = new Response("Done", event);
        return response;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @RequestMapping(value="/listevents", method = RequestMethod.GET)
    public ArrayList<Event> listEvents(){
        ArrayList<Event> eventlist = EventList.getEvents();
        return eventlist;
    }
}


