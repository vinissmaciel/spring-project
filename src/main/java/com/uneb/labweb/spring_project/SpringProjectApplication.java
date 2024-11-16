package com.uneb.labweb.spring_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.uneb.labweb.spring_project.enums.Category;
import com.uneb.labweb.spring_project.model.Course;
import com.uneb.labweb.spring_project.model.Lesson;
import com.uneb.labweb.spring_project.repository.CourseRepository;

@SpringBootApplication
public class SpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {
            courseRepository.deleteAll();
            for (int i = 0; i < 20; i++) {
                Course c = new Course();
                c.setName("Curso Angular " + i);
                c.setCategory(Category.FRONT_END);

                Lesson l = new Lesson();
                l.setName("Introdução Ao Angular");
                l.setLinkLesson("https://www.uneb.br");
                l.setCourse(c);
                c.getLessons().add(l);

                l = new Lesson();
                l.setName("Instalando e Configurando Angular");
                l.setLinkLesson("https://www.uneb.br");
                l.setCourse(c);
                c.getLessons().add(l);
                courseRepository.save(c);
            }
        };
    }

}
