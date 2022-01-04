package com.mastery.task.repository;

import com.mastery.task.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDAO extends JpaRepository<Department, Integer> {

    @Query(value = "select c FROM Department c JOIN c.employees u WHERE u.id = :id")
    Department findDepartmentByEmployeeId(Integer id);
}
