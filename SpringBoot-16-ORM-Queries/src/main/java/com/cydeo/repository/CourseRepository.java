package com.cydeo.repository;

import com.cydeo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

//todo LONG HERE IS FOR PRIMARY KEY
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCategory(String category);
    List<Course> findByCategoryOrderByName(String category);
    //verify if category exist
    boolean existsByCategory(String category);
    //count of courses
    int countByCategory(String category);
    //find name that stars with
    List<Course> findByNameStartingWith(String name);
    //return Stream
    Stream<Course> streamByCategory(String category);

    //todo NAMED PARAMETER JPQL
    @Query("select e from Course e where e.category = :category and e.rating > :rating")
    List<Course> getCourseByCategoryAndRatingGreaterThan(@Param("category") String category, @Param("rating") String rating);
}
