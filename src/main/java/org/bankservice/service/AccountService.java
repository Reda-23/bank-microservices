package org.bankservice.service;

import org.bankservice.dto.BankAccountRequestDTO;
import org.bankservice.dto.BankAccountResponseDTO;

public interface AccountService {

    BankAccountResponseDTO addAcount(BankAccountRequestDTO bankAccountRequestDTO);
}
