package com.uneb.labweb.spring_project.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.uneb.labweb.spring_project.dto.CourseDTO;
import com.uneb.labweb.spring_project.dto.LessonDTO;
import com.uneb.labweb.spring_project.enums.Category;
import com.uneb.labweb.spring_project.model.Course;
import com.uneb.labweb.spring_project.model.Lesson;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(
                lesson.getId(), lesson.getName(), lesson.getLinkLesson()))
                .collect(Collectors.toList());
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue(),
                lessons
        );

    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(converterCategoryValue(courseDTO.category()));
        
        List<Lesson> lessons = courseDTO.lessons().stream().map((lessonDTO) -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setLinkLesson(lessonDTO.linkLesson());
            lesson.setCourse(course);
            return lesson;
        }).collect(Collectors.toList());
        course.setLessons(lessons);
        return course;
    }

    public Category converterCategoryValue(String category) {
        if (category == null) {
            return null;
        }
        return switch (category) {
            case "Back-end" ->
                Category.BACK_END;
            case "Front-end" ->
                Category.FRONT_END;
            default ->
                throw new IllegalArgumentException("Categoria invalida" + category);
        };
    }
}
