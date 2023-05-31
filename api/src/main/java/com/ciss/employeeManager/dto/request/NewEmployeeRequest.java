package com.ciss.employeeManager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewEmployeeRequest {
    @NotBlank
    @Size(min = 2, max = 30, message = "Characters limit between 2 and 30")
    private String name;

    @NotBlank
    @Size(min = 2, max = 50, message = "Characters limit between 2 and 50")
    private String surname;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Long pisNumber;
}
