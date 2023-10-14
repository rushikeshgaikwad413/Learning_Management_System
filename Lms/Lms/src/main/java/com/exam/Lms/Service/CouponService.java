package com.exam.Lms.Service;

import com.exam.Lms.Dto.CouponDto;
import com.exam.Lms.Entity.Coupon;

import java.util.List;

public interface CouponService {
    public String createCoupon(CouponDto couponDto);
    Coupon findByCode(String code);
    List<Coupon> findAllCoupons();

    String deleteCouponByCode(String code);
}
