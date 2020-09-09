package pl.camp.it.filmweb.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.filmweb.dao.IReviewDAO;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.Review;
import java.util.List;

@Repository
public class ReviewDAOImpl implements IReviewDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addReview(Review review) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(review);
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
    public List<Review> getAllReviews() {
        Session session = this.sessionFactory.openSession();
        Query<Review> query = session.createQuery("FROM pl.camp.it.filmweb.model.Review");

        List<Review> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Review> getReviewsByUserId(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Review> query = session.createQuery("FROM pl.camp.it.filmweb.model.Review WHERE user_id = :user");
        query.setParameter("user", id);

        List<Review> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Review> getReviewsByMovieId(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Review> query = session.createQuery("FROM pl.camp.it.filmweb.model.Review WHERE movie_id = :movie");
        query.setParameter("movie", id);

        List<Review> result = query.getResultList();
        session.close();
        return result;
    }
}
