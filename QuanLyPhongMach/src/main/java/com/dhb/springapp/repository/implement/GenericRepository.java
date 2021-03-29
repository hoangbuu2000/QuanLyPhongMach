package com.dhb.springapp.repository.implement;

import com.dhb.springapp.repository.IGenericRepository;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class GenericRepository<T extends Serializable> implements IGenericRepository<T> {
    @Autowired
    @Qualifier("getSessionFactory")
    private LocalSessionFactoryBean sessionFactory;

    protected Session currentSession() {
        return this.sessionFactory.getObject().getCurrentSession();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public T insert(T t) {
        currentSession().save(t);
        return t;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public T getById(Class<T> type, Object id) {
        try {
            T t = currentSession().get(type, (Serializable) id);
            return t;
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public T update(T t) {
        currentSession().saveOrUpdate(t);
        return t;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delete(T t) {
        try {
            currentSession().delete(t);
            return 1;
        }
        catch (HibernateError e) {
            currentSession().getTransaction().rollback();
            return 0;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getAll(Class<T> type) {
        Query q = currentSession().createQuery("From " + type.getName());
        return q.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getAllOrderBy(Class<T> type, String field, Enum<Order> order) {
        Query q = currentSession().createQuery("From " + type.getName() + " order by " + field + " " + order.name());
        return q.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getTop(Class<T> type, int limit) {
        Query q = currentSession().createQuery("From " + type.getName()).setMaxResults(limit);
        return q.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getTopOrderBy(Class<T> type, int limit, String field, Enum<Order> order) {
        Query q = currentSession().createQuery("From " + type.getName() + " order by " + field + " "
                + order.name()).setMaxResults(limit);
        return q.getResultList();
    }
}

