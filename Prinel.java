import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.Scanner;

public class Prinel {
	/**
	 * Calculates the divisors of a number and completes the operations' array
	 * @param n number to find divisors for
	 * @param maxTarget the global maximum to be searched
	 * @param operations array with total number of operations
	 */
	public static void calculateDivisors(int n, int maxTarget, int[] operations) {
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if ((n % i == 0) && (((n + i) <= maxTarget) || (((n / i) + n) <= maxTarget))) {
				operations[n + i] = min(operations[n + i], 1 + operations[n]);
				if ((n / i) != i) {
					if (((n / i) + n) <= maxTarget) {
						operations[n + n / i] = min(operations[n + n / i], 1 + operations[n]);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		int n;
		int k;
		int maxTarget = 0;
		int[] operations = new int[100000];
		int[] points = new int[10000];
		int[] targets = new int[10000];

		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(
					"prinel.in")));
			n = sc.nextInt();
			k = sc.nextInt();
			// read the array of targets and find the maximum target
			for (int i = 1; i <= n; i++) {
				int target = sc.nextInt();
				targets[i] = target;
				if (target > maxTarget) {
					maxTarget = target;
				}
			}
			// read the array of points
			for (int i = 1; i <= n; i++) {
				int point = sc.nextInt();
				points[i] = point;
			}

			sc.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// initialize the array with an "infinite" value
		for (int i = 1; i <= maxTarget; i++) {
			operations[i] = Integer.MAX_VALUE;
		}

		// base case
		operations[1] = 0;
		// fill out the rest of the array
		for (int i = 1; i <= maxTarget; i++) {
			calculateDivisors(i, maxTarget, operations);
		}

		// 0-1 Knapsack problem
		int []dp = new int[k + 1];

		for (int i = 1; i <= n; i++) {
			for (int cap = k; cap >= 0; cap--) {
				if (operations[targets[i]] <= cap) {
					dp[cap] = max(dp[cap], dp[cap - operations[targets[i]]] + points[i]);
				}
			}
		}

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					"prinel.out")));
			pw.printf("%d", dp[k]);
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
