package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private LocalDate date;
    private String months;
    private Long custId;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    public Customer customer;

public Long getCustId() {
    return custId;
}


    public Transaction(long l, double v, LocalDate start) {
    }


    public double getAmount() {
        return amount;
    }
}
