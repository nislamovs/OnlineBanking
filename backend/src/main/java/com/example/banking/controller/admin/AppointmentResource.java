package com.example.banking.controller.admin;

import com.example.banking.model.Appointment;
import com.example.banking.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@Slf4j
public class AppointmentResource {

    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public List<Appointment> findAppointmentList() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}/confirm")
    public void confirmAppointment(@PathVariable("id") Long id) {
        appointmentService.confirmAppointment(id);
    }
}
