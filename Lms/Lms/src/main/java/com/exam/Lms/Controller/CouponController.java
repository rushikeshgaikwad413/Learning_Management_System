package com.exam.Lms.Controller;

import com.exam.Lms.Dto.CouponDto;
import com.exam.Lms.Entity.Coupon;
import com.exam.Lms.Exception.CouponAlreadyExistException;
import com.exam.Lms.Exception.CouponNotFoundException;
import com.exam.Lms.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Coupons")
public class CouponController {

    @Autowired
    CouponService couponService;

    @PostMapping("/CreateCoupon")
    public ResponseEntity<String> createCoupon (@RequestBody CouponDto couponDto){
        try {
            String coupon = couponService.createCoupon(couponDto);
            return ResponseEntity.status(HttpStatus.OK).body(coupon);
        } catch (CouponAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getCouponById")
    public ResponseEntity<?> getCouponById(@RequestParam String code){
        try {
            Coupon byCode = couponService.findByCode(code);
            return ResponseEntity.status(HttpStatus.OK).body(byCode);
        } catch (CouponNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/DeleteCouponByCode")
    public ResponseEntity<?> deleteCouponByCode(@RequestParam String code) {
        try {
            String string = couponService.deleteCouponByCode(code);
            return ResponseEntity.status(HttpStatus.OK).body(string);
        } catch (CouponNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
