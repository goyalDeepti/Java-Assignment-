package com.example.demo.ControllerImpl;

import com.example.demo.DTO.RewardsDTO;
import com.example.demo.Service.IRewardService;
import com.example.demo.Wrapper.MonthsWrapper;
import com.example.demo.Wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
@Slf4j
public class RewardControllerImpl {

    private static final Logger log = LoggerFactory.getLogger(RewardControllerImpl.class);
    @Autowired
    IRewardService rewardService;

    /**
     * calculateRewards() will calculate reward points for each customer as per month.
     * @param customerId
     * @param month
     * @return
     */
    @GetMapping("/getRewards/{customerId}/{month}/{year}")
    public ResponseEntity<RewardsDTO> getRewards(@PathVariable Long customerId, @PathVariable String month, @PathVariable Integer year) {
            log.info("Inside @Class RewardControllerImpl @Method getRewards customerId:{} month:{}", customerId, month);
            return ResponseEntity.ok(rewardService.calculateRewards(customerId, month, year));
        }

    /**
     * getRewardsBasedOnMultipleMonths() will calculate total reward points for given List of months.
     * @param months
     * @return
     */
    @GetMapping("/getRewardsForMultipleMonths")
    public ResponseEntity<ResponseWrapper> getRewardsBasedOnMultipleMonths(@RequestBody MonthsWrapper months){
        log.info("Inside @Class RewardControllerImpl @Method getRewards size of monthList:{}", months);
        return ResponseEntity.ok(rewardService.getRewardsBasedOnMultipleMonths(months));
    }


    }
