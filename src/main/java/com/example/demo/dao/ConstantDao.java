package com.example.demo.dao;

import com.example.demo.model.Constants;
import com.example.demo.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ConstantDao {
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public Constants getById(String constId){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM hris.t_constants cnst ")
                .append("WHERE cnst.id = :constId ");

        List<Constants> list = em.createQuery(sb.toString())
                .setParameter("constId", constId)
                .getResultList();

        if(list.size() == 0){
            return new Constants();
        }else{
            return list.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public Constants getByKey(String key){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM hris.t_constants cnst ")
                .append("WHERE cnst.\"key\" = :key ");

        List<Constants> list = em.createNativeQuery(sb.toString(),Constants.class)
                .setParameter("key", key)
                .getResultList();

        if(list.size() == 0){
            return new Constants();
        }else{
            return list.get(0);
        }
    }
}
