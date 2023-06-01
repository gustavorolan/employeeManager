package com.ciss.employeeManager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeRequest {

    @NotNull
    private Long employeeId;

    @Size(max = 30, message = "Characters limit between 2 and 30")
    private String name = "";

    @Size(max = 50, message = "Characters limit between 2 and 50")
    private String surname = "";

    @Email
    private String email = "";

    private Long pisNumber = 0L;
}
