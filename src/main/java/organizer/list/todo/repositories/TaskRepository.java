package organizer.list.todo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import organizer.list.todo.dtos.TaskDetails;
import organizer.list.todo.entities.Task;
import organizer.list.todo.entities.User;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<TaskDetails> findTaskByUser_Name(String name);

    List<TaskDetails> findTaskByUser(User user);
}
