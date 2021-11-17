package apap.tutorial.pergipergi.controller;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class PageController {

    private String checkPrivilege() {
        Object userinfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String x = ((UserDetails) userinfo).getAuthorities().toArray()[0].toString();

        if (x.equals("Admin")) {
            return "admin";
        } else if (x.equals("User")) {
            return "user";
        } else {
            return "agen";
        }
    }

    @RequestMapping("/")
    private String home(Model model) {
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        // model.addAttribute("kani", "Waktu sekarang adalah:");
        // model.addAttribute("yasa", LocalTime.now().format(dtf));

        if (checkPrivilege().equals("agen")) {
            model.addAttribute("isAgen", true);
        } else {
            model.addAttribute("isAgen", false);
        }
        
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
