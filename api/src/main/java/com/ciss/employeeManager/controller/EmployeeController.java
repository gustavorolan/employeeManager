package com.ciss.employeeManager.controller;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.dto.request.UpdateEmployeeRequest;
import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.service.employee.DeleteEmployeeService;
import com.ciss.employeeManager.service.employee.FindEmployeeService;
import com.ciss.employeeManager.service.employee.NewEmployeeService;
import com.ciss.employeeManager.service.employee.UpdateEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/employeeManager/v1/employee")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class EmployeeController {

    private final NewEmployeeService newEmployeeService;

    private final UpdateEmployeeService updateEmployeeService;

    private final FindEmployeeService findEmployeeService;

    private final DeleteEmployeeService deleteEmployeeService;

    @PostMapping("/new")
    public ResponseEntity<Long> create(@RequestBody @Valid NewEmployeeRequest request){
        Long id = newEmployeeService.create(request);
        return ResponseEntity.ok(id);
    }


    @PutMapping
    public ResponseEntity<String> update(@RequestBody @Valid UpdateEmployeeRequest request){
        updateEmployeeService.update(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id){
        EmployeeResponse response = findEmployeeService.getResponseById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> deleteById(@PathVariable Long id){
        deleteEmployeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<EmployeeResponse>> getEmployeesFromUser(@PathVariable Integer page){
        Page<EmployeeResponse> response = findEmployeeService.getEmployeesFromUser(page);
        return ResponseEntity.ok(response);
    }
}
