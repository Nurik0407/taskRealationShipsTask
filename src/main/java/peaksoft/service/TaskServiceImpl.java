package peaksoft.service;

import peaksoft.entity.Task;
import peaksoft.resources.TaskResource;
import peaksoft.resources.TaskResourceImpl;

import java.util.List;

public class TaskServiceImpl implements TaskService{
    TaskResource taskResource = new TaskResourceImpl();
    @Override
    public String saveTask(Task task,Long id) {
        return taskResource.saveTask(task,id);
    }

    @Override
    public Task updateTask(Long id, Task newTask) {
        return taskResource.updateTask(id, newTask);
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long id) {
        return taskResource.getAllTaskByLessonId(id);
    }

    @Override
    public String deleteTaskById(Long lessonId,Long id) {
        return taskResource.deleteTaskById(lessonId,id);
    }
}
