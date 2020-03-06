package com.rest.movie;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieDAO {

    @PersistenceContext
    private EntityManager entMan;

    public Movie create(Movie movie) {
        entMan.persist(movie);
        entMan.flush();
        return movie;
    }

    public Movie getById(int id) {
        return entMan.find(Movie.class, id);
    }

    public List<Movie> getAll() {
        List<Movie> movies = entMan.createQuery(
                "SELECT n from Movie n", Movie.class).getResultList();
        return movies;
    }
    public void updateByMovie(Movie movie) {
        entMan.merge(movie);
    }
    public void deleteById(int id) {
        Movie movie = getById(id);
        entMan.remove(movie);
    }

}
