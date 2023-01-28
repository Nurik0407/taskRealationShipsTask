package peaksoft.service;

import peaksoft.entity.Lesson;
import peaksoft.resources.LessonResource;
import peaksoft.resources.LessonResourceImpl;

import java.util.List;

public class LessonServiceImpl implements LessonService{
    LessonResource lessonResource = new LessonResourceImpl();
    @Override
    public String saveLesson(Long courseId, Lesson lesson) {
        return lessonResource.saveLesson(courseId, lesson);
    }

    @Override
    public Lesson updateLesson(Long id, Lesson newLesson) {
        return lessonResource.updateLesson(id, newLesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonResource.getLessonById(id);
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long id) {
        return lessonResource.getLessonsByCourseId(id);
    }
}
