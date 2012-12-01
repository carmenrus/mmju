package com.mmju.java7.nio.visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class CpyDir {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out
					.println("Please set the parameter as Copy from and Copy to.");
			return;
		}

		try {
			Cpy cp = new Cpy();
			cp.copyDir(Paths.get(args[0]), Paths.get(args[1]));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	static class Cpy {

		public void copyDir(Path from, final Path to) throws IOException {
			FileVisitor<Path> visitor = new FileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(Path dir,
						IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					Files.copy(dir, to.resolve(dir),
							StandardCopyOption.COPY_ATTRIBUTES);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					Files.copy(file, to.resolve(file),
							StandardCopyOption.COPY_ATTRIBUTES);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file,
						IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}
			};

			Files.walkFileTree(from, visitor);
		}
	}

}
