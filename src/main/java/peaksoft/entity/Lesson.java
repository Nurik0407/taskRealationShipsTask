package peaksoft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_sequence_lesson")
    @SequenceGenerator(name = "generator_sequence_lesson", allocationSize = 1)
    private Long id;
    private String name;
    private String videoLink;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    private Course course;
@OneToMany(cascade = ALL)
    private List<Task> tasks = new ArrayList<>();

    public Lesson(String name, String videoLink) {
        this.name = name;
        this.videoLink = videoLink;
    }

    public Lesson(String name, String videoLink, Course course) {
        this.name = name;
        this.videoLink = videoLink;
        this.course = course;
    }

    @Override
    public String toString() {
        return "\nLesson{" +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\nvideoLink='" + videoLink +
                "\n~~~~~~~~~~~~~~~~~~~~~";
    }
}
