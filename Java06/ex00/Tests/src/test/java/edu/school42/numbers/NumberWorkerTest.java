package edu.school42.numbers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

public class NumberWorkerTest {

	@ParameterizedTest
	@ValueSource(ints = {2, 11, 65357, 95311, 25233433})
	public void	isPrimeForPrimes(int n) {
		assertTrue(new NumberWorker().isPrime(n));
	}

	@ParameterizedTest
	@ValueSource(ints = {4, 10001, 214483646})
	public void	isPrimeForNotPrimes(int n) {
		assertFalse(new NumberWorker().isPrime(n));
	}

	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, -10001, -214483646})
	public void	isPrimeForIncorrectNumbers(int n) {
		assertThrows(IllegalNumberException.class, () -> {
			new NumberWorker().isPrime(n);
		});
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/data.csv")
	public void	checkDigitsSum(int n, int sum) {
		assertEquals(sum, new NumberWorker().digitsSum(n));
	}
}

