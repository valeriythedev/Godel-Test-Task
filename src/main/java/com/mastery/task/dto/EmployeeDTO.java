package com.mastery.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity of EmployeeDTO")
public class EmployeeDTO {

    private Integer id;

    @Schema(description = "Alex", example = "Alex")
    private String firstName;

    @Schema(description = "Johnson", example = "Johnson")
    private String lastName;

    @Schema(description = "1- Godel, 2 - EPAM, 3 - ISSoft, 4 - ITechArt, 5 - Wargaming", example = "1")
    private Integer departmentId;

    @Schema(description = "Junior Java Developer", example = "Junior Java Developer")
    private String jobTitle;

    @Schema(description = "2003-02-11", example = "2003-02-11")
    private Date dateOfBirth;

    @Schema(description = "1 - MALE, 2 - FEMALE", example = "1")
    private Integer genderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(departmentId, that.departmentId) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(genderId, that.genderId);
    }
}
