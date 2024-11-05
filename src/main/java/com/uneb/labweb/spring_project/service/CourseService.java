package com.uneb.labweb.spring_project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uneb.labweb.spring_project.dto.CourseDTO;
import com.uneb.labweb.spring_project.dto.mapper.CourseMapper;
import com.uneb.labweb.spring_project.exception.RecordNotFoundException;
import com.uneb.labweb.spring_project.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository,
            CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public CourseDTO update(@NotNull @Positive Long id,
            @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(this.courseMapper.converterCategoryValue(
                            course.category()));
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(
                courseRepository.save(courseMapper.toEntity(course))
        );
    }
}
