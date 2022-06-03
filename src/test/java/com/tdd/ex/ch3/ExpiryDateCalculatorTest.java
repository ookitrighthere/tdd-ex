package com.tdd.ex.ch3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

//서비스 만료일 계산

//서비스를 사용하려면 매달 1만원을 선불로 납부한다.
//납부일 기준으로 한달 뒤가 서비스 만료일이 된다.
//2개월 이상 요금을 납부할 수 있다.
//10만원을 납부하면 서비스를 1년 제공한다

/*
 * 구현하기 쉬운 경우에서 어려운 경우 순
예외적인 경우에서 정상적인 경우 순
*/




public class ExpiryDateCalculatorTest {
	
	@Test //쉬운경우부터 테스트 
	void 만원_납부하면_한달뒤_만료일이_됨() { 
//		LocalDate billingDate  = LocalDate.of(2022, 6, 3);
//		int payAmount = 10_000;	
//		ExpiryDateCalculator cal = new ExpiryDateCalculator();
//		LocalDate expiryDate = cal.calculateDxpiryDate(billingDate, payAmount);
//		assertEquals(LocalDate.of(2022, 7, 3), expiryDate);
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2022, 6, 3))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2022, 7, 3));
		
//		LocalDate billingDate2 = LocalDate.of(2022, 1, 3);
//		int payAmount2 = 10_000;
//		ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
//		LocalDate expiryDate2 = cal2.calculateDxpiryDate(billingDate2, payAmount2);	
//		assertEquals(LocalDate.of(2022, 2, 3), expiryDate2);
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2022, 1, 3))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2022, 2, 3));
	}
	
	@Test //예외 상황 테스트
	void 납부일과_한달_뒤_일자가_같지않음() { 
//		assertExpiryDate(LocalDate.of(2022, 1, 31), 10_000,
//								   LocalDate.of(2022, 2, 28));
//		assertExpiryDate(LocalDate.of(2022, 5, 31), 10_000,
//								   LocalDate.of(2022, 6, 30));
//		assertExpiryDate(LocalDate.of(2024, 1, 31), 10_000,
//								   LocalDate.of(2024, 2, 29));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2022, 1, 31))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2022, 2, 28));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2022, 5, 31))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2022, 6, 30));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2024, 1, 31))
						.payAmount(10_000)
						.build(),
				LocalDate.of(2024, 2, 29));		
				
	}
	
//	private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
//		ExpiryDateCalculator cal = new ExpiryDateCalculator();
//		LocalDate realExpiryDate = cal.calculateDxpiryDate(billingDate, payAmount);
//		assertEquals(expectedExpiryDate, realExpiryDate);
//	} 
//파라미터가 3개로 늘어나서 객체로 바꿈
	
	private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
		ExpiryDateCalculator cal = new ExpiryDateCalculator();
		LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
		assertEquals(expectedExpiryDate, realExpiryDate);
	}
}
