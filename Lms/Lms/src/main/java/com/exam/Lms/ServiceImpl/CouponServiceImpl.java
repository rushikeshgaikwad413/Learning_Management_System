package com.exam.Lms.ServiceImpl;

import com.exam.Lms.Dto.CouponDto;
import com.exam.Lms.Entity.Coupon;
import com.exam.Lms.Exception.CouponAlreadyExistException;
import com.exam.Lms.Exception.CouponNotFoundException;
import com.exam.Lms.Repository.CouponRepository;
import com.exam.Lms.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepository couponRepository;


    @Override
    public String createCoupon(CouponDto couponDto) {
        Optional<Coupon> coupon = couponRepository.findByCode(couponDto.getCode());
        if (!coupon.isPresent()) {
            Coupon coupon1 = new Coupon(couponDto);
            couponRepository.save(coupon1);
            return "Coupon with code " + couponDto.getCode() + " created successfully";
        } else {
            throw new CouponAlreadyExistException("Coupon with code " + couponDto.getCode() + " already exists");
        }
    }


    @Override
    public Coupon findByCode(String code) {
        Optional<Coupon> byCode = couponRepository.findByCode(code);
        if (byCode.isEmpty()){
            throw new CouponNotFoundException("Coupon Not Found");
        }
        return byCode.get();
    }

    @Override
    public List<Coupon> findAllCoupons() {
        return null;
    }

    @Override
    public String deleteCouponByCode(String code) {
        List<Coupon> allByCode = couponRepository.findAllByCode(code);
        if (allByCode.isEmpty()) {
            throw new CouponNotFoundException("Coupon '" + code + "' not found");
        }
        couponRepository.deleteAll(allByCode);
        return "Coupon '" + code + "' deleted successfully";
    }


}