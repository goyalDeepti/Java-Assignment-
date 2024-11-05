package com.example.demo.ServiceTest;

import com.example.demo.DTO.RewardsDTO;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.ITransactionRepository;
import com.example.demo.Service.ServiceImpl.RewardServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RewardServiceTest {

    @InjectMocks
    private RewardServiceImpl rewardService;

    @Mock
    private ITransactionRepository transactionRepository;

    /**
     * testCalculateRewards() will check with No Transactions
     */
    @Test
    public void testCalculateRewardsWithNoTransactions(){
        Long customerId = 1L;
        String month = "2023-10";
        List<Transaction>transactions = Collections.emptyList();
        when(transactionRepository.findByCustomerIdAndDateBetween(any(),any(),any())).thenReturn((transactions));
        RewardsDTO result = rewardService.calculateRewards(customerId,month);
        assertEquals(0, result.rewards);
    }
    /**
     * testCalculateRewards() will check with single Transaction
     */
    @Test
    public void testCalculateRewardsWithSingleTransactionAbove100(){
        Long customerId = 1L;
        LocalDate start = LocalDate.of(2023,10,1);
        LocalDate end = LocalDate.of(2023,10,31);
        List<Transaction>transactions  = Arrays.asList(
                new Transaction(1L,150.0,start)
        );
        when(transactionRepository.findByCustomerIdAndDateBetween(customerId,start,end)).thenReturn((transactions));
        RewardsDTO result = rewardService.calculateRewards(customerId,"2023-10");
        assertEquals(100, result.rewards);
        assertEquals(150, result.rewards);
    }

    /**
     * testCalculateRewards() will check with single Transaction between 50 and 100
     */

    @Test public void testCalculateRewardsWithSingleTransactionBetween50And100(){
        Long customerId = 1L;
        LocalDate start = LocalDate.of(2023,10,1);
        LocalDate end = LocalDate.of(2023,10,31);
        List<Transaction>transactions  = Arrays.asList(
                new Transaction(1L,150.0,start)
        );
        when(transactionRepository.findByCustomerIdAndDateBetween(customerId,start,end)).thenReturn((transactions));
        RewardsDTO result = rewardService.calculateRewards(customerId,"2023-10");
        assertEquals(25, result.rewards);
        assertEquals(75, result.rewards);
    }

    /**
     * testCalculateRewards() will check with multiple Transactions
     */
    @Test public void testCalculateRewardsWithMultipleTransaction(){
        Long customerId = 1L;
        LocalDate start = LocalDate.of(2023,10,1);
        LocalDate end = LocalDate.of(2023,10,31);
        List<Transaction>transactions  = Arrays.asList(
                new Transaction(1L,150.0,start),
                new Transaction(2L,75.0,start),
                new Transaction(3L,120.0,start)
        );
        when(transactionRepository.findByCustomerIdAndDateBetween(customerId,start,end)).thenReturn((transactions));
        RewardsDTO result = rewardService.calculateRewards(customerId,"2023-10");
        assertEquals(140, result.rewards);
        assertEquals(345, result.rewards);
    }


}
