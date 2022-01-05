package com.mastery.task.service.impl;

import com.mastery.task.dto.EmployeeDTO;
import com.mastery.task.dto.converter.EmployeeConverter;
import com.mastery.task.exception.NoSuchDepartmentException;
import com.mastery.task.exception.NoSuchGenderException;
import com.mastery.task.exception.NoSuchRecordException;
import com.mastery.task.model.Department;
import com.mastery.task.model.Employee;
import com.mastery.task.model.Gender;
import com.mastery.task.repository.DepartmentDAO;
import com.mastery.task.repository.EmployeeDAO;
import com.mastery.task.repository.GenderDAO;
import com.mastery.task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final GenderDAO genderDAO;
    private final EmployeeDAO employeeDAO;
    private final DepartmentDAO departmentDAO;
    private final EmployeeConverter converter;

    @Autowired
    public EmployeeServiceImpl(GenderDAO genderDAO, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO, EmployeeConverter converter) {
        this.genderDAO = genderDAO;
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
        this.converter = converter;
    }

    @Override
    public Employee create(EmployeeDTO employeeDTO) {
        Employee employee = converter.toEmployee(employeeDTO);

        Department department = departmentDAO.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new NoSuchDepartmentException
                        (String.format("Department with id=%s not found", employeeDTO.getDepartmentId()))
                );
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        employee.setDepartments(departmentList);

        Gender gender = genderDAO.findById(employeeDTO.getGenderId())
                .orElseThrow(() -> new NoSuchGenderException
                        (String.format("Gender with id=%s not found", employeeDTO.getGenderId()))
                );
        List<Gender> genderList = new ArrayList<>();
        genderList.add(gender);
        employee.setGenders(genderList);

        log.info("IN EmployeeServiceImpl create() employee {}", employee);
        return employeeDAO.save(employee);
    }

    @Override
    public Employee update(Employee employee, Integer id) {
        log.info("IN EmployeeServiceImpl update() employee with id {}, {}", employee, id);
        return employeeDAO.save(converter.updateEmployee(employee, id));
    }

    @Override
    public EmployeeDTO getById(Integer id) {
        Employee employee = employeeDAO.findById(id)
                .orElseThrow(() -> new NoSuchRecordException(String.format("Employee with id=%s not found", id))
                );
        log.info("IN EmployeeServiceImpl getById() employee with id {}", id);
        return converter.toDTO(employee);
    }

    @Override
    public Integer delete(Integer id) {
        Employee employee = employeeDAO.findById(id)
                .orElseThrow(() -> new NoSuchRecordException(String.format("Employee with id=%s not found", id)));
        employeeDAO.delete(employee);
        log.info("IN EmployeeServiceImpl delete() employee with id {}", id);
        return id;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        log.info("IN EmployeeServiceImpl getAll() employees List");
        return employeeDAO.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }
}
