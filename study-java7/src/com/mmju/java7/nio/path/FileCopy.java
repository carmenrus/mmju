package com.mmju.java7.nio.path;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCopy {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("Please set 2 parameters");
			return;
		}

		try {
			FileSystem fs = FileSystems.getDefault();

			Path origin = fs.getPath(args[0]);
			Path toCopy = fs.getPath(args[1]);

			Files.copy(origin, toCopy);
		} catch (IOException e) {
			System.err.println("There is no file [" + args[0] + "]");
		}

	}
}
