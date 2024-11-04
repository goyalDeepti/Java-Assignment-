package com.example.demo.RepositoryTest;

import ch.qos.logback.core.util.Loader;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.ITransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RewardRepoTest {

    @InjectMocks
    private ITransactionRepository transactionRepository;

    @Test
    public void testFindByCustomerIdAndDateBetween(){
        Long customerId = 1L;
        LocalDate start = LocalDate.of(2023,10,1);
        LocalDate end = LocalDate.of(2023,10,31);
        double amount = 200;
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId,start,end);
        assertTrue(transactions.isEmpty());
    }

}
