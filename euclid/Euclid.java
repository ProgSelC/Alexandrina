package euclid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Euclid {
	public static void main(String[] args) {
		System.out.println(evalTask("Начала.txt"));
	}

	public static String evalTask(String filename) {
		ArrayList<Character> a = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String str = "";
			boolean firstMatch = false;
			int num = 0;
			while ((str = br.readLine()) != null) {
				if (str.matches("\\([0-9]+[,]\\s[0-9]+\\).*\\([0-9]+[,]\\s[0-9]+\\)")) {
					firstMatch = true;
					num *=10;
					num += evalSingle(str);
				} else if(str.matches("^\\s*$")&& firstMatch){
					a.add((char)num);
					num = 0;
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
		}
		String result = "";
		for(char c:a){
			result += c;
		}
		return result;
	}

	public static int evalSingle(String in) {
		ArrayList<Segment> a = new ArrayList<>();
		String[] parts = in.split("[()]");
		for (String s : parts) {
			if (!s.equals("")) {
				String[] num = s.split("[, ]+");
				if (Integer.parseInt(num[0]) < Integer.parseInt(num[1])) {
					a.add(new Segment(Integer.parseInt(num[0]), Integer
							.parseInt(num[1])));
				} else {
					a.add(new Segment(Integer.parseInt(num[1]), Integer
							.parseInt(num[0])));
				}
			}
		}
		int squares = 0;
		int[] apex1x = { 1, 2, 3, 5, 6, 7, 9, 10, 11 };
		for (int i : apex1x) {
			if (a.containsAll(square1x(i))) {
				squares++;
			}
		}

		int[] apex2x = { 1, 2, 5, 6 };
		for (int i : apex2x) {
			if (a.containsAll(square2x(i))) {
				squares++;
			}
		}

		if (a.containsAll(square3x(1))) {
			squares++;
		}
		return squares;
	}

	public static ArrayList<Segment> square1x(int i) {
		ArrayList<Segment> sq = new ArrayList<>();
		sq.add(new Segment(i, i + 1));
		sq.add(new Segment(i, i + 4));
		sq.add(new Segment(i + 1, i + 5));
		sq.add(new Segment(i + 4, i + 5));
		return sq;
	}

	public static ArrayList<Segment> square2x(int i) {
		ArrayList<Segment> sq = new ArrayList<>();
		sq.add(new Segment(i, i + 1));
		sq.add(new Segment(i + 1, i + 2));
		sq.add(new Segment(i, i + 4));
		sq.add(new Segment(i + 4, i + 8));
		sq.add(new Segment(i + 8, i + 9));
		sq.add(new Segment(i + 9, i + 10));
		sq.add(new Segment(i + 2, i + 6));
		sq.add(new Segment(i + 6, i + 10));
		return sq;
	}

	public static ArrayList<Segment> square3x(int i) {
		ArrayList<Segment> sq = new ArrayList<>();
		sq.add(new Segment(i, i + 1));
		sq.add(new Segment(i + 1, i + 2));
		sq.add(new Segment(i + 2, i + 3));
		sq.add(new Segment(i, i + 4));
		sq.add(new Segment(i + 4, i + 8));
		sq.add(new Segment(i + 8, i + 12));
		sq.add(new Segment(i + 12, i + 13));
		sq.add(new Segment(i + 13, i + 14));
		sq.add(new Segment(i + 14, i + 15));
		sq.add(new Segment(i + 3, i + 7));
		sq.add(new Segment(i + 7, i + 11));
		sq.add(new Segment(i + 11, i + 15));
		return sq;
	}

}

class Segment {
	private int a;
	private int b;

	public Segment(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "(" + a + "," + b + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segment other = (Segment) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		return true;
	}
}
