package com.example.demo.IControlller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public interface IRewardController {

    @GetMapping("/{customerId}/{month}")
    public ResponseEntity<Map<String,Integer>> getRewards(@PathVariable Long customerId, @PathVariable String month);
}
