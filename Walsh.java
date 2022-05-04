import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Walsh {

	/**
	 * Counts the number of inversions of the element at (x, y) position
	 * @param inversion number of inversions
	 * @param x coordinate in matrix
	 * @param y coordinate in matrix
	 * @param n dimension of the current matrix
	 * @return number of inversions
	 */
	public static int getInversion(int inversion, long x, long y, long n) {
		/*
		 * If the dimension of the matrix is one return inversions number
		 */
		if (n == 1) {
			return inversion;
		} else {
			// element in the first quadrant
			if (x <= (n / 2) && y <= (n / 2)) {
				return getInversion(inversion, x, y, n / 2);
			// element in the third quadrant
			} else if (x <= (n / 2) && y > (n / 2)) {
				return getInversion(inversion, x, y - n / 2, n / 2);
			// element in the second quadrant
			} else if (x > (n / 2) && y <= (n / 2)) {
				return getInversion(inversion, x - n / 2, y, n / 2);
			} else if (x > (n / 2) && y > (n / 2)) {
				return getInversion(inversion + 1, x - n / 2, y - n / 2, n / 2);
			}
		}
		return inversion;
	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(new File("walsh.in"));
		FileWriter fileWriter = new FileWriter(new File("walsh.out"));

		long n, k, x, y;

		// get dimension of the matrix
		n = sc.nextInt();
		k = sc.nextInt();

		for (int i = 0; i < k; i++) {
			// read the coordinates
			x = sc.nextInt();
			y = sc.nextInt();

			// get number of inversions for current coordinates
			int inv = getInversion(0, x, y, n);
			if (inv % 2 == 0) {
				fileWriter.write('0');
				fileWriter.write('\n');
			} else {
				fileWriter.write('1');
				fileWriter.write('\n');
			}
		}
		// close reading and writing files
		sc.close();
		fileWriter.flush();
	}
}
