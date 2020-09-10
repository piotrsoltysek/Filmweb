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

    @Override
    public List<Movie> getMoviesByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Movie> query = session.createQuery("FROM pl.camp.it.filmweb.model.Movie WHERE title LIKE :title");
        query.setParameter("title", "%" + pattern + "%");

        List<Movie> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Director> getDirectorsByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Director> query = session.createQuery("FROM pl.camp.it.filmweb.model.Director WHERE name LIKE :name OR surname LIKE :surname");
        query.setParameter("name", "%" + pattern + "%");
        query.setParameter("surname", "%" + pattern + "%");

        List<Director> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Movie> getMoviesByProductionYear(String productionYear) {
        Session session = this.sessionFactory.openSession();
        Query<Movie> query = session.createQuery("FROM pl.camp.it.filmweb.model.Movie WHERE productionYear LIKE :productionYear");
        query.setParameter("productionYear", "%" + productionYear + "%");

        List<Movie> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Movie> getMoviesByDirectorId(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Movie> query = session.createQuery("FROM pl.camp.it.filmweb.model.Movie WHERE director_id = :director");
        query.setParameter("director", id);

        List<Movie> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Movie> getMovieByFilter(String pattern, Movie.Genre genre, String productionYear) {// TODO: 2020-09-09 Jak to zrobić??
        Session session = this.sessionFactory.openSession();
        Query<Movie> query = session.createQuery("FROM pl.camp.it.filmweb.model.Movie WHERE title LIKE :title AND genre = :genre AND productionYear = :productionYear");
        if (pattern != null) {
            query.setParameter("title", "%" + pattern + "%");
        } else {
            query.setParameter("title", "*");
        }

        if (genre != null) {
            query.setParameter("genre", genre);
        } else {
            query.setParameter("genre", "*");
        }

        if (productionYear != null) {
            query.setParameter("productionYear", productionYear);
        } else {
            query.setParameter("productionYear", "*");
        }

        List<Movie> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Movie> getMoviesByUserId(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Movie> query = session.createQuery("FROM pl.camp.it.filmweb.model.Movie WHERE user_id = :user");
        query.setParameter("user", id);

        List<Movie> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Movie getMovieById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Movie> query = session.createQuery("FROM pl.camp.it.filmweb.model.Movie WHERE id = :id");
        query.setParameter("id", id);

        Movie result = query.getSingleResult();
        session.close();
        return result;
    }
}