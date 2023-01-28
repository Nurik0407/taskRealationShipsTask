package peaksoft;

import peaksoft.config.Util;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.service.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    static CourseService courseService = new CourseServiceImpl();
    static InstructorService instructorService = new InstructorServiceImpl();
    static LessonService lessonService = new LessonServiceImpl();
    static TaskService taskService = new TaskServiceImpl();

    public static void main(String[] args) {
        Util.getEntityManagerFactory();

        while (true) {
            System.out.println("""
                    1 COURSE
                    2 INSTRUCTOR
                    3 LESSON
                    4 TASK
                      """);
            int j = new Scanner(System.in).nextInt();

            switch (j) {

                case 1 -> {
                    try {
                        boolean trueOrFalse = true;
                        while (trueOrFalse) {
                            System.out.println("""
                                    1 SAVE COURSE
                                    2 GET COURSE BY ID
                                    3 GET ALL COURSE (sort by creat at)
                                    4 UPDATE COURSE
                                    5 DELETE COURSE BY ID
                                    6 GET COURSE BY NAME
                                    7 EXIT
                                    """);
                            int i = new Scanner(System.in).nextInt();
                            switch (i) {
                                case 1 -> System.out.println(courseService.saveCourse(scanCourse()));
                                case 2 -> System.out.println(courseService.getCourseById(scanId()));
                                case 3 -> {
                                    System.out.println("sort by creat at (asc/desc): ");
                                    String descOrAsc = new Scanner(System.in).nextLine();
                                    System.out.println(courseService.getAllCourse(descOrAsc));
                                }
                                case 4 -> System.out.println(courseService.updateCourse(scanId(), scanCourse()));
                                case 5 -> System.out.println(courseService.deleteCourseById(scanId()));
                                case 6 -> {
                                    System.out.println("Enter course name: ");
                                    String name = new Scanner(System.in).nextLine();
                                    System.out.println(courseService.getCourseByName(name));
                                }
                                case 7 -> trueOrFalse = false;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    boolean trueOrFalse = true;
                    while (trueOrFalse) {
                        System.out.println("""
                                1 SAVE INSTRUCTOR
                                2 UPDATE INSTRUCTOR
                                3 GET INSTRUCTOR BY ID
                                4 GET INSTRUCTORS BY COURSE ID
                                5 DELETE INSTRUCTOR BY ID
                                6 ASSIGN INSTRUCTOR TO COURSE
                                7 EXIT
                                """);
                        try {
                            int i = new Scanner(System.in).nextInt();
                            switch (i) {
                                case 1 -> System.out.println(instructorService.saveInstructor(scanInstr()));
                                case 2 -> System.out.println(instructorService.updateInstructor(scanId(), scanInstr()));
                                case 3 -> System.out.println(instructorService.getInstructorById(scanId()));
                                case 4 -> System.out.println(instructorService.getInstructorsByCourseId(scanId()));
                                case 5 -> System.out.println(instructorService.deleteInstructorById(scanId()));
                                case 6 -> {
                                    System.out.println("Enter course id: ");
                                    Long idc = new Scanner(System.in).nextLong();
                                    System.out.println("Enter instructor id: ");
                                    Long idi = new Scanner(System.in).nextLong();
                                    System.out.println(instructorService.assignInstructorToCourse(idc, idi));
                                }
                                case 7 -> trueOrFalse = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                case 3 -> {
                    boolean trueOrFalse = true;
                    while (trueOrFalse) {
                        System.out.println("""
                                1  SAVE LESSON
                                2 UPDATE LESSON
                                3 GET LESSON BY ID
                                4 GET LESSONS BY COURSE ID
                                7 EXIT
                                """);
                        try {
                            int i = new Scanner(System.in).nextInt();
                            switch (i) {
                                case 1 -> {
                                    System.out.println("Enter course ID: ");
                                    Long id = new Scanner(System.in).nextLong();
                                    System.out.println("Enter lesson name: ");
                                    String name = new Scanner(System.in).nextLine();
                                    System.out.println("Enter video link: ");
                                    String link = new Scanner(System.in).nextLine();
                                    Lesson lesson = new Lesson(name, link);
                                    System.out.println(lessonService.saveLesson(id, lesson));
                                }
                                case 2 -> {
                                    System.out.println("Enter lesson name: ");
                                    String name = new Scanner(System.in).nextLine();
                                    System.out.println("Enter video link: ");
                                    String link = new Scanner(System.in).nextLine();
                                    Lesson lesson = new Lesson(name, link);
                                    System.out.println(lessonService.updateLesson(scanId(), lesson));
                                }
                                case 3 -> System.out.println(lessonService.getLessonById(scanId()));
                                case 4 -> System.out.println(lessonService.getLessonsByCourseId(scanId()));
                                case 7 -> trueOrFalse = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                case 4 -> {
                    boolean trueOrFalse = true;
                    while (trueOrFalse) {
                        System.out.println("""
                                1 SAVE TASK
                                2 UPDATE TASK
                                3 GET ALL TASK BY LESSON ID
                                4 DELETE TASK BY ID
                                7 EXIT
                                """);
                        try {
                            int i = new Scanner(System.in).nextInt();
                            switch (i) {
                                case 1 -> {
                                    System.out.println("Enter name task: ");
                                    String name = new Scanner(System.in).nextLine();
                                    System.out.println("Enter deadline:(dd/mm/yyyy) ");
                                    String deadLine = new Scanner(System.in).nextLine();
                                    String[] split = deadLine.split("/");
                                    System.out.println("Enter task: ");
                                    String taskk = new Scanner(System.in).nextLine();
                                    System.out.println("Enter lesson id: ");
                                    Long id = new Scanner(System.in).nextLong();
                                    Task task = new Task(name, LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0])), taskk);
                                    System.out.println(taskService.saveTask(task, id));
                                }
                                case 2 -> System.out.println(taskService.updateTask(scanId(), scanTask()));
                                case 3 -> System.out.println(taskService.getAllTaskByLessonId(scanId()));
                                case 4 -> {
                                    System.out.println("Enter task id: ");
                                    Long taskId = new Scanner(System.in).nextLong();
                                    System.out.println(taskService.deleteTaskById(taskId));
                                }
                                case 7 -> trueOrFalse = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }

    }

    static Course scanCourse() {
        System.out.println("Enter course name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter duration: ");
        int duration = new Scanner(System.in).nextInt();
        System.out.println("Enter creat at:(dd/mm/yyyy) ");
        String creat = new Scanner(System.in).nextLine();
        String[] split = creat.split("/");
        System.out.println("Enter image link: ");
        String image = new Scanner(System.in).nextLine();
        System.out.println("Enter description: ");
        String description = new Scanner(System.in).nextLine();
        Course course = new Course(name, duration,
                LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0])),
                image, description);
        return course;
    }

    static Instructor scanInstr() {
        System.out.println("Enter first name: ");
        String first = new Scanner(System.in).nextLine();
        System.out.println("Enter last name: ");
        String last = new Scanner(System.in).nextLine();
        System.out.println("Enter email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.println("Enter phone number: ");
        String number = new Scanner(System.in).nextLine();
        Instructor instructor = new Instructor(first, last, email, number);
        return instructor;
    }


    static Task scanTask() {
        System.out.println("Enter name task: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter deadline:(dd/mm/yyyy) ");
        String deadLine = new Scanner(System.in).nextLine();
        String[] split = deadLine.split("/");
        System.out.println("Enter task: ");
        String taskk = new Scanner(System.in).nextLine();
        Task task = new Task(name, LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0])), taskk);
        return task;
    }

    static Long scanId() {
        try {
            System.out.println("Enter ID: ");
            Long id = new Scanner(System.in).nextLong();
            return id;
        } catch (InputMismatchException e) {
            System.out.println("!!! number");
        }
        return null;
    }
}

