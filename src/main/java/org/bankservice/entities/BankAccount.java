package org.bankservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankservice.enums.TypeAccount;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccount {

    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    @Enumerated (value = EnumType.STRING)
    private TypeAccount type;
}
