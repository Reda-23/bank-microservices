package org.bankservice.web;


import org.bankservice.entities.BankAccount;
import org.bankservice.repository.BankAccountRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Optional;

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
