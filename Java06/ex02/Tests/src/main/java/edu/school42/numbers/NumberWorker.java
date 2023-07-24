package edu.school42.numbers;

public class NumberWorker {
	public	NumberWorker() {}

	public boolean	isPrime(int number) {
		if (number <= 1) {
			throw new IllegalNumberException("IllegalNumberException");
		}
		if (number % 2 == 0) {
			return (number == 2);
		}
		for (int i = 3; i <= number / i; i += 2) {
			if (number % i == 0) {
				return (false);
			}
		}
		return (true);
	}

	public int	digitsSum(int number) {
		int	out = 0;

		while (number != 0) {
			out += number % 10;
			number /= 10;
		}
		return ((out >= 0) ? out : -out);
	}
}
