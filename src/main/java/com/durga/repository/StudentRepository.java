package com.durga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.durga.beans.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
