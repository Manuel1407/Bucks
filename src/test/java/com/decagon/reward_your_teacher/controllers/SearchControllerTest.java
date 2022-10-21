//package com.decagon.reward_your_teacher.controllers;
//
//import com.decagon.reward_your_teacher.SecurityConfigTest.TestSecurityConfig;
//import com.decagon.reward_your_teacher.usecase.payload.response.SchoolSearchResponse;
//import com.decagon.reward_your_teacher.usecase.payload.response.TeacherSearchResponse;
//import com.decagon.reward_your_teacher.usecase.services.SearchService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestSecurityConfig.class)
//@AutoConfigureMockMvc
//class SearchControllerTest {
//    @MockBean
//    private SearchService searchService;
//    @Autowired
//   private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        List<TeacherSearchResponse> list = new ArrayList<>();
//        TeacherSearchResponse teacherSearchResponse = new TeacherSearchResponse("fortunate","Grammer school",2);
//        TeacherSearchResponse teacherSearchResponse1 = new TeacherSearchResponse("busola","vile school",3);
//        list.add(teacherSearchResponse1);
//        list.add(teacherSearchResponse);
//        int offset = 0;
//        int pageSize = 10;
//
//        Set<SchoolSearchResponse> schoolSearchResponses = new HashSet<>();
//        SchoolSearchResponse schoolSearchResponse = new SchoolSearchResponse("Grammer School");
//        SchoolSearchResponse schoolSearchResponse2 = new SchoolSearchResponse("vill School");
//        SchoolSearchResponse schoolSearchResponse3 = new SchoolSearchResponse("corner School");
//        schoolSearchResponses.add(schoolSearchResponse);
//        schoolSearchResponses.add(schoolSearchResponse2);
//        schoolSearchResponses.add(schoolSearchResponse3);
//
//        Mockito.when(searchService.getAllTeachers(offset,pageSize)).thenReturn(list);
//        Mockito.when(searchService.searchTeacherByName("kenny")).thenReturn(list);
//        Mockito.when(searchService.findAllTeachersInASchool(offset,pageSize,"Grammer school")).thenReturn(list);
//        Mockito.when(searchService.findAllSchools(offset, pageSize)).thenReturn(schoolSearchResponses);
//
//    }
//
//    @Test
//    void getAllSchools() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/schools?offset=0&pageSize=10"))
//                .andExpect(status().isFound())
//                .andExpect(content().json("[{}, {}, {}]"));
//
//    }
//    @Test
//    void findAllTeacherInASchool() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/teachers/Grammer school?offset=0&pageSize=10"))
//                .andExpect(status().isFound())
//                .andExpect(content().json("[{}, {}]"));
//    }
//
//    @Test
//    void getAllTeachers() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/teachers/?offset=0&pageSize=10"))
//                .andExpect(status().isFound())
//                .andExpect(content().json("[{}, {}]"));
//    }
//
//    @Test
//    void findTeacher() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/teacher/kenny"))
//                .andExpect(status().isFound())
//                .andExpect(content().json("[{},{}]"));
//    }
//}