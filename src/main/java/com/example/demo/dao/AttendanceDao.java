package com.example.demo.dao;

import com.example.demo.model.Attendance;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AttendanceDao {
    @PersistenceContext
    private EntityManager em;

    public void insert(Attendance attendances){
        em.persist(attendances);
    }

    @SuppressWarnings("unchecked")
    public List<Attendance> getAttendances(String employeeId){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM hris.t_attendances attd ")
                .append("WHERE attd.employee_id = :employeeId ");

        List<Attendance> list = em.createNativeQuery(sb.toString(),Attendance.class)
                .setParameter("employeeId",employeeId)
                .getResultList();

        return list;
    }
}
