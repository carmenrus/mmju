package com.mmju.java7.nio.path;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FileSystemSample {

	public static void main(String[] args) {
		try (FileSystem fileSystem = FileSystems.getDefault()) {
			System.out.println(fileSystem.provider().getClass().getName());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
