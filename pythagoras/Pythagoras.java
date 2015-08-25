package pythagoras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Pythagoras {

	public static void main(String[] args) {

		System.out.println(evalTask("О природе.txt"));

	}

	public static String evalTask(String filename) {
		ArrayList<String> a = readTask(filename);
		String result = "";
		for (String num : a) {
			result += evalSingle(num);
		}
		return result;
	}

	public static char evalSingle(String num) {
		int number = Integer.parseInt(num);
		int dv = number;
		while (!isDV(dv)) {
			dv++;
		}

		return (char) (dv - number);
	}

	public static boolean isDV(int num) {
		boolean result = true;
		ArrayList<Integer> numbers = new ArrayList<>();

		while (num > 0) {
			numbers.add(num % 10);
			num = num / 10;
		}
		int d1 = numbers.get(0);
		int d2 = -1;
		for (int n : numbers) {
			if (n != d1) {
				if (d2 == -1) {
					d2 = n;
				} else if (n != d2) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	public static ArrayList<String> readTask(String filename) {
		ArrayList<String> a = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String str = "";
			while ((str = br.readLine()) != null) {
				if (str.matches("^[0-9]{2,}$")) {
					a.add(str);
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
		}

		return a;
	}

}
