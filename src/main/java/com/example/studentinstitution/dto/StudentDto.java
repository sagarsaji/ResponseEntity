package com.example.studentinstitution.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be blank")
    private String name;
    @NotNull
    @NotEmpty(message = "add some courses")
    private List<String> courses;

}
