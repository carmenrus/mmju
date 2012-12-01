package com.mmju.java7.nio.attribute;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

public class FileAttributeSample {
	
	public static void main(String[] args) {
		try {
			showAttributeInfo(FileSystems.getDefault().getPath(args[0]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showAttributeInfo(Path path) throws IOException {
		// File Owner
		UserPrincipal user = Files.getOwner(path);
		System.out.println("File Owner : " + user);

		// Last Update Time
		FileTime lstUpdTime = Files.getLastModifiedTime(path);
		System.out.println("Last Update Time : " + lstUpdTime);

		// Is writable
		boolean isWritable = Files.isWritable(path);
		System.out.println("Is Writable File : " + isWritable);

		// Creation Time
		FileTime creationTime = (FileTime) Files.getAttribute(path,
				"creationTime");
		System.out.println("File Creation Time : " + creationTime);
	}
}
