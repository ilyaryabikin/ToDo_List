package organizer.list.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import organizer.list.todo.dtos.UserCredentials;
import organizer.list.todo.entities.User;
import organizer.list.todo.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(UserCredentials userCredentials) {
        User user = new User();
        user.setName(userCredentials.getUsername());
        user.setPassword(userCredentials.getPassword());
        userRepository.save(user);
    }
}
