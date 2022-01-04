package com.mastery.task.dto.converter;

import com.mastery.task.dto.EmployeeDTO;
import com.mastery.task.model.Employee;
import com.mastery.task.repository.DepartmentDAO;
import com.mastery.task.repository.EmployeeDAO;
import com.mastery.task.repository.GenderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    private final EmployeeDAO employeeDAO;
    private final DepartmentDAO departmentDAO;
    private final GenderDAO genderDAO;

    @Autowired
    public EmployeeConverter(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO, GenderDAO genderDAO) {
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
        this.genderDAO = genderDAO;
    }

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setDepartmentId(departmentDAO.findDepartmentByEmployeeId(employee.getId()).getId());
        employeeDTO.setJobTitle(employee.getJobTitle());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setGenderId(genderDAO.findGenderByEmployeeId(employee.getId()).getId());
        return employeeDTO;
    }

    public Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setJobTitle(employeeDTO.getJobTitle());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        return employee;
    }
}
