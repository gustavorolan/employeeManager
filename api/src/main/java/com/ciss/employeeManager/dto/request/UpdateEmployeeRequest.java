package com.ciss.employeeManager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {

    @NotNull
    private Long employeeId;

    private String name = "";

    private String surname = "";

    private String email = "";

    private Long pisNumber = 0L;
}
