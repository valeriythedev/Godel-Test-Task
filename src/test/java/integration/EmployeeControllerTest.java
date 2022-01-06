package integration;

import com.mastery.task.application.Application;
import com.mastery.task.dto.EmployeeDTO;
import com.mastery.task.exception.InvalidEmployeeDataException;
import com.mastery.task.exception.NoSuchDepartmentException;
import com.mastery.task.exception.NoSuchGenderException;
import com.mastery.task.exception.NoSuchRecordException;
import com.mastery.task.exception.controllerhandler.GlobalControllerAdvice;
import com.mastery.task.model.Employee;
import com.mastery.task.rest.EmployeeController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
@Slf4j
public class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldCreateEmployee() throws Exception {
        when(employeeController.create(any(EmployeeDTO.class))).thenReturn(new EmployeeDTO(null, "Valeriy", "Liashuk", 1, "Junior Java Developer", Date.valueOf("2003-02-11"), 1));
        mvc.perform(post("/api/employees/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                         {
                            "firstName": "Valeriy",
                            "lastName": "Liashuk",
                            "departmentId": 1,
                            "jobTitle": "Junior Java Developer",
                            "dateOfBirth": "2003-02-11",
                            "genderId": 1
                         }
                         """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Valeriy"))
                .andExpect(jsonPath("$.lastName").value("Liashuk"))
                .andExpect(jsonPath("$.departmentId").value(1))
                .andExpect(jsonPath("$.jobTitle").value("Junior Java Developer"))
                .andExpect(jsonPath("$.dateOfBirth").value("2003-02-11"))
                .andExpect(jsonPath("$.genderId").value(1));
        log.info("Execute test EmployeeControllerTest shouldCreateEmployee()");
    }

    @Test
    public void shouldUpdateEmployee() throws Exception {
        when(employeeController.update(any(Employee.class), any(Integer.TYPE))).thenReturn(new Employee(null, "Denis", "Beresten", "Middle Java Developer", Date.valueOf("2003-04-16")));
        mvc.perform(patch("/api/employees/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "jobTitle": "Middle Java Developer"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobTitle").value("Middle Java Developer"));
        log.info("Execute test EmployeeControllerTest shouldUpdateEmployee()");
    }

    @Test
    public void shouldGetByIdEmployee() throws Exception {
        when(employeeController.getById(any(Integer.TYPE))).thenReturn(new EmployeeDTO(10, "Denis", "Beresten", 2, "Junior Java Developer", Date.valueOf("2003-04-16"), 1));
        mvc.perform(get("/api/employees/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.firstName").value("Denis"))
                .andExpect(jsonPath("$.lastName").value("Beresten"))
                .andExpect(jsonPath("$.departmentId").value(2))
                .andExpect(jsonPath("$.jobTitle").value("Junior Java Developer"))
                .andExpect(jsonPath("$.dateOfBirth").value("2003-04-16"))
                .andExpect(jsonPath("$.genderId").value(1));
        log.info("Execute test EmployeeControllerTest shouldGetByIdEmployee()");
    }

    @Test
    public void shouldGetAllEmployees() throws Exception {
        when(employeeController.getAll()).thenReturn(List.of(new EmployeeDTO(8, "Polina", "Fadeeva", 1, "Lead English Teacher", Date.valueOf("2004-03-05"), 2),
                                                             new EmployeeDTO(10, "Denis", "Beresten", 2, "Junior Java Developer", Date.valueOf("2003-04-16"), 1)));
        mvc.perform(get("/api/employees/")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Polina"))
                .andExpect(jsonPath("$[1].jobTitle").value("Junior Java Developer"));
        log.info("Execute test EmployeeControllerTest shouldGetAllEmployees()");
    }

    @Test
    public void shouldDeleteEmployeeById() throws Exception {
        when(employeeController.delete(any(Integer.TYPE))).thenReturn(10);
        mvc.perform(delete("/api/employees/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10));
        log.info("Execute test EmployeeControllerTest shouldDeleteEmployeeById()");
    }

    @Test
    public void shouldExistsGlobalControllerAdvice() {
        GlobalControllerAdvice advice = this.webApplicationContext.getBean(GlobalControllerAdvice.class);
        assertNotNull(advice);
        log.info("Execute test EmployeeControllerTest shouldExistsGlobalControllerAdvice()");
    }

    @Test
    public void shouldReturnNotFoundIfRecordNotFound() throws Exception {
        when(employeeController.getById(any(Integer.TYPE))).thenThrow(NoSuchRecordException.class);
        mvc.perform(get("/api/employees/{id}", 0)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"));
        log.info("Execute test EmployeeControllerTest shouldReturnNotFoundIfRecordNotFound");
    }

    @Test
    public void shouldReturnBadRequestIfDepartmentIdIncorrect() throws Exception {
        when(employeeController.create(any(EmployeeDTO.class))).thenThrow(NoSuchDepartmentException.class);
        mvc.perform(post("/api/employees/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "firstName": "Valeriy",
                            "lastName": "Liashuk",
                            "departmentId": 0,
                            "jobTitle": "Junior Java Developer",
                            "dateOfBirth": "2003-02-11",
                            "genderId": 1
                        }
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"));
        log.info("Execute test EmployeeControllerTest shouldReturnBadRequestIfDepartmentIdIncorrect()");
    }

    @Test
    public void shouldReturnBadRequestIfGenderIdIncorrect() throws Exception {
        when(employeeController.create(any(EmployeeDTO.class))).thenThrow(NoSuchGenderException.class);
        mvc.perform(post("/api/employees/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "firstName": "Valeriy",
                            "lastName": "Liashuk",
                            "departmentId": 1,
                            "jobTitle": "Junior Java Developer",
                            "dateOfBirth": "2003-02-11",
                            "genderId": 0
                        }
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"));
        log.info("Execute test EmployeeControllerTest shouldReturnBadRequestIfGenderIdIncorrect()");
    }

    @Test
    public void shouldReturnBadRequestIfEmployeeDataIncorrect() throws Exception {
        when(employeeController.create(any(EmployeeDTO.class))).thenThrow(InvalidEmployeeDataException.class);
        mvc.perform(post("/api/employees/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "firstName": "",
                            "lastName": "Liashuk",
                            "departmentId": 1,
                            "jobTitle": "Junior Java Developer",
                            "dateOfBirth": "2003-02-11",
                            "genderId": 1
                        }
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"));
        log.info("Execute test EmployeeControllerTest shouldReturnBadRequestIfEmployeeDataIncorrect()");
    }
}
