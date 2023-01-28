package peaksoft.service;

import peaksoft.entity.Instructor;
import peaksoft.resources.InstructorResource;
import peaksoft.resources.InstructorResourceImpl;

import java.util.List;

public class InstructorServiceImpl implements InstructorService{
    InstructorResource instructorResource = new InstructorResourceImpl();
    @Override
    public String saveInstructor(Instructor instructor) {
        return instructorResource.saveInstructor(instructor);
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor newInstructor) {
        return instructorResource.updateInstructor(id, newInstructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorResource.getInstructorById(id);
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long id) {
        return instructorResource.getInstructorsByCourseId(id);
    }

    @Override
    public String deleteInstructorById(Long id) {
        return instructorResource.deleteInstructorById(id);
    }

    @Override
    public String assignInstructorToCourse(Long courseIs, Long instructorId) {
        return instructorResource.assignInstructorToCourse(courseIs, instructorId);
    }
}
