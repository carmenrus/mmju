package com.mmju.java7.nio.path;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MoveSample {

	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path origin = fs.getPath(args[0]);
		Path toMove = fs.getPath(args[1]);
		Files.move(origin, toMove, StandardCopyOption.ATOMIC_MOVE);
	}
}
