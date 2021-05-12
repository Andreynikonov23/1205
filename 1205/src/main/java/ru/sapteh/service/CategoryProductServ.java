package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.CategoryProduct;
import ru.sapteh.models.Warehouse;

import java.util.List;

public class CategoryProductServ implements DAO<CategoryProduct, Integer> {
    private final SessionFactory factory;

    public CategoryProductServ(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(CategoryProduct categoryProduct) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(categoryProduct);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(CategoryProduct categoryProduct) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(categoryProduct);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(CategoryProduct categoryProduct) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(categoryProduct);
            session.getTransaction().commit();
        }
    }

    @Override
    public CategoryProduct read(Integer id) {
        try(Session session = factory.openSession()) {
            return session.get(CategoryProduct.class, id);
        }
    }

    @Override
    public List<CategoryProduct> findByAll() {
        try(Session session = factory.openSession()) {
            Query<CategoryProduct> query = session.createQuery("FROM CategoryProduct");
            return query.list();
        }
    }
}
