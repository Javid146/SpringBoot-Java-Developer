package com.cydeo.service.impl;

import com.cydeo.dto.CourseDTO;
import com.cydeo.entity.Course;

import com.cydeo.repository.CourseRepository;
import com.cydeo.service.CourseService;
import com.cydeo.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final MapperUtil mapperUtil;

    public CourseServiceImpl(CourseRepository courseRepository, MapperUtil mapperUtil) {
        this.courseRepository = courseRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public CourseDTO createCourse(CourseDTO course) {
        courseRepository.save(mapperUtil.convert(course, new Course()));
        return course;
    }

    @Override
    public CourseDTO getCourseById(long courseId) {
        //as findById method is run here we will get below log info twice in console, in case we run api calls with postman:
        //Before () : -> Method : execution(CourseDTO com.cydeo.controller.CourseController.getCourseById(Long)) - Arguments : [1] - Target: com.cydeo.controller.CourseController@4e02f17d
        Course course = courseRepository.findById(courseId).get();
        return mapperUtil.convert(course, new CourseDTO());
    }

    @Override
    public List<CourseDTO> getCoursesByCategory(String category) {
        List<Course> list = courseRepository.findAllByCategory(category);
        return list.stream().map(obj -> mapperUtil.convert(obj, new CourseDTO())).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getCourses() {
        List<Course> list = courseRepository.findAll();
        return list.stream().map(obj -> mapperUtil.convert(obj, new CourseDTO())).collect(Collectors.toList());
    }

    @Override
    public void updateCourse(Long courseId, CourseDTO courseDTO) {

        Course course = mapperUtil.convert(courseDTO, new Course());

        courseRepository.findById(courseId).ifPresent(dbCourse -> {

            dbCourse.setName(course.getName());
            dbCourse.setCategory(course.getCategory());
            dbCourse.setDescription(course.getDescription());
            dbCourse.setRating(course.getRating());

            courseRepository.save(dbCourse);

        });

    }

    @Override
    public void deleteCourses() {
        courseRepository.deleteAll();
    }

    //@Loggable
    @Override
    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

}
