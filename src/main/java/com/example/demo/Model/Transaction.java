package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private double amount;
    private LocalDate transactionDate;
    private String months;
    private Long customerId;

public Long getCustomerId() {
    return customerId;
}


    public Transaction(long l, double v, LocalDate start) {
    }


    public double getAmount() {
        return amount;
    }
}
