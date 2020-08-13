package com.example.banking.repository;

import com.example.banking.model.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber(int accountNumber);
}
