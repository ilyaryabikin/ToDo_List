package organizer.list.todo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import organizer.list.todo.dtos.TaskDetails;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate targetDate;
}
