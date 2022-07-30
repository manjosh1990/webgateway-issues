package com.example.restservice.microservices.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class RestServiceController {

    @GetMapping("/hello")
    public ResponseEntity<String> getMessage(){
        return new  ResponseEntity<>("Hello world", HttpStatus.OK);
    }

    @RequestMapping(value = "/post/{messageType}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CrossOrigin
    public ResponseEntity<?>   postMessage( @PathVariable String messageType,@RequestParam(value = "rowData") String rowData) {
        System.out.println(rowData);
        JSONObject object = new JSONObject(rowData);
        object.put(messageType,messageType);
        System.out.println("Created");
        return new  ResponseEntity<>(object.toString(), HttpStatus.OK);
    }
}
