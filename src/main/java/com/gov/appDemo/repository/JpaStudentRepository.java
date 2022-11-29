package com.gov.appDemo.repository;

import com.gov.appDemo.domain.Student;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
@Transactional
public class JpaStudentRepository {

    private final EntityManager entityManager;

    public void save(Student student) {
        entityManager.persist(student);
    }

    public void saveAll(List<Student> students) {
        students.forEach(this::save);
    }

    public List<Student> findStudentByName(String name) {
        return entityManager
            .createQuery("select s from Student as s where s.name = :name", Student.class)
            .setParameter("name", name)
            .getResultList();
    }

    public int countStudentByDegree(String degree) {
        return entityManager
            .createQuery("select s from Student as s where s.degree = :degree", Student.class)
            .setParameter("degree", degree)
            .getResultList().size();
    }

    public boolean hasStudent(Student student) {
        List<Student> students = entityManager
            .createQuery(
                "select s from Student as s " + " where s.name = :name or s.email = :email",
                Student.class)
            .setParameter("name", student.getName())
            .setParameter("email", student.getEmail())
            .getResultList();
        return !students.isEmpty();
    }
}
