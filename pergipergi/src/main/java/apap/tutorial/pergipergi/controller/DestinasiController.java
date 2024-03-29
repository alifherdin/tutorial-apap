package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.service.DestinasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.userdetails.UserDetails;
 
@Controller
public class DestinasiController {
 
    @Qualifier("destinasiServiceImpl")
    @Autowired
    DestinasiService destinasiService;

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
 
    @GetMapping("/destinasi/add")
    public String addDestinasiForm(Model model){
        if (checkPrivilege().equals("agen")) {
            model.addAttribute("destinasi", new DestinasiModel());
            return "form-add-destinasi";
        } else {
            return "authorization-error";
        }
    }
 
    @PostMapping("/destinasi/add")
    public String addDestinasiSubmit(
            @ModelAttribute DestinasiModel destinasi,
            Model model
    ){
        destinasiService.addDestinasi(destinasi);
        model.addAttribute("noDestinasi", destinasi.getNoDestinasi());
        return "add-destinasi";
    }
 
    @GetMapping("/destinasi/viewall")
    public String viewAllDestinasi(Model model) {
        model.addAttribute("listDestinasi", destinasiService.getListDestinasi());
        return "viewall-destinasi";
    }
}