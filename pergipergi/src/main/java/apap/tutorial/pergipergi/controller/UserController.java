package apap.tutorial.pergipergi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tutorial.pergipergi.service.UserService;
import apap.tutorial.pergipergi.model.RoleModel;
import apap.tutorial.pergipergi.model.UserModel;
import apap.tutorial.pergipergi.service.RoleService;
import apap.tutorial.pergipergi.model.PassModel;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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

    @GetMapping("/add")
    public String addUserFormPage(Model model) {
        if (checkPrivilege().equals("admin")) {
            UserModel user = new UserModel();
            List<RoleModel> listRole = roleService.findAll();
            model.addAttribute("user", user);
            model.addAttribute("listRole", listRole);

            return "form-add-user";

        } else {
            return "authorization-error";
        }
    }

    @PostMapping("/add")
    public String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        UserModel z = userService.addUser(user);

        if (z.getNama().equals("Error: Email sudah dipakai.")) {
            return "kosong";
        }

        model.addAttribute("user", user);

        return "redirect:/";
    }

    @GetMapping("/allUsers")
    public String seeAllUsers(Model model) {
        if (checkPrivilege().equals("admin")) {
            model.addAttribute("listUsers", userService.seeAllUsers());
            return "viewall-users";
        } else {
            return "authorization-error";
        }
    }

    @GetMapping("/hapus/{uname}")
    public String delUsers(Model model, @PathVariable String uname) {
        if (checkPrivilege().equals("admin")) {
            userService.removeUser(uname);
            return "redirect:/user/allUsers";
        } else {
            return "authorization-error";
        }
    }

    @GetMapping("/password-change")
    public String chgPassForm(Model model) {
        PassModel temp = new PassModel();

        model.addAttribute("box", temp);

        return "form-change-password";
    }

    @PostMapping("/password-change")
    public String chgPass(Model model, @ModelAttribute PassModel passes) {
        Object userinfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String namaUser = ((UserDetails) userinfo).getUsername();

        if (userService.checkPassword(namaUser, passes.getOldPass())) {
            if (passes.getNewPass().equals(passes.getPassConfirm())) {
                UserModel zero = userService.updatePassword(namaUser, passes.getNewPass());

                return "update-password-success";
            } else {
                model.addAttribute("warning", "Konfirmasikan password baru anda dengan tepat.");
                model.addAttribute("box", passes);
            }
        } else {
            model.addAttribute("warning", "Pastikan bahwa password lama yang dimasukkan sudah tepat.");
            model.addAttribute("box", passes);
        }

        return "form-change-password";
    }

}
