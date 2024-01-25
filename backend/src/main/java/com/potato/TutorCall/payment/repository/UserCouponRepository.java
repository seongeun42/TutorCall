package com.potato.TutorCall.payment.repository;

import com.potato.TutorCall.payment.domain.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {}
