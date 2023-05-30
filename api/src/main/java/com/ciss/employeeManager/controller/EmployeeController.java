package com.ciss.employeeManager.controller;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.dto.request.UpdateEmployeeRequest;
import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.service.FindEmployeeService;
import com.ciss.employeeManager.service.NewEmployeeService;
import com.ciss.employeeManager.service.UpdateEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/employeeManager/v1/employee")
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final NewEmployeeService newEmployeeService;

    private final UpdateEmployeeService updateEmployeeService;

    private final FindEmployeeService findEmployeeService;

    @PostMapping("/new")
    public ResponseEntity<Long> create(@RequestBody @Valid NewEmployeeRequest request){
        Long id = newEmployeeService.create(request);
        return ResponseEntity.ok(id);
    }


    @PutMapping
    public ResponseEntity<String> put(@RequestBody @Valid UpdateEmployeeRequest request){
        updateEmployeeService.put(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id){
        EmployeeResponse response = findEmployeeService.getResponseById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployeesFromUser(){
        List<EmployeeResponse> response = findEmployeeService.getEmployeesFromUser();
        return ResponseEntity.ok(response);
    }
}
