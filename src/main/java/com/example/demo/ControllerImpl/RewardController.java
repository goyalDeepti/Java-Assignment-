package com.example.demo.ControllerImpl;

import com.example.demo.Model.Transaction;
import com.example.demo.Service.RewardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

public class RewardController {

    @Autowired
    public RewardServiceImpl rewardService;

    public ResponseEntity<Map<String, Integer>> getRewards(@PathVariable Long customerId, @PathVariable String month){
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
