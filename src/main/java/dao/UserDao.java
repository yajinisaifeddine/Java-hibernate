package dao;

import model.User;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    Class<User> entityClass = User.class;




    public Optional<User> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(entityClass, id));
        }
    }

    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        }
    }


    public Optional<User> findBy(String filed, String value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from " + entityClass.getSimpleName() + " where " + filed + " = :value";
            return session.createQuery(hql, entityClass).setParameter("value", value).uniqueResultOptional();
        }
    }


    public List<User> findMultipleBy(String filed, String value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from " + entityClass.getSimpleName() + " where " + filed + " = :value";
            return session.createQuery(hql, entityClass).setParameter("value", value).getResultList();
        }
    }


    public Optional<User> save(User entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public Optional<User> update(User entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public Optional<User> delete(User entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public Optional<User> delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Optional<User> entity = findById(id);
            if (entity.isEmpty()) return Optional.empty();
            session.save(entity.get());
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
