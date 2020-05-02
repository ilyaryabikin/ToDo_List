package organizer.list.todo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import organizer.list.todo.entities.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TaskDetails {

    private Long id;

    @NotBlank
    private String description;

    private String user;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    public TaskDetails(Task task) {
        this.id = task.getId();
        this.description = task.getDescription();
        this.user = task.getUser().getName();
        this.targetDate = task.getTargetDate();
    }
}
