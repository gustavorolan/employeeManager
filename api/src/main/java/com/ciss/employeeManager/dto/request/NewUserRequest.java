package com.ciss.employeeManager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserRequest {
    @NotBlank
    @Size(min = 6, max = 120, message = "Characters limit between 6 and 120")
    private String password;

    @NotBlank
    @Email
    private String email;
}
