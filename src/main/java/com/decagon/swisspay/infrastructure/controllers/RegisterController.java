package com.decagon.swisspay.infrastructure.controllers;

import com.decagon.swisspay.usecase.payload.request.StudentRegistrationRequest;
import com.decagon.swisspay.usecase.payload.request.TeacherRegistrationRequest;
import com.decagon.swisspay.usecase.payload.response.ApiResponse;
import com.decagon.swisspay.usecase.payload.response.RegistrationResponse;
import com.decagon.swisspay.usecase.services.RegisterService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/register")
@CrossOrigin(origins = "http://localhost:3000/**")
public class RegisterController {

    private final RegisterService registerService;
    
    @PostMapping(value = "/student")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerStudent(@Valid @RequestBody StudentRegistrationRequest studentRegistrationRequest) throws Exception {
       RegistrationResponse response = registerService.registerStudent(studentRegistrationRequest);
        return new ResponseEntity<>(new ApiResponse<>("Registered successfully",true,response),HttpStatus.CREATED);
    }

    @PostMapping(value = "/teacher")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerTeacher(@Valid @RequestParam("registration") String registration,@RequestParam("file") MultipartFile file) throws Exception {
        TeacherRegistrationRequest teacherRegistrationRequest = new Gson().fromJson(registration,TeacherRegistrationRequest.class);
      RegistrationResponse response =  registerService.registerTeacher( teacherRegistrationRequest,file);
        return new ResponseEntity<>(new ApiResponse<>("Registered Successfully",true,response), HttpStatus.CREATED);
    }
    @GetMapping(value = "/verification")
    public String verifyUser(@Valid @RequestParam("token") String token) throws Exception {
        registerService.verifyUser(token);
//        String link = "http://localhost:3000/login";
//        String link = "www.google.com";
        StringBuilder sb = new StringBuilder();
        sb.append("Email confirmed, follow the link below to login.");
        sb.append("<br><br><a href=\"http://localhost:3000/login\" target=\"_blank\">Login</a>");

        String link = sb.toString();
//        return "Registered successfully, follow the link to login, <a href=\""+ link +"\" target=\"_blank\">Login</a>";
        return link;
    }
}
