package com.mni.model.gen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AutoloadGen {
	
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("model.txt"));
		final StringBuilder sb = new StringBuilder();
		lines.stream().forEach(a -> sb.append("'").append(a).append("_m'").append(",\n"));
		System.out.println(sb.toString());
	}

}
