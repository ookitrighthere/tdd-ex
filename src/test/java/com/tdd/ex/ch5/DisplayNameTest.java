package com.tdd.ex.ch5;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("@DisplayName Test")
public class DisplayNameTest {
	
	@DisplayName("값 같은지 비교")
	@Test
	void assertEqaulsMethod() {
		LocalDate dateTime1 = LocalDate.now();
		LocalDate dateTime2 = LocalDate.now();
		assertEquals(dateTime1, dateTime2);
	}
	
	@Disabled
	@Test
	void failMethod() {
		try {
			AuthService authService = new AuthService();
			authService.authenticate(null, null);
		} catch(IllegalArgumentException e) {
			
		}
	}
	@DisplayName("익셉션 발생 여부 테스트")
	@Test
	void assertThrowsTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			AuthService authService = new AuthService();
			authService.authenticate(null, null);
		});
	}
	
	@Test
	void assertAllTest() {
		assertAll (
				() -> assertEquals(3, 5 / 2),
				() -> assertEquals(4, 2 * 2),
				() -> assertEquals(6, 11 / 2)
				);
	}
}
