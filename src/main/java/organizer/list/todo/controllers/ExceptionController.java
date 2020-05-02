package organizer.list.todo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import organizer.list.todo.exceptions.TaskNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    public ModelAndView handleTaskNotFoundException(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest req, Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }
}
