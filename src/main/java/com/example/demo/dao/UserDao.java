package com.example.demo.dao;

import com.example.demo.model.Employee;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public void add(User user){
        em.persist(user);
    }

    @SuppressWarnings("unchecked")
    public User getByEmail(String email){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM hris.t_users ")
                .append("WHERE email = :email ");

        List<User> list = em.createNativeQuery(sb.toString(),User.class)
                .setParameter("email", email)
                .getResultList();

        if(list.size() == 0){
            return new User();
        }else{
            return list.get(0);
        }}

    @SuppressWarnings("unchecked")
    public User getById(String id){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM hris.t_users usr ")
                .append("WHERE usr.id = :id ");

        List<User> list = em.createNativeQuery(sb.toString(),User.class)
                .setParameter("id", id)
                .getResultList();

        if(list.size() == 0){
            return new User();
        }else{
            return list.get(0);
        }}
}
