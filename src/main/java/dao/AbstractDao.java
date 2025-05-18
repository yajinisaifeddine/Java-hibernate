package dao;

import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {
    Class<T> entityClass;

    protected AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(entityClass, id));
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        }
    }

    @Override
    public Optional<T> findBy(String filed, String value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from " + entityClass.getSimpleName() + " where " + filed + " = :value";
            return session.createQuery(hql, entityClass).setParameter("value", value).uniqueResultOptional();
        }
    }

    @Override
    public List<T> findMultipleBy(String filed, String value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from " + entityClass.getSimpleName() + " where " + filed + " = :value";
            return session.createQuery(hql, entityClass).setParameter("value", value).getResultList();
        }
    }

    @Override
    public Optional<T> save(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> update(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> delete(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Optional<T> entity = findById(id);
            if (entity.isEmpty())return Optional.empty();
            session.save(entity.get());
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
