package com.exam.Lms.Repository;

import com.exam.Lms.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    public Optional<Course> findByName(String name);

    List<Course> findAllByName(String name);

    void deleteCourseByName(String name);

    @Query("SELECT c FROM Course c WHERE c.name = :name")
    Optional<Course> findCourseByName(@Param("name") String name);

}
