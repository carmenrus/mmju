package com.mmju.java7.nio.visitor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitorTest {
	
	public static void main(String[] args) {

		FileVisitor<Path> visitor = new FileVisitor<Path>() {

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				System.out.println("postVisitDirectory : " + dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("preVisitDirectory : " + dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("visitFile : " + file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				System.out.println("visitFileFailed : " + file);
				return FileVisitResult.CONTINUE;
			}
		};

		try {
			Files.walkFileTree(FileSystems.getDefault().getPath("alpha"),
					visitor);
		} catch (IOException e) {
			System.err.println("There is no File.");
		}
	}
}
