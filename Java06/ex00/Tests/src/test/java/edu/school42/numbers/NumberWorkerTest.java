package edu.school42.numbers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.ValueSource;

public class NumberWorkerTest {

	@ParameterizedTest
	@ValueSource(ints = {2, 11, 65357, 95311})
	public void	isPrimeForPrimes(int n) {
		assertTrue(new NumberWorker().isPrime(n));
	}
}

