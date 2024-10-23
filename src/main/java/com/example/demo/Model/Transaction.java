package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private String date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public double getAmount() {
        return amount;
    }
}
