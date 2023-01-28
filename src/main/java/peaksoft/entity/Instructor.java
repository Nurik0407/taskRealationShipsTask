package peaksoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_sequence_ins")
    @SequenceGenerator(name = "generator_sequence_ins", sequenceName = "generator_id", allocationSize = 1)
    private Long id;
    @Column(name = "first_name", length = 20)
    private String firstname;
    @Column(name = "last_name", length = 20)
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor(String firstname, String lastName, String email, String phoneNumber) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\nInstructor{" +
                "\nid=" + id +
                "\nfirstname='" + firstname + '\'' +
                "\nlastName='" + lastName + '\'' +
                "\nemail='" + email + '\'' +
                "\nphoneNumber='" + phoneNumber +
                "\n~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
