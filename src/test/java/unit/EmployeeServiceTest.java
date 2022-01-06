package unit;

import com.mastery.task.application.Application;
import com.mastery.task.dto.EmployeeDTO;
import com.mastery.task.exception.InvalidEmployeeDataException;
import com.mastery.task.exception.NoSuchDepartmentException;
import com.mastery.task.exception.NoSuchGenderException;
import com.mastery.task.exception.NoSuchRecordException;
import com.mastery.task.model.Employee;
import com.mastery.task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@Transactional
@Slf4j
public class EmployeeServiceTest {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeServiceTest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    void shouldCreateEmployee() {
        assertNotNull(employeeService);
        EmployeeDTO expectedEmployee = new EmployeeDTO(null, "Valeriy", "Liashuk", 1,"Junior Java Developer", Date.valueOf(LocalDate.now()), 1);
        Employee savedEmployee = employeeService.create(expectedEmployee);
        assertNotNull(savedEmployee);
        expectedEmployee.setId(savedEmployee.getId());
        EmployeeDTO actualEmployee = employeeService.getById(expectedEmployee.getId());
        assertEquals(expectedEmployee, actualEmployee);
        log.info("Execute test EmployeeServiceTest shouldCreateEmployee() expectedEmployee {}, actualEmployee {}", expectedEmployee, actualEmployee);
    }

    @Test
    void shouldUpdateEmployee() {
        assertNotNull(employeeService);
        EmployeeDTO expectedEmployee = new EmployeeDTO(null, "Valeriy", "Liashuk", 1,"Junior Java Developer", Date.valueOf(LocalDate.now()), 1);
        Employee savedEmployee = employeeService.create(expectedEmployee);
        assertNotNull(savedEmployee);
        expectedEmployee.setId(savedEmployee.getId());
        EmployeeDTO actualEmployee = new EmployeeDTO(expectedEmployee.getId(), "Valeriy", "Godel", 1,"Junior Java Developer", Date.valueOf(LocalDate.now()), 1);
        expectedEmployee.setLastName(actualEmployee.getLastName());
        assertEquals(expectedEmployee, actualEmployee);
        log.info("Execute test EmployeeServiceTest shouldUpdateEmployee() expectedEmployee {}, actualEmployee {}", expectedEmployee, actualEmployee);
    }

    @Test
    void shouldDeleteEmployeeById() {
        assertNotNull(employeeService);
        EmployeeDTO expectedEmployee = employeeService.getById(10);
        employeeService.delete(expectedEmployee.getId());
        assertThrows(NoSuchRecordException.class, () -> employeeService.getById(expectedEmployee.getId()));
        log.info("Execute test EmployeeServiceTest shouldDeleteEmployeeById() with id {}", expectedEmployee.getId());
    }

    @Test
    void shouldFindEmployeeById() {
        assertNotNull(employeeService);
        EmployeeDTO expectedEmployee = new EmployeeDTO(null, "Valeriy", "Liashuk", 1,"Junior Java Developer", Date.valueOf(LocalDate.now()), 1);
        Employee savedEmployee = employeeService.create(expectedEmployee);
        assertNotNull(savedEmployee);
        expectedEmployee.setId(savedEmployee.getId());
        EmployeeDTO actualEmployee = employeeService.getById(10);
        assertNotNull(actualEmployee);
        log.info("Execute test EmployeeServiceTest shouldFindEmployeeById() with id {}", expectedEmployee.getId());
    }

    @Test
    void shouldFindAllEmployees() {
        assertNotNull(employeeService);
        List<EmployeeDTO> employeeList = employeeService.getAll();
        assertNotNull(employeeList);
        log.info("Execute test EmployeeServiceTest shouldFindAllEmployees()");
    }

    @Test
    void shouldThrowNoSuchDepartmentException() {
        assertNotNull(employeeService);
        EmployeeDTO expectedEmployee = new EmployeeDTO(null, "Valeriy", "Liashuk", 6,"Junior Java Developer", Date.valueOf(LocalDate.now()), 1);
        assertThrows(NoSuchDepartmentException.class, () -> employeeService.create(expectedEmployee));
        log.info("Execute test EmployeeServiceTest shouldThrowNoSuchDepartmentException()");
    }

    @Test
    void shouldThrowNoSuchGenderException() {
        assertNotNull(employeeService);
        EmployeeDTO expectedEmployee = new EmployeeDTO(null, "Valeriy", "Liashuk", 1,"Junior Java Developer", Date.valueOf(LocalDate.now()), 3);
        assertThrows(NoSuchGenderException.class, () -> employeeService.create(expectedEmployee));
        log.info("Execute test EmployeeServiceTest shouldThrowNoSuchGenderException()");
    }

    @Test
    void shouldThrowNoSuchRecordException() {
        assertNotNull(employeeService);
        assertThrows(NoSuchRecordException.class, () -> employeeService.getById(1));
        log.info("Execute test EmployeeServiceTest shouldThrowNoSuchRecordException()");
    }

    @Test
    void shouldThrowInvalidEmployeeDataException() {
        assertNotNull(employeeService);
        EmployeeDTO expectedEmployee = new EmployeeDTO(null, "", "Liashuk", 1,"Junior Java Developer", Date.valueOf(LocalDate.now()), 1);
        assertThrows(InvalidEmployeeDataException.class, () -> employeeService.create(expectedEmployee));
        log.info("Execute test EmployeeServiceTest shouldThrowInvalidEmployeeDataException()");
    }
}
