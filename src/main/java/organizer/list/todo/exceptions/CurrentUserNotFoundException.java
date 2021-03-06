package organizer.list.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CurrentUserNotFoundException extends RuntimeException {

    public CurrentUserNotFoundException() {
        super();
    }
}
