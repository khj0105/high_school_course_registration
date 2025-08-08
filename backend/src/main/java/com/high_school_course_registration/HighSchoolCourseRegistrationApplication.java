package com.high_school_course_registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HighSchoolCourseRegistrationApplication {
	public static void main(String[] args) { SpringApplication.run(HighSchoolCourseRegistrationApplication.class, args); }
}