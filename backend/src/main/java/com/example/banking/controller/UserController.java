package com.example.banking.controller;

import com.example.banking.model.User;
import com.example.banking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/profile")
    public String profile(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping(value = "/profile")
    public String profilePost(@ModelAttribute("user") User newUser, Model model) {
        User user = userService.findByUsername(newUser.getUsername());
        user.toBuilder().username(newUser.getUsername()).firstName(newUser.getFirstName()).lastName(newUser.getFirstName())
                .email(newUser.getEmail()).phone(newUser.getPhone());

        model.addAttribute("user", user);
        userService.saveUser(user);

        return "profile";
    }


}

