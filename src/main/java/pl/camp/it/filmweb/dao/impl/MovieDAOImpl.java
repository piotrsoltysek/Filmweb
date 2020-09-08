package pl.camp.it.filmweb.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.filmweb.dao.IMovieDAO;
import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import javax.persistence.NoResultException;
import java.util.List;


@Repository
public class MovieDAOImpl implements IMovieDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<Movie> getAllMovies() {
        Session session = this.sessionFactory.openSession();
        Query<Movie> query = session.createQuery("FROM pl.camp.it.filmweb.model.Movie");

        List<Movie> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void addMovie(Movie movie) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(movie);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Director> getAllDirectors() {
        Session session = this.sessionFactory.openSession();
        Query<Director> query = session.createQuery("FROM pl.camp.it.filmweb.model.Director");

        List<Director> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Director getDirectorByNameAndSurname(String name, String surname) {
        try {
            Session session = sessionFactory.openSession();
            Query<Director> query = session.createQuery("FROM pl.camp.it.filmweb.model.Director WHERE name = :name and surname = :surname");
            query.setParameter("name", name);
            query.setParameter("surname", surname);

            Director director = query.getSingleResult();
            session.close();
            return director;
        } catch (
                NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Movie> getMoviesByGenre(Movie.Genre genre) {
        Session session = this.sessionFactory.openSession();
        Query<Movie> query = session.createQuery("FROM pl.camp.it.filmweb.model.Movie WHERE genre = :genre");
        query.setParameter("genre", genre);

        List<Movie> result = query.getResultList();
        session.close();
        return result;
    }
}