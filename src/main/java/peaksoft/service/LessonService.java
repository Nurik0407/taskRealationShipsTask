package peaksoft.service;

import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {
    String saveLesson(Long courseId, Lesson lesson);
    Lesson updateLesson(Long id,Lesson newLesson);

    Lesson getLessonById(Long id);
    List<Lesson> getLessonsByCourseId(Long id);
}
