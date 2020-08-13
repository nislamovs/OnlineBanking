package com.example.banking.controller;

import com.example.banking.model.Appointment;
import com.example.banking.model.User;
import com.example.banking.service.AppointmentService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/appointment")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @GetMapping(value = "/create")
    public String createAppointment(Model model) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        model.addAttribute("dateString", "");

        return "appointment";
    }

    @PostMapping(value = "/create")
    public String createAppointmentPost(@ModelAttribute("appointment") Appointment appointment, @ModelAttribute("dateString") String date, Model model, Principal principal) throws ParseException {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date d1 = format1.parse(date);
        appointment.setDate(d1);

        User user = userService.findByUsername(principal.getName());
        appointment.setUser(user);

        appointmentService.createAppointment(appointment);

        return "redirect:/userFront";
    }

}
