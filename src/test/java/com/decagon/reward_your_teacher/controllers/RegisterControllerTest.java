//package com.decagon.reward_your_teacher.controllers;
//
//import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
//import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
//import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
//import com.decagon.reward_your_teacher.domain.entities.enums.Position;
//import com.decagon.reward_your_teacher.domain.entities.enums.Role;
//import com.decagon.reward_your_teacher.domain.entities.enums.Status;
//import com.decagon.reward_your_teacher.infrastructure.configuration.security.AuthUserService;
//import com.decagon.reward_your_teacher.usecase.payload.request.StudentRegistrationRequest;
//import com.decagon.reward_your_teacher.usecase.payload.request.TeacherRegistrationRequest;
//import com.decagon.reward_your_teacher.usecase.payload.response.RegistrationResponse;
//import com.decagon.reward_your_teacher.usecase.services.RegisterService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.time.LocalDateTime;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class RegisterControllerTest {
//@Autowired
//private MockMvc mockMvc;
//@MockBean
//private AuthUserService authUserService;
//@MockBean
//private RegisterService registerService;
//static TeacherRegistrationRequest teacherRegistrationRequest;
//
//private static MockMultipartFile file;
//private static TeacherEntity teacher;
//   private static StudentRegistrationRequest studentRegistrationRequest;
//   private static RegistrationResponse response;
//    @BeforeEach
//    void setUp() {
//    studentRegistrationRequest = StudentRegistrationRequest
//            .builder()
//            .name("PodA")
//            .email("pod@gmail.com")
//            .phoneNumber("08101135678")
//            .schoolName("Grammer school")
//            .password("1234")
//            .build();
//        AppUserEntity appUserEntity = AppUserEntity.builder()
//                .email("k@gmail.com")
//                .password("12345")
//                .role((Role.TEACHER))
//                .build();
//        SchoolEntity school = SchoolEntity.builder().schoolState("lagos").schoolAddress("Lagos state")
//                .schoolCity("sangotedo").schoolType("primary").schoolName("Gramer school").build();
//
//         teacher = TeacherEntity.builder()
//                .name("PodA")
//                .phoneNumber("08101135965")
//                .school(school)
//                .nin("http://www.nin.com")
//                .yearsOfTeaching(3)
//                .subjectsTaught("english")
//                .appUserEntity(appUserEntity)
//                .position(Position.TEACHER)
//                .status(Status.ACTIVE)
//                .about("I am a teacher")
//                .build();
//
//         file = new MockMultipartFile("file", "orig", null, "bar".getBytes());
//     response= RegistrationResponse.builder().message("success").timeStamp(LocalDateTime.now()).build();
//    teacherRegistrationRequest = TeacherRegistrationRequest.builder()
//            .name("POD A")
//            .email("kenny@gmail.com")
//            .school("Sangotedo High School")
//          //  .image(file)
//            .schoolType("High School")
//            .subjectTaught("Math")
//            .phoneNumber("09060206819")
//            .yearsOfTeaching(7)
//            .password("password")
//            .build();
//    }
//
//
//    @Test
//    void registerStudent() throws Exception {
//        Mockito.when(registerService.registerStudent(studentRegistrationRequest))
//                .thenReturn(new RegistrationResponse<>("Success", LocalDateTime.now()));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/register/student")
//                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
//                        "        \"name\" : \"Iyke\",\n" +
//                        "        \"email\" : \"k@gmail.com\",\n" +
//                        "        \"password\" : \"1234\",\n" +
//                        "        \"phoneNumber\":\"12344323\",\n" +
//                        "        \"schoolName\" : \"Pearlville School\"\n" +
//                        "        }")).andExpect(MockMvcResultMatchers.status().isCreated());
//    }
//
////    @Test
////    @Disabled
////    void registerTeacher() throws Exception {
////        Mockito.when(registerService.registerTeacher(teacherRegistrationRequest, file))
////                .thenReturn(response);
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/register/teacher?name=kenny&email=g@gmail.com&password=1234&school" +
////                        "=Pearlville School&yearsOfTeaching=4&subjectTaught=math&schoolType=primary&phoneNumber=08101135965")
////                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
////    }
//}