package com.mastery.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(departmentId, that.departmentId) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(genderId, that.genderId);
    }
}
