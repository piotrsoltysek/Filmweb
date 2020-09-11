package pl.camp.it.filmweb.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.filmweb.dao.IRatingDAO;
import pl.camp.it.filmweb.model.Rating;
import java.util.List;


@Repository
public class RatingDAOImpl implements IRatingDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void addRating(Rating rating) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(rating);
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
    public List<Rating> getRatingByMovieId(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Rating> query = session.createQuery("FROM pl.camp.it.filmweb.model.Rating WHERE movie_id = :movie");
        query.setParameter("movie", id);

        List<Rating> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Rating> getUserRate(int userId, int movieId) {
        Session session = this.sessionFactory.openSession();
        System.out.println("query");
        Query<Rating> query = session.createQuery("FROM pl.camp.it.filmweb.model.Rating WHERE user_id = :userId AND movie_id = :movieId");
        query.setParameter("userId", userId);
        query.setParameter("movieId", movieId);

        List<Rating> result = query.getResultList();
        session.close();
        return result;
    }
}
