package com.example.demo.Wrapper;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MonthsWrapper {

    public List<String> months;
    public int year;
}
