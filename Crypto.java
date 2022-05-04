import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Crypto {

	/**
	 * Find number of times a string occurs as a subsequence in given string
	 * @param key the given string containing '?' characters
	 * @param substring to be searched for
	 * @param n length of the string
	 * @param l length of the substring
	 * @param distinct number of distinct, unique characters in the substring
	 * @return number of occurrences
	 */
	static long count(String key, String substring, int n, int l, long distinct) {
		long prevCount = 1L;
		long currCount = 1L;
		long[][] lookupMatrix = new long[n + 1][l + 1];

		// first line of the matrix is 0
		for (int i = 0; i <= l; ++i) {
			lookupMatrix[0][i] = 0L;
		}

		// first column of the matrix is 1
		for (int i = 0; i <= n; ++i) {
			lookupMatrix[i][0] = 1L;
		}

		// iterate and complete the matrix
		for (int i = 1; i <= n; i++) {
			// current character in string is '?'
			if (key.charAt(i - 1) == '?') {
				prevCount = currCount;
				currCount = (currCount % 1000000007
						* distinct % 1000000007)
						% 1000000007;
			}
			for (int j = 1; j <= l; j++) {
				// the last characters are the same
				if (key.charAt(i - 1) == substring.charAt(j - 1)) {
					// complete the first column
					if (j == 1) {
						lookupMatrix[i][j] = currCount % 1000000007;
					} else {
						lookupMatrix[i][j] = lookupMatrix[i - 1][j - 1] % 1000000007;
					}
					lookupMatrix[i][j] = (lookupMatrix[i][j] % 1000000007
							+ lookupMatrix[i - 1][j] % 1000000007)
							% 1000000007;
				// current character is '?'
				} else if (key.charAt(i - 1) == '?') {
					// complete the first column
					if (j == 1) {
						lookupMatrix[i][j] = prevCount % 1000000007;
					} else {
						lookupMatrix[i][j] = lookupMatrix[i - 1][j - 1] % 1000000007;
					}
					lookupMatrix[i][j] = (lookupMatrix[i][j] % 1000000007
							+ ((lookupMatrix[i - 1][j] % 1000000007)
							* (distinct % 1000000007)) % 1000000007)
							% 1000000007;
				// characters are different
				} else {
					lookupMatrix[i][j] = lookupMatrix[i - 1][j] % 1000000007;
				}
			}
		}
		return lookupMatrix[n][l] % 1000000007;
	}

	public static void main(String[] args) {

		int n;
		int l;
		String key, substring;

		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(
					"crypto.in")));
			n = sc.nextInt();
			l = sc.nextInt();

			sc.nextLine();

			key = sc.nextLine();
			substring = sc.nextLine();

			sc.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		long distinct = substring.chars().distinct().count();

		long number = count(key, substring, n, l, distinct);

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					"crypto.out")));
			pw.printf("%d\n", number);
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
