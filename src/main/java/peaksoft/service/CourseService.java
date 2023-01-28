package peaksoft.service;

import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {
    String saveCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourse(String ascendingOrDescending);
    Course updateCourse(Long id,Course course);
    String deleteCourseById(Long id);
    Course getCourseByName(String courseName);
}
