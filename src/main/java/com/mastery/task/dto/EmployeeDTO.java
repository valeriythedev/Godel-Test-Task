package com.mastery.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer departmentId;
    private String jobTitle;
    private Date dateOfBirth;
    private Integer genderId;
}
