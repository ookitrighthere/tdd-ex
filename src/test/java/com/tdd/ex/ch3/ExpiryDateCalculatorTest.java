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
	@Test
	void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
		//첫 납부일 1/31, 2/28에 1만원을 납부하면 다음 만료일은 3/31이다.
		PayData payData = PayData.builder()
								.firstBillingDate(LocalDate.of(2019, 1, 31))
								.billingDate(LocalDate.of(2019, 2, 28))
								.payAmount(10_000)
								.build();
		assertExpiryDate(payData, LocalDate.of(2019, 3, 31));
		
		//새로운 테스트 사례 추가( -> 구현 일반화)
		//첫  납부일 1-30,  2-28에 1만원을 납부하면 다음 만료일은 3/30 이다.
		PayData payData2 = PayData.builder()
								.firstBillingDate(LocalDate.of(2019, 1, 30))
								.billingDate(LocalDate.of(2019, 2, 28))
								.payAmount(10_000)
								.build();
		assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));
		
		//첫 납부일 5/31, 6/30에 1만원을 납부하면 다음 만료일은 7/31 이다.
		PayData payData3 = PayData.builder()
								.firstBillingDate(LocalDate.of(2019, 5, 31))
								.billingDate(LocalDate.of(2019, 6, 30))
								.payAmount(10_000)
								.build();
		assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
	}
	
	//다음 쉬운 테스트 추가
	//2만원을 지불하면 만료일이 두달 뒤
	//3만원을 지불하면 만료일이 석달 뒤
	@Test
	void 이만원_이상_납부하면_비례해서_만료일_계산() {
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 3, 1))
						.payAmount(20_000)
						.build(),
			   LocalDate.of(2019, 5, 1));
		assertExpiryDate(
				PayData.builder()
						.billingDate(LocalDate.of(2019, 3, 1))
						.payAmount(30_000)
						.build(),
				LocalDate.of(2019, 6, 1));
	}
	
	//예외 상황 테스트 추가
	//첫 납부일이 1/31이고 만료되는 2/28에 2만원을 납부하면 다음 만료일은 4/30이다.
	@Test
	void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
		assertExpiryDate(
				PayData.builder()
						.firstBillingDate(LocalDate.of(2020, 1, 31))
						.billingDate(LocalDate.of(2020, 2, 28))
						.payAmount(20_000)
						.build(),
				LocalDate.of(2020, 4, 30));
		//첫 납부일 3/31 만료되는 4/30에 3만원을 납부하면 다음 만료일은 7/31일이다.
		assertExpiryDate(
				PayData.builder()
						.firstBillingDate(LocalDate.of(2019, 3, 31))
						.billingDate(LocalDate.of(2019, 4, 30))
						.payAmount(30_000)
						.build(),
				LocalDate.of(2019, 7, 31));
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
