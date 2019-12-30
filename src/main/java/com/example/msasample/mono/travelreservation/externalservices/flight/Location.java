package com.example.msasample.mono.travelreservation.externalservices.flight;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Location {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File location = new File("text.txt");
		location.createNewFile();
		if (!location.exists()) {
			System.out.println("ファイルが見つかりません");
		} else {
			int readInt = 0;
			FileInputStream fileInputStream = new FileInputStream(location);
			while (readInt != -1) {

				readInt = fileInputStream.read();
				System.out.println((char) readInt);

			}
			fileInputStream.close();
		}

	}

	private static void addrness(String string) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unused")
	private static void location(String string) {
		// TODO Auto-generated method stub

	}

}
