package peaksoft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_sequence_task")
    @SequenceGenerator(name = "generator_sequence_task",sequenceName = "sequence_task", allocationSize = 1)
    private Long id;
    private String name;
    private LocalDate deadline;
    private String task;

    public Task(String name, LocalDate deadline, String task) {
        this.name = name;
        this.deadline = deadline;
        this.task = task;
    }



    @Override
    public String toString() {
        return "\nTask{" +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\ndeadline=" + deadline +
                "\ntask='" + task+
                "\n~~~~~~~~~~~~~~~~~~~~";
    }
}
