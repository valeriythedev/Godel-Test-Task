package com.mastery.task.rest;

import com.mastery.task.dto.EmployeeDTO;
import com.mastery.task.model.Employee;
import com.mastery.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees/",
produces = MediaType.APPLICATION_JSON_VALUE,
consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee Controller"
        , description = "Interaction with employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @Operation(summary = "Creating employee")
    public EmployeeDTO create(@RequestBody @Parameter(description = "Employee body") EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return employeeDTO;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @Operation(summary = "Updating employee")
    public Employee update(@RequestBody Employee employee,
                           @PathVariable("id") @Parameter(description = "Id of updating employee") Integer id) {
        return employeeService.update(employee, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting employee")
    public Integer delete(@PathVariable("id") @Parameter(description = "Id of deleting employee") Integer id) {
        return employeeService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Getting employee")
    public EmployeeDTO getById(@PathVariable("id") @Parameter(description = "Id of getting employee") Integer id) {
        return employeeService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Get all employees")
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll();
    }
}
