package org.bankservice.web;


import org.bankservice.dto.BankAccountRequestDTO;
import org.bankservice.dto.BankAccountResponseDTO;
import org.bankservice.entities.BankAccount;
import org.bankservice.repository.BankAccountRepository;
import org.bankservice.service.AccountService;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BankAccountRestController {

    private final BankAccountRepository bankAccountRepository;
    private final AccountService accountService;

    public BankAccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
    }


    @GetMapping("/accounts")
    List<BankAccount> allAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    BankAccount getAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account with %s Not Found",id)));
    }

    @PostMapping("/accounts")
    BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.addAcount(bankAccountRequestDTO);
    }

    @PutMapping("/accounts/{id}")
    BankAccount update(@RequestBody BankAccount bankAccount ,@PathVariable String id){
        BankAccount bankAccount1 = bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The Id Doesn't Match The Resources"));

        bankAccount1.setBalance(bankAccount.getBalance());
        bankAccount1.setCreatedAt(new Date());
        bankAccount1.setType(bankAccount.getType());
        bankAccount1.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(bankAccount1);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable String id){
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        bankAccountRepository.delete(bankAccount);
    }

}
