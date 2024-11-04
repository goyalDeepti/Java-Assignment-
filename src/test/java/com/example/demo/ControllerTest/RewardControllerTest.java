package com.example.demo.ControllerTest;

import com.example.demo.ControllerImpl.RewardControllerImpl;
import com.example.demo.DTO.RewardsDTO;
import com.example.demo.ExceptionUtils.CustomerNotFoundException;
import com.example.demo.ExceptionUtils.InvalidMonthException;
import com.example.demo.Service.ServiceImpl.RewardServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class RewardControllerTest {

    @InjectMocks
    private RewardControllerImpl rewardController;

    @Mock
    private RewardServiceImpl rewardService;

    @Test
    public void testGetReward_ValidCustomerIdAndMonth(){
        Long customerId = 1L;
        String month = "2023-01";
        RewardsDTO mockReponse = new RewardsDTO();
        when(rewardService.calculateRewards(customerId,month)).thenReturn(mockReponse);
        ResponseEntity<RewardsDTO>response = rewardController.getRewards(customerId,month);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReponse, response.getBody());
        verify(rewardService).calculateRewards(customerId,month);
    }

    @Test
    public void testGetReward_InvalidCustomerId(){
        Long customerId = 999L;
        String month = "2023-10";
        RewardsDTO mockReponse = new RewardsDTO();
        when(rewardService.calculateRewards(customerId,month)).thenThrow(new CustomerNotFoundException());
        ResponseEntity<RewardsDTO>response = rewardController.getRewards(customerId,month);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(mockReponse, response.getBody());
        verify(rewardService).calculateRewards(customerId,month);
    }

    @Test
    public void testGetReward_InvalidMonth(){
        Long customerId = 999L;
        String month = "Invalid_month";
        RewardsDTO mockReponse = new RewardsDTO();
        when(rewardService.calculateRewards(customerId,month)).thenThrow(new InvalidMonthException());
        ResponseEntity<RewardsDTO>response = rewardController.getRewards(customerId,month);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(mockReponse, response.getBody());
        verify(rewardService,never()).calculateRewards(any(),any());
    }

    @Test
    public void testGetRewardsBasedOnMultipleMonths(){
        List<String>monthList = new ArrayList<>();
        monthList.add("2023-01");
        monthList.add("2023-09");
        RewardsDTO mockReponse = new RewardsDTO();
        when(rewardService.getRewardsBasedOnMultipleMonths(monthList)).thenReturn(mockReponse);
        ResponseEntity<RewardsDTO>response = rewardController.getRewardsBasedOnMultipleMonths(monthList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReponse, response.getBody());
        verify(rewardService).getRewardsBasedOnMultipleMonths(monthList);
    }
}
