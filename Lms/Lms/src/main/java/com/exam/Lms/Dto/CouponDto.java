package com.exam.Lms.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {

    private int id;

    private String code;
    private double discountPercentage;
    private LocalDate startdate;
    private LocalDate expirationDate;
}
