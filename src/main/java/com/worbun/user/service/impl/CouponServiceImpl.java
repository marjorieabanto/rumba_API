package com.worbun.user.service.impl;

import com.worbun.user.model.Coupon;
import com.worbun.user.repository.CouponRepository;
import com.worbun.user.service.CouponService;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository  couponRepository;
    @Override
    public boolean validateCoupon(String couponCode) {

        Optional<Coupon> coupon = couponRepository.findByCouponCode(couponCode);

        if (coupon.isPresent() && coupon.get().isValid() && coupon.get().getExpirationDate().isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }



    @Override
    public void deleteCoupon(Long coupon) {

        couponRepository.deleteById(coupon);

    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}
