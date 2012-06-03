package com.mmju.java7.nio.attribute;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileAttributeViewSample {

	public static void main(String[] args) {
		try {
			showAttributeInfo(FileSystems.getDefault().getPath(args[0]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showAttributeInfo(Path path) throws IOException {

		BasicFileAttributeView bfw = Files.getFileAttributeView(path,
				BasicFileAttributeView.class);
		BasicFileAttributes bfs = bfw.readAttributes();

		// Last Update Time
		FileTime lstUpdTime = bfs.lastModifiedTime();
		System.out.println("Last Update Time : " + lstUpdTime);

		// Creation Time
		FileTime creationTime = bfs.creationTime();
		System.out.println("Creation Time : " + creationTime);
	}

}
