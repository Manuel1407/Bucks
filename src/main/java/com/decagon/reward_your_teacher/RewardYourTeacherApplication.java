package com.decagon.reward_your_teacher;

import com.decagon.reward_your_teacher.domain.entities.SchoolEntity;
import com.decagon.reward_your_teacher.usecase.services.impl.SchoolServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class RewardYourTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(RewardYourTeacherApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(SchoolServiceImpl schoolServiceImpl) {
        if (schoolServiceImpl.getSchoolCount() < 1) {
            return args -> {
                // read json and write to db
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<SchoolEntity>> typeReference = new TypeReference<>() {
                };
                InputStream inputStream = TypeReference.class.getResourceAsStream("/Schools.json");
                try {
                    List<SchoolEntity> schools = mapper.readValue(inputStream, typeReference);
                    schoolServiceImpl.saveSchool(schools);
                } catch (IOException e) {
                    throw new RuntimeException("Cannot save school");
                }
            };
        }
        return null;
    }

}
