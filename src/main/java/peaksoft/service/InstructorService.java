package peaksoft.service;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {
    String saveInstructor(Instructor instructor);

    Instructor updateInstructor(Long id, Instructor newInstructor);

    Instructor getInstructorById(Long id);

    List<Instructor> getInstructorsByCourseId(Long id);

    String deleteInstructorById(Long id);

    String assignInstructorToCourse(Long courseIs, Long instructorId);
}
