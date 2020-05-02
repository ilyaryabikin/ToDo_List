package organizer.list.todo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import organizer.list.todo.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
