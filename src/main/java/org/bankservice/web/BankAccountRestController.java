package org.bankservice.web;


import org.bankservice.entities.BankAccount;
import org.bankservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountRestController {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
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
    BankAccount save(@RequestBody BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }


}
