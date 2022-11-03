package org.bankservice;

import org.bankservice.entities.BankAccount;
import org.bankservice.enums.TypeAccount;
import org.bankservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository){
		return args -> {
			for (int i = 0; i < 10; i++) {
				BankAccount bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.createdAt(new Date())
						.type(Math.random()>0.5? TypeAccount.CURRENT_ACCOUNT:TypeAccount.SAVING_ACCOUNT)
						.balance(1000+Math.random()*90000)
						.currency("MAD")
						.build();
				bankAccountRepository.save(bankAccount);

			}
		};
	}

}
