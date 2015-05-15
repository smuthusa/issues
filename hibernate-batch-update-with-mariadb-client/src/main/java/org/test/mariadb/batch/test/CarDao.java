package org.test.mariadb.batch.test;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collection;

public class CarDao {
    private SessionFactory sessionFactory;
    private TransactionTemplate transactionTemplate;

    public CarDao(SessionFactory sessionFactory, TransactionTemplate transactionTemplate) {
        this.sessionFactory = sessionFactory;
        this.transactionTemplate = transactionTemplate;
    }
    public void deleteAll() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                sessionFactory.getCurrentSession().createQuery("delete from Car").executeUpdate();
            }
        });
    }
    public Collection<Car> save(final Collection<Car> cars){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Session session = sessionFactory.getCurrentSession();
                for(Car car:cars){
                    session.save(car);
                }
            }
        });
        return cars;
    }
}
