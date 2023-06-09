package com.ciss.employeeManager.controller;

import com.ciss.employeeManager.dto.request.NewUserRequest;
import com.ciss.employeeManager.service.user.NewUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/employeeManager/v1/user")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final NewUserService newUserService;

    @PostMapping("/new")
    public ResponseEntity<Long> create(@RequestBody @Valid NewUserRequest request){
        Long id = newUserService.create(request);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok().build();
    }
}
