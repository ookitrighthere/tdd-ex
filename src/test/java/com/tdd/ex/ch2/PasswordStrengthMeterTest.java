package com.tdd.ex.ch2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;




//암호 검사기
//1. 길이가 8글자 이상
//2. 0부터 9사이의 숫자를 포함
//3. 대문자 포함

//모두 충족 시 암호는 강함
//2개의 규칙 충족 시 보통
//1개의 규칙 충족 시 약함


public class PasswordStrengthMeterTest {
	
	 private PasswordStrengthMeter meter = new PasswordStrengthMeter();
	 
	 private void assertStrength(String password, PasswordStrength expStr) {
		 PasswordStrength result = meter.meter(password);
		 assertEquals(expStr, result);
	 }
	/**
	 * 모든 조건 충족 
	 * 
	 */
	@Test
	void meetsAllCriteria_Then_String() { 
		//PasswordStrengthMeter meter = new PasswordStrengthMeter(); 중복제거 
		//PasswordStrength result = meter.meter("ab12!@AB");
		//assertEquals(PasswordStrength.STRONG, result);
		assertStrength("ab12!@AB", PasswordStrength.STRONG);
		
		//PasswordStrength result2 = meter.meter("abc11!ABC");
		//assertEquals(PasswordStrength.STRONG, result);
		assertStrength("abc11!ABC", PasswordStrength.STRONG);
	}

	/**
	 * 길이 7글자 이하 
	 * 
	 */
	@Test
	void meetsOtherCriteria_except_for_Length_Then_Normal() {
		//PasswordStrengthMeter meter = new PasswordStrengthMeter();
		//PasswordStrength result = meter.meter("ab12!A");
		//assertEquals(PasswordStrength.NORMAL, result);
		assertStrength("ab12!A", PasswordStrength.NORMAL);
	}
	
	/**
	 * 숫자 포함 x 
	 * 
	 */
	@Test
	void meetsOtherCriteria_except_for_number_Then_Normal() {
		//PasswordStrengthMeter meter = new PasswordStrengthMeter();
		//PasswordStrength result = meter.meter("Abc#!abqwe");
		//assertEquals(PasswordStrength.NORMAL,result);
		assertStrength("Abc#!abqwe", PasswordStrength.NORMAL);
	}
	
	/**
	 * null값
	 * 
	 */
	@Test
	void nullInput_Then_Invalid() {
		assertStrength(null, PasswordStrength.INVALID);
	}
	/**
	 * 빈 값 
	 */
	@Test
	void emptyInput_Then_Invalid() {
		assertStrength("", PasswordStrength.INVALID);
	}
	/**
	 * 대문자 포함 x
	 */
	@Test
	void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
		assertStrength("ab12!@df", PasswordStrength.NORMAL);
	}
	
	/**
	 * 길이 8글자이상 조건만 충족
	 */
	@Test
	void meetsOnlyLengthCriteria_Then_Weak() {
		assertStrength("abdefghi", PasswordStrength.WEAK);
	}
	
	/**
	 * 숫자 조건만 충족
	 */
	@Test
	void meetsOnlyNumCriteria_Then_Weak() {
		assertStrength("12345", PasswordStrength.WEAK);
	}
	
	/**
	 * 대문자 조건만 충족
	 */
	@Test
	void meetsOnlyUpperCriteria_Then_Weak() {
		assertStrength("ABZEF", PasswordStrength.WEAK);
	}
	@Test
	void meetsNoCriteria_Then_Weak() {
		assertStrength("abc", PasswordStrength.WEAK);
	}
}
