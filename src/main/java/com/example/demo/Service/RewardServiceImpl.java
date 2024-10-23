package com.example.demo.Service;

import com.example.demo.IService.IRewardService;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RewardServiceImpl implements IRewardService {

    private static final Logger log = LoggerFactory.getLogger(RewardServiceImpl.class);
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Map<String,Integer> calculateRewards(Long customerId, String month) {
        Map<String, Integer> rewards = new HashMap<>();
        try {
            log.info("Inside calculateRewards method for customerId:{}, month:{}", customerId, month);
            //Define the start and end dates for the month
            String startDate = month + "-01";
            String endDate = month + "-31";
            List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
            int totalPoints = 0;
            for (Transaction transaction : transactions) {
                double amount = transaction.getAmount();
                if (amount > 100) {
                    totalPoints += (int) ((amount - 100) * 2) + 50; // one point for $50 to $100
                    log.info("Total points ");
                } else {
                    totalPoints += (int) (amount - 50); // 1 point fot each dollar over $50
                }
            }
            rewards.put(month, totalPoints);
        }catch(Exception e){
       log.error("Exception occurred while calculating rewards: {}", e.getStackTrace());
       throw new RuntimeException("Something went wrong");
        }
        return rewards;
    }
}
