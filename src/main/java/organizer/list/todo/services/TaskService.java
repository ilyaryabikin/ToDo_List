package organizer.list.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import organizer.list.todo.dtos.TaskDetails;
import organizer.list.todo.entities.Task;
import organizer.list.todo.repositories.TaskRepository;
import organizer.list.todo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static organizer.list.todo.security.PrincipalManager.getCurrentUser;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Optional<TaskDetails> findTaskDetailsById(Long id) {
        return taskRepository.findById(id)
                .map(TaskDetails::new);
    }

    public List<TaskDetails> findTaskByUser_Name(String name) {
        return taskRepository.findTaskByUser_Name(name);
    }

    public void save(TaskDetails taskDetails) {
        getCurrentUser()
                .flatMap(userRepository::findUserByName)
                .ifPresent(user -> {
                    Task task = new Task();
                    task.setId(taskDetails.getId());
                    task.setDescription(taskDetails.getDescription());
                    task.setTargetDate(taskDetails.getTargetDate());
                    task.setUser(user);
                    taskRepository.save(task);
                });
    }

    public void delete(Long id) {
        taskRepository.findById(id)
                .ifPresent(taskRepository::delete);
    }
}
