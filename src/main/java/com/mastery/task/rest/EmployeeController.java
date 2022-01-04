package com.mastery.task.rest;

import com.mastery.task.dto.EmployeeDTO;
import com.mastery.task.model.Employee;
import com.mastery.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/employees/",
produces = MediaType.APPLICATION_JSON_VALUE,
consumes = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.update(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }
}
