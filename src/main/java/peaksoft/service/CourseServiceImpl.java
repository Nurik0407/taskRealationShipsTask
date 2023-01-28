package peaksoft.service;

import peaksoft.entity.Course;
import peaksoft.resources.CourseResource;
import peaksoft.resources.CourseResourceImpl;

import java.util.List;

public class CourseServiceImpl implements CourseService{
    CourseResource courseResource = new CourseResourceImpl();
    @Override
    public String saveCourse(Course course) {
        return courseResource.saveCourse(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseResource.getCourseById(id);
    }

    @Override
    public List<Course> getAllCourse(String ascendingOrDescending) {
        return courseResource.getAllCourse(ascendingOrDescending);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        return courseResource.updateCourse(id, course);
    }

    @Override
    public String deleteCourseById(Long id) {
        return courseResource.deleteCourseById(id);
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseResource.getCourseByName(courseName);
    }
}
