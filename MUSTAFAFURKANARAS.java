import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class systemproje1 {
	//MUSTAFA FURKAN ARAS 150116019
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		File file = new File("C:\\Users\\furkan\\Desktop\\input.txt");

		// TAKING STRING********************************

		BufferedReader reader = new BufferedReader(new FileReader(file));

		ArrayList<String> list = new ArrayList<>();
		String hepsi = "";
		if (file.exists()) {

			String str = "";
			while ((str = reader.readLine()) != null)
				list.add(str);

			StringBuffer bufferedStr = new StringBuffer();
			for (String var : list)
				bufferedStr.append(var);

			reader.close();
			hepsi = bufferedStr.toString().trim();

		} else {
			System.out.println("okunmadı");
		}

		reader.close();
		String[] bytes = hepsi.split(" "); // all bytes
		for (String string : bytes) {
			System.out.print(string + " ");
		}
		System.out.println();

		System.out.println("enter the number of the bytes");
		
		int bytesayisi = input.nextInt();

		System.out.println("which data type  1-unsigned integer  2-signed integer  3-floating point");
		
		int veritipi = input.nextInt();

		System.out.println("Choose order  1-big indian 2-littlee Endian ");
		
		int siralama = input.nextInt();

		if (veritipi == 1) {// taking unsigned integer 
			int tekrar = 0;
			while (true) {
				if (tekrar == bytes.length / bytesayisi)
					break;
				String binaryString = birlestirici(bytes, bytesayisi, siralama);
				int[] binary = hexatobinary(binaryString);

				int decimalNumber = binarytodec(binary);
				System.out.println(decimalNumber);
				for (int i = 0; i < bytes.length - bytesayisi; i++)
					bytes[i] = bytes[i + bytesayisi];

				tekrar++;
			}

		} else if (veritipi == 2) {// taking signed integer 
			int tekrar = 0;
			while (true) {
				if (tekrar == bytes.length / bytesayisi)
					break;
				String binaryString = birlestirici(bytes, bytesayisi, siralama);
				int[] binary = hexatobinary(binaryString);

				int signednumber = hexTo2scomp(binary);
				System.out.println(signednumber);
				for (int i = 0; i < bytes.length - bytesayisi; i++)
					bytes[i] = bytes[i + bytesayisi];

				tekrar++;
			}
		} else if (veritipi == 3) {// taking floating point number

			int tekrar = 0;

			while (true) {
				if (tekrar == bytes.length / bytesayisi)
					break;

				String binaryString = birlestirici(bytes, bytesayisi, siralama);

				int[] binary = hexatobinary(binaryString);

				float floatingNumber = floating(binary);
				System.out.println(floatingNumber);

				for (int i = 0; i < bytes.length - bytesayisi; i++)
					bytes[i] = bytes[i + bytesayisi];

				tekrar++;
			}
		}
		// ********************************

	}

	public static float floating(int[] bytes) {
		float deger = 0;
		int exp = 0;
		if (bytes.length == 8) {
			exp = 4; // for exponent
			int S = 0;
			int e = 0;
			if (bytes[0] == '0') // for sign
				S = 0;
			if (bytes[0] == '1')
				S = 1;
			for (int i = exp, j = 0; j < exp; i--, j++) { // calculating e
				e += (int) Math.pow(2, j) * bytes[i]; //
			}

			int E = e - ((int) Math.pow(2, exp - 1) - 1); // bias = 2^k-1 -1

			float mantisa = 1;
			for (int i = exp + 1, j = 1; i < bytes.length; i++, j++) { // calculating mantisa
				mantisa += (float) Math.pow(2, -j) * bytes[i];

			}
			float value = (int) Math.pow((-1), S) * mantisa * (int) Math.pow(2, E); // floating value

			deger = value;

		} else if (bytes.length == 16) {// same with before if
			exp = 6;
			int S = 0;
			int e = 0;
			if (bytes[0] == '0')
				S = 0;
			if (bytes[0] == '1')
				S = 1;
			for (int i = exp, j = 0; j < exp; i--, j++) {
				e += (int) Math.pow(2, j) * bytes[i]; //
			}

			int E = e - ((int) Math.pow(2, exp - 1) - 1); // bias = 2^k-1 -1

			float mantisa = 1;
			for (int i = exp + 1, j = 1; i < bytes.length; i++, j++) {
				mantisa += (float) Math.pow(2, -j) * bytes[i];

			}
			float value = (int) Math.pow((-1), S) * mantisa * (int) Math.pow(2, E);

			deger = value;

		} else if (bytes.length == 24) {// same
			exp = 8;
			int S = 0;
			int e = 0;
			if (bytes[0] == '0')
				S = 0;
			if (bytes[0] == '1')
				S = 1;
			for (int i = exp, j = 0; j < exp; i--, j++) {
				e += (int) Math.pow(2, j) * bytes[i]; //
			}

			int E = e - ((int) Math.pow(2, exp - 1) - 1); // bias = 2^k-1 -1

			float mantisa = 1;
			for (int i = exp + 1, j = 1; i < bytes.length; i++, j++) {
				mantisa += (float) Math.pow(2, -j) * bytes[i];

			}
			float value = (int) Math.pow((-1), S) * mantisa * (int) Math.pow(2, E);

			deger = value;

		} else if (bytes.length == 32) {
			exp = 10;
			int S = 0;
			int e = 0;
			if (bytes[0] == '0')
				S = 0;
			if (bytes[0] == '1')
				S = 1;
			for (int i = exp, j = 0; j < exp; i--, j++) {
				e += (int) Math.pow(2, j) * bytes[i]; //
			}

			int E = e - ((int) Math.pow(2, exp - 1) - 1); // bias = 2^k-1 -1

			float mantisa = 1;
			for (int i = exp + 1, j = 1; i < bytes.length; i++, j++) {
				mantisa += (float) Math.pow(2, -j) * bytes[i];

			}
			float value = (int) Math.pow((-1), S) * mantisa * (int) Math.pow(2, E);

			deger = value;

		} else if (bytes.length == 48) {
			exp = 12;
			int S = 0;
			int e = 0;
			if (bytes[0] == '0')
				S = 0;
			if (bytes[0] == '1')
				S = 1;
			for (int i = exp, j = 0; j < exp; i--, j++) {
				e += (int) Math.pow(2, j) * bytes[i]; // calculatin e
			}

			int E = e - ((int) Math.pow(2, exp - 1) - 1); // bias = 2^k-1 -1

			float mantisa = 1;
			for (int i = exp + 1, j = 1; i < bytes.length; i++, j++) {
				mantisa += (float) Math.pow(2, -j) * bytes[i];

			}
			float value = (int) Math.pow((-1), S) * mantisa * (int) Math.pow(2, E);

			deger = value;

		}

		return deger;
	}
	// *************************************

	public static String birlestirici(String[] hexalar, int bytesayisi, int siralama) {
		// this method for associating string bytes and determine little indian or big
		// indian

		StringBuffer ekleme = new StringBuffer();

		String[] arr = new String[bytesayisi];

		if (siralama == 2) {
			
			int k = 0;
			for (int i = bytesayisi - 1; i >= 0; i--) {
				arr[i] = hexalar[k];

				k++;
			}
		} else if (siralama == 1) {
			for (int j = 0; j < bytesayisi; j++)

				arr[j] = hexalar[j];
		}

		for (int i = 0; i < bytesayisi; i++)
			ekleme.append(arr[i]);

		String binaryString = ekleme.toString();

		return binaryString;
	}

	

	public static int[] hexatobinary(String hexinput) {// hexa to binary
		int n = hexinput.length() * 4;
		int bin_num[] = new int[n];
		int unsigned = hexToDecimal(hexinput);

		int index = 0;
		while (unsigned > 0) {
			bin_num[index++] = unsigned % 2;
			unsigned = unsigned / 2;
		}
		// reversing array
		int[] temp = new int[n];
		int j = n;
		for (int i = 0; i < n; i++) {
			temp[j - 1] = bin_num[i];
			j = j - 1;
		}

		return temp;

	}
	public static int binarytodec(int[] binary) { // binary to decimal
		int sayi = 0;
		
		int decvalue = 0;

		for (int i = binary.length - 1; i >= 0; i--) {
			decvalue += binary[i] * Math.pow(2, sayi);
			sayi++;
		}

		return decvalue;
	}

	public static int hexTo2scomp(int[] hexinput) { // decimal from signed two's complement
		int value = 0;
		int n = hexinput.length; // total number of bits
		int unsigned = binarytodec(hexinput); // chancing hex to unsigned.

		int signed = 0;

		if (hexinput[0] == 1) {

			signed = ((int) Math.pow(2, n) - unsigned);

			value = -signed;

		} else {
			value = unsigned;

		}
		return value;

	}

	public static int hexToDecimal(String hexInput) {// hexa to decimal
		int decimal = 0;
		int len = hexInput.length();

		for (int i = 0; i < len; ++i) {
			char c = hexInput.charAt(i);
			int cValue;

			switch (c) {
			case '0':
				cValue = 0;
				break;
			case '1':
				cValue = 1;
				break;
			case '2':
				cValue = 2;
				break;
			case '3':
				cValue = 3;
				break;
			case '4':
				cValue = 4;
				break;
			case '5':
				cValue = 5;
				break;
			case '6':
				cValue = 6;
				break;
			case '7':
				cValue = 7;
				break;
			case '8':
				cValue = 8;
				break;
			case '9':
				cValue = 9;
				break;
			case 'a':
				cValue = 10;
				break;
			case 'b':
				cValue = 11;
				break;
			case 'c':
				cValue = 12;
				break;
			case 'd':
				cValue = 13;
				break;
			case 'e':
				cValue = 14;
				break;
			case 'f':
				cValue = 15;
				break;

			default:
				throw new IllegalArgumentException("bisey yok");
			}
			decimal = 16 * decimal + cValue;
		}
		return decimal;
	}

}
