package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RewardsDTO {

    public Map<String,Integer> monthsWithRewards=new HashMap<>();
//    public Integer rewards;
    public Long custId;
}
