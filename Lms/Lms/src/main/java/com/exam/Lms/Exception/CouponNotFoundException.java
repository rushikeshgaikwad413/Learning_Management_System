package com.exam.Lms.Exception;

public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(String s)
    {
        super(s);
    }
}
