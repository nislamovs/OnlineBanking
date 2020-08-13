package com.example.banking.controller;

import com.example.banking.model.*;
import com.example.banking.service.AccountService;
import com.example.banking.service.TransactionService;
import com.example.banking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping("/primaryAccount")
    public String primaryAccount(Model model, Principal principal) {
        List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionList(principal.getName());

        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("primaryTransactionList", primaryTransactionList);

        return "primaryAccount";
    }

    @GetMapping("/savingsAccount")
    public String savingsAccount(Model model, Principal principal) {
        List<SavingsTransaction> savingsTransactionList = transactionService.findSavingsTransactionList(principal.getName());
        User user = userService.findByUsername(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("savingsAccount", savingsAccount);
        model.addAttribute("savingsTransactionList", savingsTransactionList);

        return "savingsAccount";
    }

    @GetMapping(value = "/deposit")
    public String deposit(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");

        return "deposit";
    }

    @PostMapping(value = "/deposit")
    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
        accountService.deposit(accountType, Double.parseDouble(amount), principal);

        return "redirect:/userFront";
    }

    @GetMapping(value = "/withdraw")
    public String withdraw(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");

        return "withdraw";
    }

    @PostMapping(value = "/withdraw")
    public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
        accountService.withdraw(accountType, Double.parseDouble(amount), principal);

        return "redirect:/userFront";
    }
}
