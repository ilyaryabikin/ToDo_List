package organizer.list.todo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import organizer.list.todo.dtos.UserCredentials;
import organizer.list.todo.services.UserService;

import javax.validation.Valid;

@Controller
@Slf4j
public class LoginController {
    private static final String REDIRECT_TO = "redirect:/";
    private static final String LOGIN_PAGE = "login";
    private static final String REGISTER_PAGE = "register";

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return LOGIN_PAGE;

    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("userCredentials", new UserCredentials());
        return REGISTER_PAGE;
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("userCredentials") @Valid UserCredentials userCredentials,
                                  BindingResult result) {
        if (result.hasErrors()) {
            log.warn("New user's input credentials have errors");
            return REGISTER_PAGE;
        }
        if (!userCredentials.getPassword().equals(userCredentials.getConfirmedPassword())) {
            result.rejectValue("password", "", "Password and confirmed password don't match");
            log.warn("New user's password and confirmed password don't match");
            return REGISTER_PAGE;
        }
        userService.createNewUser(userCredentials);
        log.info("New user have registered {}", userCredentials);
        return REDIRECT_TO + LOGIN_PAGE;
    }
}
