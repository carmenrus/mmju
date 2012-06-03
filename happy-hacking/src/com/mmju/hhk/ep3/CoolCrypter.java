package com.mmju.hhk.ep3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CoolCrypter {

	public static void main(String[] args) {
		if (args.length != 2)
			System.err.println("Please set path and key!");
		try {
			new CoolCrypter().crypt(Paths.get(args[0]), args[1]);
		} catch (IOException e) {
			System.err.println("IOException");
		}
	}

	public void crypt(Path path, String key) throws IOException {
		byte[] input = Files.readAllBytes(path);
		byte[] result = new byte[input.length];
		byte[] keys = key.getBytes();
		int count = 0;

		for (int i = 0; i < input.length; i++) {
			if (count >= keys.length) {
				count = 0;
			}
			byte c = input[i];
			c ^= keys[count];
			result[i] = c;
			count++;
		}
		String newFile = path.toString() + "upd.txt";
		Files.write(Paths.get(newFile), result, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}

}
