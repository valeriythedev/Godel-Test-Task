package com.mastery.task.service;

import com.mastery.task.dto.EmployeeDTO;
import com.mastery.task.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(EmployeeDTO employeeDTO);

    Employee update(Employee employee, Integer id);

    EmployeeDTO getById(Integer id);

    Integer delete(Integer id);

    List<EmployeeDTO> getAll();
}
