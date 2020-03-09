package com.rest.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieDAO movieDAO = new MovieDAO();

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getMovie() {
        List<Movie> movies = movieDAO.getAll();
        return movies;
    }


    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable int id) {
        Movie reMovie = null;
        reMovie = movieDAO.getById(id);
        if(reMovie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID" + id + " was not found.");
        }
        return reMovie;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public Movie restMovie(@RequestBody Movie movie) {

        movie = movieDAO.create(movie);
        return movie;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.PUT)
    public Movie updateMovie(@RequestBody Movie movie) {
        Movie testMovie = movieDAO.getById((int) movie.getId());
        if (testMovie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID "+ movie.getId() + " was not found.");
        }
        movieDAO.updateByMovie(movie);
        if(movie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID "+ movie.getId() + " was not found.");
        }
        return movie;
    }
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    public Movie updateItem(@PathVariable int id, @RequestBody String str) {
        Movie movie = movieDAO.getById(id);
        if(movie == null) {
            assert false;
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID "+ movie.getId() + " was not found.");
        }
        movie.setTitle(str);
        movieDAO.updateByMovie(movie);
        return movie;
    }
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public String deleteMovie(@PathVariable int id) {
        getMovieById(id);
        movieDAO.deleteById(id);
        return "ID "+id+ " deleted";
    }

}
