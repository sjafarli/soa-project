package com.utwente.soa.team8MovieProject.services.impl;

import com.utwente.soa.team8MovieProject.services.SearchService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
public class SearchServiceImpl implements SearchService {

    private String[] movies = {"Frozen", "Cars", "Batman: The dark Knight", "Batman: Origins", "The Joker", "It: Ends", "21 Jump Street"};

    @Override
    public ArrayList<String> search(String query) {
        // list of results fitting the query
        ArrayList<String> result = new ArrayList<>();
        // loop through movie list searching for substring matching
        for (String movie : movies) {
            if (movie.toLowerCase().contains(query.toLowerCase())) {
                result.add(movie);
            }
        }
        return result;
    }
}
