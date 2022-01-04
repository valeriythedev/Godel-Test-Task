package com.mastery.task.repository;

import com.mastery.task.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderDAO extends JpaRepository<Gender, Integer> {

    @Query(value = "select c FROM Gender c JOIN c.employeeList u where u.id= :id")
    Gender findGenderByEmployeeId(Integer id);
}
