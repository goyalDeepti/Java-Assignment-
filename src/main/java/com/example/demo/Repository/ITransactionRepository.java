package com.example.demo.Repository;

import com.example.demo.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByCustomerIdAndTransactionDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);

    List<Transaction> findAllByMonthsAndTransactionDateBetween(String month, LocalDate startDate, LocalDate endDate);

    public Optional<Transaction> findByCustomerId(Long customerId);


}
