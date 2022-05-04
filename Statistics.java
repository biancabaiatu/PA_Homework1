import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Statistics {

	/**
	 * Counts the number of appearances of a char in a String
	 * @param s string to be questioned
	 * @param c char to be searched
	 * @return number of appearances
	 */
	public static int numberOfAppearances(String s, char c) {
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if (c == s.charAt(i)) {
				cnt++;
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(new File("statistics.in"));
		FileWriter fileWriter = new FileWriter("statistics.out");

		long n;
		int cnt_max = 0;
		List<String> words = new ArrayList<>();

		n = sc.nextInt();
		sc.nextLine();

		// read and memorize the list of words
		for (int i = 0; i < n; i++) {
			String word = sc.nextLine();
			words.add(word);
		}

		char character = 'a';

		// parse all the characters in the alphabet
		for (int i = 0; i < 26; i++) {
			int finalI = i;
			int cnt = 0;
			int ok = 0;

			// sort the list of words
			words.sort((o1, o2) -> (-1)
					* Integer.compare(2
							* numberOfAppearances(o1, (char) (character + finalI)) - o1.length(),
					2 * numberOfAppearances(o2, (char) (character + finalI)) - o2.length()));

			StringBuilder concatenate = new StringBuilder();

			// try to concatenate until number of appearances <= length / 2
			for (int j = 0; j < words.size() && ok == 0; j++) {
				concatenate.append(words.get(j));
				if (numberOfAppearances(String.valueOf(concatenate), (char) (character + finalI))
						> (concatenate.length() / 2)) {
					cnt++;
					if (cnt > cnt_max) {
						cnt_max = cnt;
					}
				} else {
					ok = 1;
				}
			}
		}

		// Verify whether there is a maximum value
		if (cnt_max == 0) {
			fileWriter.write(String.valueOf(-1));
		// if it exists print the maximum value
		} else {
			fileWriter.write(String.valueOf(cnt_max));
		}
		fileWriter.flush();
	}
}
