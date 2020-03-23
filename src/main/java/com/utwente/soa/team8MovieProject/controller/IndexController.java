package com.utwente.soa.team8MovieProject.controller;


import com.utwente.soa.team8MovieProject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/movie")
public class IndexController {

    @Autowired
    private SearchService searchService;

    @RequestMapping (
            path = "/search",
            method = RequestMethod.GET
    )
    public ArrayList<String> searchMovie(@RequestParam String query){
        return searchService.search(query);
    }

}