package com.example.demo.Service;


import com.example.demo.DTO.RewardsDTO;
import com.example.demo.Wrapper.MonthsWrapper;
import com.example.demo.Wrapper.ResponseWrapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface IRewardService {

    /**
     * calculateRewards() will calculate reward points for each customer per month.
     * @param customerId
     * @param month
     * @return
     */
    public RewardsDTO calculateRewards(Long customerId, String month, int year);

    /**
     * getRewardsBasedOnMultipleMonths() will calculate total reward points for given List of months.
     * @param months
     * @return
     */
    public ResponseWrapper getRewardsBasedOnMultipleMonths(MonthsWrapper months);
    }
