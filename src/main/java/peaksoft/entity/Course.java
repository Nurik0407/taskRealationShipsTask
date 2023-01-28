package peaksoft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_sequence_course")
    @SequenceGenerator(name = "generator_sequence_course", allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    private int duration;
    @Column(name = "column_at")
    private LocalDate creatAt;
    @Column(name = "imagine_link")
    private String imagineLink;
    private String description;
    @ManyToMany(mappedBy = "courses",cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    private List<Instructor> instructors;
    @OneToMany(cascade = ALL, mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    public Course(String courseName, int duration, LocalDate creatAt, String imagineLink, String description, List<Instructor> instructors, List<Lesson> lessons) {
        this.courseName = courseName;
        this.duration = duration;
        this.creatAt = creatAt;
        this.imagineLink = imagineLink;
        this.description = description;
        this.instructors = instructors;
        this.lessons = lessons;
    }

    public Course(String courseName, int duration, LocalDate creatAt, String imagineLink, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.creatAt = creatAt;
        this.imagineLink = imagineLink;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nCourse{" +
                "\nid=" + id +
                "\ncourseName: " + courseName +
                "\nduration=" + duration +
                "\ncreatAt=" + creatAt +
                "\nimagineLink='" + imagineLink + '\'' +
                "\ndescription='" + description +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
