package com.worbun.user.service;

import com.worbun.user.model.Coupon;

import java.util.List;

public interface CouponService {

    public boolean validateCoupon(String couponCode);

    public Coupon createCoupon (Coupon coupon);

    public void deleteCoupon( Long couponId);

    public List<Coupon> getAllCoupons();
}
