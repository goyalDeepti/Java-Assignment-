package com.example.demo.Service;


import com.example.demo.DTO.RewardsDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IRewardService {
    public RewardsDTO calculateRewards(Long customerId, String month);
    public RewardsDTO getRewardsBasedOnMultipleMonths( List<String>months);
    }
