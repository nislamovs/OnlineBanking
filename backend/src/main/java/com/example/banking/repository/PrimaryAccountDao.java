package com.example.banking.repository;

import com.example.banking.model.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {

    PrimaryAccount findByAccountNumber(int accountNumber);
}
