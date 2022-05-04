package com.api.controller;

import com.api.utils.GraphQLRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RequestController {
    
    @CrossOrigin
    @GetMapping("/request")
    public String request(@RequestParam String query) throws Exception{
        return GraphQLRequest.sendRequest(query);
    }

}
