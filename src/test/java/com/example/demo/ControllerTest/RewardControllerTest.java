package com.example.demo.ControllerTest;

import com.example.demo.ControllerImpl.RewardControllerImpl;
import com.example.demo.DTO.RewardsDTO;
import com.example.demo.ExceptionUtils.CustomerNotFoundException;
import com.example.demo.ExceptionUtils.InvalidMonthException;
import com.example.demo.Service.ServiceImpl.RewardServiceImpl;
import com.example.demo.Wrapper.MonthsWrapper;
import com.example.demo.Wrapper.ResponseWrapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        int year=2024;
        RewardsDTO mockReponse = new RewardsDTO();
        when(rewardService.calculateRewards(customerId,month,year)).thenReturn(mockReponse);
        ResponseEntity<RewardsDTO>response = rewardController.getRewards(customerId,month,year);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReponse, response.getBody());
        verify(rewardService).calculateRewards(customerId,month,year);
    }

    @Test
    public void testGetReward_InvalidCustomerId(){
        Long customerId = 999L;
        String month = "2023-10";
        int year=2024;
        RewardsDTO mockReponse = new RewardsDTO();
        when(rewardService.calculateRewards(customerId,month,year)).thenThrow(new CustomerNotFoundException());
        ResponseEntity<RewardsDTO>response = rewardController.getRewards(customerId,month,year);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(mockReponse, response.getBody());
        verify(rewardService).calculateRewards(customerId,month,year);
    }

    @Test
    public void testGetReward_InvalidMonth(){
        Long customerId = 999L;
        String month = "Invalid_month";
        int year = 2024;
        RewardsDTO mockReponse = new RewardsDTO();
        when(rewardService.calculateRewards(customerId,month,year)).thenThrow(new InvalidMonthException());
        ResponseEntity<RewardsDTO>response = rewardController.getRewards(customerId,month,year);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(mockReponse, response.getBody());
        verify(rewardService,never()).calculateRewards(any(),any(), Mockito.any());
    }

    @Test
    public void testGetRewardsBasedOnMultipleMonths(){
        MonthsWrapper monthsWrapper = new MonthsWrapper();
        ResponseWrapper mockReponse = new ResponseWrapper();
        when(rewardService.getRewardsBasedOnMultipleMonths(monthsWrapper)).thenReturn(mockReponse);
        ResponseEntity<ResponseWrapper>response = rewardController.getRewardsBasedOnMultipleMonths(monthsWrapper);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReponse, response.getBody());
        verify(rewardService).getRewardsBasedOnMultipleMonths(monthsWrapper);
    }
}
