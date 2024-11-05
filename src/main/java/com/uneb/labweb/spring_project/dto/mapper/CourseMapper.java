package com.uneb.labweb.spring_project.dto.mapper;

import org.springframework.stereotype.Component;

import com.uneb.labweb.spring_project.dto.CourseDTO;
import com.uneb.labweb.spring_project.enums.Category;
import com.uneb.labweb.spring_project.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue()
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
