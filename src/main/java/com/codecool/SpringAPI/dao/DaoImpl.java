package com.codecool.SpringAPI.dao;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;

public class DaoImpl<T> implements Dao<T> {

    private Class<T> aClass;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public DaoImpl(Class<T> aClass) {
        this.aClass = aClass;
    }

    @Override
    public List<T> getAll() {
        initEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.aClass);
        Root<T> object = criteriaQuery.from(this.aClass);
        criteriaQuery.select(object);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        List<T> resultList = query.getResultList();
        closeManagers();
        return resultList;
    }

    @Override
    public T get(long objectId) {
        initEntityManager();
        T receivedObject = entityManager.find(this.aClass, objectId);
        closeManagers();
        return receivedObject;
    }

    @Override
    public void add(T object) {
        initEntityManager();
        try {
            Field objectId = this.aClass.getDeclaredField("id");
            objectId.setAccessible(true);
            if (entityManager.find(this.aClass, objectId.get(object)) == null){
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.merge(object);
                transaction.commit();
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        closeManagers();
    }

    @Override
    public void update(T object) {
        initEntityManager();
        try {
            Field objectId = this.aClass.getDeclaredField("id");
            objectId.setAccessible(true);
            if (entityManager.find(this.aClass, objectId.get(object)) != null){
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.merge(object);
                transaction.commit();
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        closeManagers();
    }

    @Override
    public void delete(long objectId) {
        initEntityManager();
        T objectToDelete = entityManager.find(this.aClass, objectId);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(objectToDelete);
        transaction.commit();
        closeManagers();
    }

    private void initEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("restPU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    private void closeManagers() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void resetDataBase() {
        initEntityManager();
        closeManagers();
    }
}
