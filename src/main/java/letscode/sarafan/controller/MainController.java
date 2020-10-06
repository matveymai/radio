package letscode.sarafan.controller;

import letscode.sarafan.domain.User;
import letscode.sarafan.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Value(value = "${spring.profiles.active}")
    private String profile;

    private final MessageRepository messageRepository;

    @Autowired
    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();

        if(user!=null) {
            data.put("profile", user);
            data.put("messages", messageRepository.findAll());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode","dev".equals(profile));
        return "index";
    }
}
