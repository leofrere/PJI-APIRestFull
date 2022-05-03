package com.api.controller;

import com.api.utils.GraphQLRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    
    @GetMapping("/request")
    public String request(@RequestParam String data) throws Exception{
        return GraphQLRequest.sendRequest(data);
    }

}
