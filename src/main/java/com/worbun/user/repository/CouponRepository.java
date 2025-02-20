package com.worbun.user.repository;

import com.worbun.user.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository  extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCouponCode(String couponCode);
}
