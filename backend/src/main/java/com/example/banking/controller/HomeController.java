package com.example.banking.controller;

import com.example.banking.model.PrimaryAccount;
import com.example.banking.model.SavingsAccount;
import com.example.banking.model.User;
import com.example.banking.model.security.UserRole;
import com.example.banking.repository.RoleDao;
import com.example.banking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserService userService;
    private final RoleDao roleDao;

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/signup")
    public String signup(Model model) {

        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (userService.checkUserExists(user.getUsername(), user.getEmail())) {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);

            return "redirect:/";
        }
    }

    @GetMapping("/userFront")
    public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("savingsAccount", savingsAccount);

        return "userFront";
    }
}
