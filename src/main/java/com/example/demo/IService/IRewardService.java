package com.example.demo.IService;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface IRewardService {
    public Map<String,Integer> calculateRewards(Long customerId, String month);

    }
