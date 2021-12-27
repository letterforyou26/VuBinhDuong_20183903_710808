package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidateRushOrderInformationTest {

	private PlaceRushOrderController placeRushOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
	@CsvSource(value = { "Hà Nội, true",
						 "Thái Bình, false",
						 "Hà Nội 1, false",
						 "null, false"},
			   nullValues = {"null"})

	void test_provinceSupportRushOrder(String province, boolean expected) {
		// when
		boolean isValid = placeRushOrderController.checkProvinceSupportRushOrder(province);
		
		// then
		assertEquals(expected, isValid);
	}

	@ParameterizedTest
	@CsvSource({
		"12, true",
		"13, false",
		"-1, false",
	})

	void test_mediaSupportRushOrder(int mediaID, boolean expected) {
		// when
		boolean isValid = placeRushOrderController.checkMediaSupportRushOrder(mediaID);
		
		// then
		assertEquals(expected, isValid);
	}
	
	@ParameterizedTest
	@CsvSource(value = { "Some info test, true",
						 "null, false",},
			   nullValues = {"null"})

	void test_rushOrderInfo(String info, boolean expected) {
		// when
		boolean isValid = placeRushOrderController.validateRushOrderInfo(info);
		
		// then
		assertEquals(expected, isValid);
	}
	
	@ParameterizedTest
	@CsvSource(value = { "Some info test, true",
						 "null, false",},
			   nullValues = {"null"})

	void test_rushOrderInstruction(String ins, boolean expected) {
		// when
		boolean isValid = placeRushOrderController.validateRushOrderInstruction(ins);
		
		// then
		assertEquals(expected, isValid);
	}
	
	@ParameterizedTest
	@CsvSource(value = { "11-12-2021 09:30, true",
						 "11-13-2021 09:30, false",
						 "2021-12-11 09:30, false",
						 "null, false",},
			   nullValues = {"null"})

	void test_timeReceive(String time, boolean expected) {
		// when
		boolean isValid = placeRushOrderController.validateReceiveTime(time);
		
		// then
		assertEquals(expected, isValid);
	}
}
