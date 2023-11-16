package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDao {
    @PersistenceContext
    private EntityManager em;

    public void insert(Employee employee){
        em.persist(employee);
    }

    @SuppressWarnings("unchecked")
    public Employee getById(String employeeId){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM hris.t_employees emp ")
                .append("WHERE emp.id = :employeeId ");

        List<Employee> list = em.createNativeQuery(sb.toString())
                .setParameter("employeeId", employeeId)
                .getResultList();

        if(list.size() == 0){
            return new Employee();
        }else{
            return list.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getSubordinates(String employeeId){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM hris.t_employees emp ")
                .append("WHERE emp.supervisor_id = :employeeId ");

        List<Employee> list = em.createNativeQuery(sb.toString(),Employee.class)
                .setParameter("employeeId", employeeId)
                .getResultList();

        return list;
    }
}
