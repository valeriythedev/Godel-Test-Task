package com.mastery.task.dto.converter;

import com.mastery.task.dto.EmployeeDTO;
import com.mastery.task.exception.InvalidEmployeeDataException;
import com.mastery.task.exception.NoSuchRecordException;
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
        if(!(employeeDTO.getFirstName().equals(""))) {
            employee.setFirstName(employeeDTO.getFirstName());
        } else {
            throw new InvalidEmployeeDataException("Employee firstName cannot be null.");
        }
        if(!(employeeDTO.getLastName().equals(""))) {
            employee.setLastName(employeeDTO.getLastName());
        } else {
            throw new InvalidEmployeeDataException("Employee lastName cannot be null.");
        }
        if(!(employeeDTO.getJobTitle().equals(""))) {
            employee.setJobTitle(employeeDTO.getJobTitle());
        } else {
            throw new InvalidEmployeeDataException("Employee jobTitle cannot be null.");
        }
        if(!(employeeDTO.getDateOfBirth() == null)) {
            employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        } else {
            throw new InvalidEmployeeDataException("Employee dateOfBirth cannot be null.");
        }
        return employee;
    }

    public Employee updateEmployee(Employee employee, Integer id) {
        Employee updatedEmployee = employeeDAO.findById(id)
                .orElseThrow(() -> new NoSuchRecordException
                        (String.format("Employee with id=%s not found", id))
                );
        if(!(employee.getFirstName() == null)) {
            updatedEmployee.setFirstName(employee.getFirstName());
        }
        if(!(employee.getLastName() == null)) {
            updatedEmployee.setLastName(employee.getLastName());
        }
        if(!(employee.getJobTitle() == null)) {
            updatedEmployee.setJobTitle(employee.getJobTitle());
        }
        if(!(employee.getDateOfBirth() == null)) {
            updatedEmployee.setDateOfBirth(employee.getDateOfBirth());
        }
        return updatedEmployee;
    }
}
