package com.tdd.ex.ch3;

import java.time.LocalDate;

public class ExpiryDateCalculator {
//	public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
//		return billingDate.plusMonths(1);//	}
	public LocalDate calculateExpiryDate(PayData payData) {
		int addedMonths = payData.getPayAmount() / 10_000;
		if(payData.getFirstBillingDate() != null) {
			LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
			if(payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
				return candidateExp.withDayOfMonth(
						payData.getFirstBillingDate().getDayOfMonth()); //첫 납부일의 일자를 후보 만료일의 일자로 사용
			}
//			if(payData.getFirstBillingDate().equals(LocalDate.of(2019, 1, 31))) {
//				return LocalDate.of(2019, 3, 31);
//			}
		}
		return payData.getBillingDate().plusMonths(addedMonths);
	}
}
