package com.example.demo.Service.ServiceImpl;

import com.example.demo.DTO.RewardsDTO;
import com.example.demo.ExceptionUtils.CustomerNotFoundException;
import com.example.demo.ExceptionUtils.InvalidMonthException;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.ICustomerRepository;
import com.example.demo.Repository.ITransactionRepository;
import com.example.demo.Service.IRewardService;
import com.example.demo.Wrapper.MonthsWrapper;
import com.example.demo.Wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;

@Slf4j
@Service
public class RewardServiceImpl implements IRewardService {


    private static final Logger log = LoggerFactory.getLogger(RewardServiceImpl.class);
    @Autowired
    ITransactionRepository transactionRepository;

        @Autowired
        ICustomerRepository customerRepository;

        @Override
        public RewardsDTO calculateRewards(Long customerId, String month, int year) {
            log.info("Inside calculateRewards method for customerId:{}, month:{}", customerId, month);
            customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("NO CUSTOMER PRESENT WITH ID = " + customerId));
                if (!checkMonthIsValid(month))
                    throw new InvalidMonthException("Month should be between Jan to Dec, please check");
                    //Define the start and end dates for the month
            LocalDate startDate = LocalDate.of(year, 1, 1);
            LocalDate endDate = LocalDate.of(year, 12, 31);
            List<Transaction>transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
                    log.info("Count of data record of transactions: {}", transactions.size());
            int totalPoints = 0;
            for (Transaction transaction : transactions) {
              double amount = transaction.getAmount();
                if (amount > 100) {
                    totalPoints += (int) ((amount - 100) * 2) + 50; // one point for over 100
                } else {
                    totalPoints += (int) (amount - 50); // 1 point for each dollar between $50 to $100
                }
            }
            List<String>monthList = new ArrayList<>();
            monthList.add(month);
            RewardsDTO rewardsDTO = new RewardsDTO();
            rewardsDTO.custId = customerId;
            rewardsDTO.rewards = totalPoints;
             rewardsDTO.months = monthList;
            return rewardsDTO;
        }

        private Boolean checkMonthIsValid(String month){
            ArrayList<String> monthDataList =  new ArrayList<>(List.of("January","February","March","April","May","June","July","August","September","October","November","December"));
            return monthDataList.contains(month);
        }

        @Override
        public ResponseWrapper getRewardsBasedOnMultipleMonths(MonthsWrapper months){
            ResponseWrapper responseWrapper = new ResponseWrapper();
            List<String> monthList = new ArrayList<>();
            List<String> monthsData = months.months;
            for(String month : monthsData){
                log.info("initial data of month: {}",month);
                if (!checkMonthIsValid(month))
                    throw new InvalidMonthException("Month should be between Jan to Dec, please check");
                //Define the start and end dates for the month
                LocalDate startDate = LocalDate.of(months.year, 1, 1);
                LocalDate endDate = LocalDate.of(months.year, 12, 31);
                List<Transaction>transactions = transactionRepository.findAllByMonthsAndDateBetween(month,startDate, endDate);
                log.info("Count of data record of transactions in getRewardsBasedOnMultipleMonths: {}", transactions.size());
                int totalPoints = 0;
                for (Transaction transaction : transactions) {
                    double amount = transaction.getAmount();
                    if (amount > 100) {
                        totalPoints += (int) ((amount - 100) * 2) + 50; // one point for over 100
                    } else {
                        totalPoints += (int) (amount - 50); // 1 point for each dollar between $50 to $100
                    }
                    log.info("month data inside for loop: {}", month);
                    monthList.add(month);
                    log.info("month List inside for loop: {}", monthList);
                }
//                rewardsDTO.rewards = totalPoints;
//                rewardsDTO.months = monthList;
                responseWrapper.rewards = totalPoints;
                responseWrapper.months = monthList;
            }
            return responseWrapper;
        }

    }

