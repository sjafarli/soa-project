package com.utwente.soa.voting.services.impl;

import com.utwente.soa.voting.dto.MovieRequestDTO;
import com.utwente.soa.voting.integrations.request.MovieXmlRequest;
import com.utwente.soa.voting.services.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class VotingServiceImpl implements VotingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${list.of.movies}")
    private List <String> movies;

    @Override
    @JmsListener(destination = "${queue.voting}")
    public void addMovie(MovieXmlRequest movie) {
        //TODO make sure movies cant be added twice, perhaps UNIQUE DB field. also 2nd time should count as a vote

        //add to voting database if there is a msg in queue
       // movies.add(movie.getMovieIdmbID());
        //unique will be the field
         jdbcTemplate.update("insert into voting (name, imdb_id, votes) " + "values(?,  ?, ?)",
                new Object[] {
                        movie.getName(), movie.getMovieIdmbID(), 0
                });
    }

    //will change it to a Repository call
    @Override
    public List<MovieRequestDTO> showVotingList() {
        String sql = "SELECT * FROM voting";
        return jdbcTemplate.query(sql, new RequestRowMapper());
    }

    @Override
    public MovieRequestDTO voteMovie(String imdbID) {
        //TODO still throws errors, e.g if movie not in voting list or somehow twice?
        MovieRequestDTO request = new MovieRequestDTO();
        try {
             request = findMovieById(imdbID);
        }catch (HttpServerErrorException.InternalServerError e){
            e.printStackTrace();
        }
        int current_votes =request.getVotes();
        int newVotes =current_votes+1;
        jdbcTemplate.update("update voting " + " set votes = ? " + " where imdb_id = ?",
                new Object[] {
                        newVotes, request.getImdb_id()
                });
        return request;
    }

    private MovieRequestDTO findMovieById(String imdbID) {

        return jdbcTemplate.queryForObject("select * from voting where imdb_id=?", new Object[] {imdbID},
                new BeanPropertyRowMapper<>(MovieRequestDTO.class));
    }
}

class RequestRowMapper implements RowMapper< MovieRequestDTO > {
    @Override
    public MovieRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        MovieRequestDTO movie = new MovieRequestDTO();
        movie.setImdb_id(rs.getString("imdb_id"));
        movie.setName(rs.getString("name"));
        movie.setVotes(rs.getInt("votes"));
        return movie;
    }
}
