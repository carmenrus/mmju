package com.mmju.java7.nio.path;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteSample {

	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		for (String s : args) {
			Path origin = fs.getPath(s);
			Files.delete(origin);
		}
	}
}
