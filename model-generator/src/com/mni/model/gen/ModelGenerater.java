package com.mni.model.gen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class ModelGenerater {
	
	private String table;
	private List<String> list;

	public ModelGenerater(String table)  {
		try {
			this.table = table;
			list = Files.readAllLines(Paths.get("template.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generate() {
		try {
			Files.write(Paths.get("model", getFileName()), getModelLines(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<String> getModelLines() {
		return list.stream()
			.map(a -> a.replace("{MODEL}", ModelGenerater.this.getModelName()))
			.map(a -> a.replace("{TABLE}", ModelGenerater.this.getTable()))
			.collect(Collectors.toList());
	}
	
	private String getTable() {
		return table;
	}
	
	private String getModelName() {
		return table.substring(0,1).toUpperCase() + table.substring(1) + "_m"; 
	}
	
	private String getFileName() {
		return table + "_m.php";
	}
	
	public static void main(String[] args) throws IOException {
		List<String> list = Files.readAllLines(Paths.get("model.txt"));
		list.stream().map(ModelGenerater::new).forEach(a -> {
			a.generate();
		});
	}

}
