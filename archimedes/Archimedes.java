package archimedes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archimedes {

	public static void main(String[] args) {
		
		System.out.println(evalTask("Измерения круга.txt"));
	}

	public static String evalTask(String filename) {
		ArrayList<Integer> a = readTask(filename);
		String result = "";
		for(int sweets:a){
			result += evalSingle(sweets);
		}
		return result;
	}
	
	public static char evalSingle(int sweets){
		int firstCh = 0;
		int newCh = 1;

		for (; sweets > (firstCh + newCh);) {
			sweets -= (firstCh + newCh);
			firstCh = (firstCh + newCh);
			newCh++;
		}

		int result = (sweets - firstCh > 0) ? sweets : firstCh;
		return (char) (result);
	}

	public static ArrayList<Integer> readTask(String filename) {
		ArrayList<Integer> a = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String str = "";
			while ((str = br.readLine()) != null) {
				if (str.matches("^[0-9]{3}$")) {
					a.add(Integer.parseInt(str));
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
		}
		
		return a;
	}

}
