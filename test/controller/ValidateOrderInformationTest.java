package controller;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidateOrderInformationTest {
	
	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"0123456789, true",
		"01234, false",
		"012a345678, false",
		"1234567890, false"
	})
	
	void test_phoneNumber(String phone, boolean expected) {
		// when
		boolean isValid = placeOrderController.validatePhoneNumber(phone);
		
		// then
		assertEquals(expected, isValid);
	}
	
	@ParameterizedTest
	@CsvSource({
		"John Wick, true",
		"Alex Va5 Dice, false",
		"null, false",
		"a, false"
	})
	
	void test_name(String name, boolean expected) {
		// when
		boolean isValid = placeOrderController.validateName(name);
		
		// then
		assertEquals(expected, isValid);
	}

	@ParameterizedTest
	@CsvSource({
		"Ha Noi, true",
		"HN, false",
		"null, false",
		"378 Le Duan, true",
		"@Ha Noi, false"
	})
	
	void test_address(String address, boolean expected) {
		// when
		boolean isValid = placeOrderController.validateAddress(address);
		
		// then
		assertEquals(expected, isValid);
	}
}
