package apap.tutorial.pergipergi.controller;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BaseController {
    @GetMapping("/")
    private String home(Model model) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        model.addAttribute("kani", "Waktu sekarang adalah:");
        model.addAttribute("yasa", LocalTime.now().format(dtf));
        
        return "home";
    }
}
