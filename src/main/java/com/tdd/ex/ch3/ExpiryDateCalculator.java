package com.tdd.ex.ch3;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
//	public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
//		return billingDate.plusMonths(1);//	}
	public LocalDate calculateExpiryDate(PayData payData) {
		int addedMonths = payData.getPayAmount() == 100_000? 12 : payData.getPayAmount() / 10_000;
		if(payData.getFirstBillingDate() != null) {
			return expiryDateUsingFirstBillingDate(payData, addedMonths);
		} else {
			return payData.getBillingDate().plusMonths(addedMonths);
		}
	}
	
	private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
		LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths); //예상만료일
		final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();  //첫 납부일의 일자 
		if(dayOfFirstBilling !=candidateExp.getDayOfMonth()) {
			final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth(); //예상만료일이 속한 월이 마지막 일자
			if(dayLenOfCandiMon < dayOfFirstBilling) {
				return candidateExp.withDayOfMonth(dayLenOfCandiMon);
			}
			return candidateExp.withDayOfMonth(dayOfFirstBilling);
		} else {
			return candidateExp;
		}
	}
}
