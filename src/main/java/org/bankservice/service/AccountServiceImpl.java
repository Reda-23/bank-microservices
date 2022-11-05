package org.bankservice.service;

import org.bankservice.dto.BankAccountRequestDTO;
import org.bankservice.dto.BankAccountResponseDTO;
import org.bankservice.entities.BankAccount;
import org.bankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bankAccountRepository;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccountResponseDTO addAcount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount =  BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .type(bankAccountRequestDTO.getType())
                .currency(bankAccountRequestDTO.getCurrency())
                .build();
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(savedAccount.getId())
                .createdAt(savedAccount.getCreatedAt())
                .balance(savedAccount.getBalance())
                .type(savedAccount.getType())
                .currency(savedAccount.getCurrency())
                .build();

            return bankAccountResponseDTO;
    }
}
