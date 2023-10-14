package com.exam.Lms.Entity;

import com.exam.Lms.Dto.CouponDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter

public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;
    private double discountPercentage;
    private LocalDate startdate;
    private LocalDate expirationDate;

    public Coupon() {
    }

    public Coupon(CouponDto couponDto) {
        this.code= couponDto.getCode();
        this.discountPercentage=couponDto.getDiscountPercentage();
        this.startdate=couponDto.getStartdate();
        this.expirationDate=couponDto.getExpirationDate();
        this.id= couponDto.getId();
    }
}
