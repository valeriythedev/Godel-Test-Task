package com.mastery.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
@Schema(description = "Entity of employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    @Schema(description = "Alex", example = "Denis")
    private String firstName;

    @Column(name = "last_name")
    @Schema(description = "Johnson")
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employees_departments",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<Department> departments;

    @Column(name = "job_title")
    @Schema(description = "Junior Java Developer")
    private String jobTitle;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employees_genders",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "gender_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<Gender> genders;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    @Schema(description = "2003-02-11")
    private Date dateOfBirth;

    public Employee(Integer id, String firstName, String lastName, String jobTitle, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
