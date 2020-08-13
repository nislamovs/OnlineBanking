package com.example.banking.controller.admin;

import com.example.banking.model.PrimaryTransaction;
import com.example.banking.model.SavingsTransaction;
import com.example.banking.model.User;
import com.example.banking.service.TransactionService;
import com.example.banking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@Slf4j
public class UserResource {

    private final UserService userService;
    private final TransactionService transactionService;

    @GetMapping(value = "/user/all")
    public List<User> userList() {
        return userService.findUserList();
    }

    @GetMapping(value = "/user/primary/transaction")
    public List<PrimaryTransaction> getPrimaryTransactionList(@RequestParam("username") String username) {
        return transactionService.findPrimaryTransactionList(username);
    }

    @GetMapping(value = "/user/savings/transaction")
    public List<SavingsTransaction> getSavingsTransactionList(@RequestParam("username") String username) {
        return transactionService.findSavingsTransactionList(username);
    }

    @GetMapping(value = "/user/{username}/enable")
    public void enableUser(@PathVariable("username") String username) {
        userService.enableUser(username);
    }

    @GetMapping(value = "/user/{username}/disable")
    public void disableUser(@PathVariable("username") String username) {
        userService.disableUser(username);
    }
}
