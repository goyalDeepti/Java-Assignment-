package com.example.demo.ControllerImpl;

import com.example.demo.IService.IRewardService;
import com.example.demo.Service.RewardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardControllerImpl {

    @Autowired
    IRewardService rewardService;

    @GetMapping("/getRewards/{customerId}/{month}")
    public ResponseEntity<Map<String, Integer>> getRewards(@PathVariable  Long customerId, @PathVariable  String month){
        Map<String,Integer>response= new HashMap<>();
        try{
           response=  rewardService.calculateRewards(customerId,month);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }catch(Exception e){
            response.put("An error occurred ",0);
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
