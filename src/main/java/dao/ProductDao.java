package dao;

import model.Product;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class ProductDao implements Dao<Product>{
    Class<Product> entityClass = Product.class;




    public Optional<Product> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(entityClass, id));
        }
    }

    public List<Product> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        }
    }


    public Optional<Product> findBy(String filed, String value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from " + entityClass.getSimpleName() + " where " + filed + " = :value";
            return session.createQuery(hql, entityClass).setParameter("value", value).uniqueResultOptional();
        }
    }


    public List<Product> findMultipleBy(String filed, String value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from " + entityClass.getSimpleName() + " where " + filed + " = :value";
            return session.createQuery(hql, entityClass).setParameter("value", value).getResultList();
        }
    }


    public Optional<Product> save(Product entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public Optional<Product> update(Product entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public Optional<Product> delete(Product entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public Optional<Product> delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Optional<Product> entity = findById(id);
            if (entity.isEmpty()) return Optional.empty();
            session.save(entity.get());
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
