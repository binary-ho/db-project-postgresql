package com.gov.appDemo.repository;

import com.gov.appDemo.domain.Student;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
@RequiredArgsConstructor
public class JpaStudentRepository {

    private final EntityManager entityManager;

    public boolean save(Student student) {
        if (hasUser(student.getEmail())) return false;
        entityManager.persist(student);
        return true;
    }

    public void saveAll(List<Student> students) {
        students.forEach(this::save);
    }

    public List<Student> findByName(String name) throws Exception {
        List<Student> students = entityManager
            .createQuery("select s from Student as s where s.name = :name", Student.class)
            .setParameter("name", name)
            .getResultList();
        return students;
    }

    public int countStudentByDegree(String degree) {
        List<Student> students = entityManager
            .createQuery("select s from Student as s where s.degree = :degree", Student.class)
            .setParameter("degree", degree)
            .getResultList();
        return students.size();
    }

    public boolean hasUser(String email) {
        if (email == null) return false;
        List<Student> students = entityManager
            .createQuery("select s from Student as s where s.email = :email", Student.class)
            .setParameter("email", email)
            .getResultList();
        return !students.isEmpty();
    }
}
