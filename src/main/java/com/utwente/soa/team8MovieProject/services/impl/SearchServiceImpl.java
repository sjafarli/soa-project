package com.utwente.soa.team8MovieProject.services.impl;

import com.utwente.soa.team8MovieProject.Movie;
import com.utwente.soa.team8MovieProject.exceptions.InvalidIMdBIdException;
import com.utwente.soa.team8MovieProject.integrations.request.MovieRequest;
import com.utwente.soa.team8MovieProject.omdb.DetailedResult;
import com.utwente.soa.team8MovieProject.omdb.SearchResult;
import com.utwente.soa.team8MovieProject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("voting")
    private String votingQueue;
    private MovieRequest movieRequest = new MovieRequest();

    // omdb API key (1000 requests per day max)
    private static String API_KEY = "dc9932d6";
    // temporary non-persistent local movie storage, later to be converted to database TODO convert to database
    private static List<Movie> MOVIES;

    static {
        MOVIES = new ArrayList<>();
        MOVIES.add(new Movie("tt2294629", "Frozen", "2019", "TODO", true));
        MOVIES.add(new Movie("tt0317219", "Cars", "2020", "TODO", true));
        MOVIES.add(new Movie("tt0468569", "Batman: The dark Knight", "2020", "TODO", true));
        MOVIES.add(new Movie("tt0103776", "Batman Returns", "2020", "TODO", true));
        MOVIES.add(new Movie("tt7286456", "Joker", "2020", "TODO", true));
    }

    /**
     * Searches the local movie database for a specific title
     * retrieves fitting titles from IMdB database if no local titles found
     *
     * @param query search query for movie title
     * @return List of all movies fitting the query
     */
    @Override
    public List<Movie> search(String query) {

        List<Movie> results = new ArrayList<>();

        // TODO change to Java 8 stream notation
        for (Movie movie : MOVIES) {
            String name = movie.getName();
            if (name.toLowerCase().contains(query.toLowerCase())) {
                results.add(movie);
            }
        }

        if (results.size() == 0) {
            results = omdbLookup(query);
        }

        return results;
    }

    /**
     * Search the IMdB database for titles containing the query String, using the omdb API and HTTP REST requests
     *
     * @param query search query for movie titles
     * @return List of all Movies in IMdB database fitting the query
     */
    private List<Movie> omdbLookup(String query) {
        RestTemplate rest = new RestTemplate();
        SearchResult result = rest.getForObject("http://www.omdbapi.com/?s=" + query + "&apikey=" + API_KEY + "&r=XML&t=movie",
                SearchResult.class);

        return result.getResults().stream().map(item -> new Movie(item.getImdbID(), item.getTitle(), "" + item.getYear(), "TODO", false)).collect(Collectors.toList());
    }

    /**
     * Retrieves information about a single local movie by IMdB ID
     *
     * @param imdb_id IMdB ID of the movie
     * @return Movie object containing information
     */
    @Override
    public Movie getMovie(String imdb_id) throws InvalidIMdBIdException {
        for (Movie movie : MOVIES) {
            if (movie.getImdb_id().equals(imdb_id)) {
                return movie;
            }
        }
        throw new InvalidIMdBIdException(imdb_id);
    }

    /**
     * Returns a list of all Movies currently part of local storage.
     *
     * @return List of all movies in local storage
     */
    @Override
    public List<Movie> getMovies() {
        return MOVIES;
    }

    /**
     * removes a movie from local storage
     *
     * @param imdb_id IMdB movie id of the movie
     * @return information about the removed Movie
     * @throws InvalidIMdBIdException if no movie with such an id exists in local storage
     */
    @Override
    public Movie removeMovie(String imdb_id) throws InvalidIMdBIdException {
        Movie movie = getMovie(imdb_id);
        MOVIES.remove(movie);
        movie.setAvailable(false);
        return movie;
    }

    /**
     * Adds a new movie to the local storage, after retrieving information from omdb API
     *
     * @param imdb_id IMdB id of the movie that should be added
     * @return Movie object that has been added to local storage
     */
    @Override
    public Movie addMovie(String imdb_id) throws InvalidIMdBIdException {
        Movie movie = omdbIdLookup(imdb_id);
        MOVIES.add(movie);
        return movie;
    }

    /**
     * Retrieves detailed information about a movie from the omdb API
     *
     * @param imdb_id IMdB ID of the movie
     * @return Movie object containing information
     */
    private Movie omdbIdLookup(String imdb_id) throws InvalidIMdBIdException {
        RestTemplate rest = new RestTemplate();
        try {
            DetailedResult result = rest.getForObject("http://www.omdbapi.com/?i=" + imdb_id + "&apikey=" + API_KEY + "&r=XML", DetailedResult.class);
            return new Movie(result.details().getImdbID(), result.details().getTitle(), "" + result.details().getYear(), result.details().getPlot(), true);
        } catch (NullPointerException e) {
            throw new InvalidIMdBIdException(imdb_id);
        }
    }

    @Override
    public ResponseEntity<String> submitSuggestion(String imdb_id) {
        Movie movie = omdbIdLookup(imdb_id);
        movieRequest.setMovieIdmbID(imdb_id);
        System.out.println(votingQueue);
        jmsTemplate.convertAndSend(votingQueue, movieRequest);
        return  ResponseEntity.ok("Your movie requested has been added to the voting list!");

    }
}
