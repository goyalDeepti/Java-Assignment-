package com.example.demo.ControllerImpl;

import com.example.demo.IService.IRewardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
@Slf4j
public class RewardControllerImpl {

    private static final Logger log = LoggerFactory.getLogger(RewardControllerImpl.class);
    @Autowired
    IRewardService rewardService;

    @GetMapping("/getRewards/{customerId}/{month}")
    public Map<String, Integer> getRewards(@PathVariable Long customerId, @PathVariable String month) {
        Map<String, Integer> response = new HashMap<>();
            log.info("Inside @Class RewardControllerImpl @Method getRewards:{}", customerId);
            return rewardService.calculateRewards(customerId, month);
        }
    }
