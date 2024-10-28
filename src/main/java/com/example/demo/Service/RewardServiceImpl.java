package com.example.demo.Service;

import com.example.demo.ExceptionUtils.CustomerNotFoundException;
import com.example.demo.ExceptionUtils.InvalidMonthException;
import com.example.demo.IService.IRewardService;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RewardServiceImpl implements IRewardService {

    private static final Logger log = LoggerFactory.getLogger(RewardServiceImpl.class);
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Map<String,Integer> calculateRewards(Long customerId, String month) {
        Map<String, Integer> rewards = new HashMap<>();
            log.info("Inside calculateRewards method for customerId:{}, month:{}", customerId, month);
             customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("NO CUSTOMER PRESENT WITH ID = " + customerId));
            if(!checkMonthIsValid(month)){
                throw new InvalidMonthException("Month should be between Jan to Dec, please check");
            }
            //Define the start and end dates for the month
            String startDate = month + "-01";
            String endDate = month + "-31";
            List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
            int totalPoints = 0;
            for (Transaction transaction : transactions) {
                double amount = transaction.getAmount();
                if (amount > 100) {
                    totalPoints += (int) ((amount - 100) * 2) + 50; // one point for over 100
                } else {
                    totalPoints += (int) (amount - 50); // 1 point for each dollar between $50 to $100
                }
            }
            rewards.put(month, totalPoints);
        return rewards;
    }

    private Boolean checkMonthIsValid(String month){
        ArrayList<String> monthList =  new ArrayList<>(List.of("January","February","March","April","May","June","July","August","September","October","November","December"));
        return monthList.contains(month);
    }

}
