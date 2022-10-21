package com.decagon.reward_your_teacher.dao;//package com.decagon.reward_your_teacher.domain.dao;
//
//import com.decagon.reward_your_teacher.domain.entities.AppUserEntity;
//import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
//import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
//import com.decagon.reward_your_teacher.domain.entities.enums.Role;
//import org.checkerframework.checker.units.qual.A;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@DataJpaTest
//@ExtendWith(MockitoExtension.class)
//class StudentDaoTest {
//
//    @Autowired
//    private StudentDao studentDao;
//
//    @Autowired
//    private AppUserDao appUserDao;
//    @Autowired
//    private SchoolDao schoolDao;
//
//
//    private static AppUserEntity appUser;
//
//    @BeforeEach
//    void setUp() {
//
//        appUser = AppUserEntity.builder()
//                .email("test@gmail.com")
//                .password("testPass123")
//                .role(Role.STUDENT).build();
//
//        AppUserEntity appUserEntity = appUserDao.saveRecord(appUser);
//
////        SchoolEntity school = SchoolEntity.builder()
////                .schoolName("Asajon School")
////                .schoolType("Secondary")
////                .schoolCity("Sangotedo")
////                .schoolAddress("7, Asajon way")
////                .schoolState("Lagos")
////                .build();
//
//     //   SchoolEntity schoolEntity = schoolDao.saveRecord(school);
//
//        StudentEntity student = StudentEntity.builder()
//                .name("PodA")
//               // .school(schoolEntity)
//                .phoneNumber("09883993884")
//                .appUserEntity(appUserEntity)
//                .build();
//
//        studentDao.saveRecord(student);
//    }
//
//    @Test
//    void getStudentEntityByAppUserEntity() {
//        StudentEntity studentEntityByAppUserEntity = studentDao.getStudentEntityByAppUserEntity(appUser);
//        assertEquals(studentEntityByAppUserEntity.getName(), "PodA");
//    }
//}