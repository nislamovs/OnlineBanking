package com.example.banking.service;

import com.example.banking.model.PrimaryAccount;
import com.example.banking.model.SavingsAccount;

import java.security.Principal;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();

    SavingsAccount createSavingsAccount();

    void deposit(String accountType, double amount, Principal principal);

    void withdraw(String accountType, double amount, Principal principal);


}
