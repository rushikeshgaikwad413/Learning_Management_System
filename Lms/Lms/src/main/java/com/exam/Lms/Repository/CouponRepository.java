package com.exam.Lms.Repository;

import com.exam.Lms.Entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    public Optional<Coupon> findByCode(String code);

    List<Coupon> findAllByCode(String code);
}
