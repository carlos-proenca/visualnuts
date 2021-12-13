package pt.com.visualnuts.number;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NumberVerifierServiceTest {

	@Autowired
	private NumberVerifierService service;

	@Test
	public void shouldPrintVisual() {
		List<String> numberList = service.getVisualNutsListFromNumber(3);
		assertTrue(numberList != null);
		assertTrue(numberList.contains("Visual "));
	}

	@Test
	public void shouldPrintNuts() {
		List<String> numberList = service.getVisualNutsListFromNumber(5);
		assertTrue(numberList != null);
		assertTrue(numberList.contains("Nuts"));
	}

	@Test
	public void shouldPrintVisualNuts() {
		List<String> numberList = service.getVisualNutsListFromNumber(15);
		assertTrue(numberList != null);
		assertTrue(numberList.contains("Visual Nuts"));
	}

	@Test
	public void shouldGetExceptionForWrongParam() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			service.getVisualNutsListFromNumber(null);
		});
		assertEquals(exception.getMessage(), "Please provide a valid number");
	}
}
