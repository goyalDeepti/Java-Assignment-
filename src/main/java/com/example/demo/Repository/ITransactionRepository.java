package com.example.demo.Repository;

import com.example.demo.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByCustIdAndDateBetween(Long custId, LocalDate startDate, LocalDate endDate);

    List<Transaction> findAllByMonthsAndDateBetween(String month, LocalDate startDate, LocalDate endDate);

    public Optional<Transaction> findByCustId(Long custId);


}
