package peaksoft.service;

import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {
    String saveTask(Task task,Long id);
    Task updateTask(Long id,Task newTask);
    List<Task> getAllTaskByLessonId(Long id);
    String deleteTaskById(Long id);
}
