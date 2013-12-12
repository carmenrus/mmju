package com.jdc.address;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

	private List<Address> list;
	private List<String> lines;

	public FileManager() {

		// initialize list
		this.list = new ArrayList<>();

		try {
			// read lines from file
			lines = Files.readAllLines(getPath(), Charset.defaultCharset());

			// for null pointer exception
			if (null == lines)
				lines = new ArrayList<>();

			// convert line to Address	
			for (String s : lines) {
				// add address to list
				this.list.add(this.getAddressFromLint(s));
			}

		} catch (IOException e) {
			System.out.println("There is no file.");
		}
	}

	public List<Address> getAllData() {
		return this.list;
	}

	public void AddData(Address address) {
		// add address to list
		this.list.add(address);
		// add line to lines
		this.lines.add(this.getLineFromAddress(address));
		// save lines
		this.save();
	}

	public void delete(Address address) {
		this.list.remove(address);
		this.lines.remove(this.getLineFromAddress(address));
		this.save();
	}

	public void update(Address oldAddress, Address newAddress) {
		// delete old object
		this.delete(oldAddress);
		// add new object
		this.AddData(newAddress);
	}
	
	private void save() {
		try {
			// write lines to file
			Files.delete(getPath());
			Files.write(getPath(), lines, Charset.defaultCharset(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.err.println("There is no file");
		}
	}

	private String getLineFromAddress(Address address) {
		StringBuilder sb = new StringBuilder();
		sb.append(address.getName()).append("\t");
		sb.append(address.getPhone()).append("\t");
		sb.append(address.getAddress());
		return sb.toString();
	}

	private Path getPath() {
		return Paths.get("data", getClass().getSimpleName() + ".txt");
	}

	private Address getAddressFromLint(String s) {
		return new Address(s.split("\t"));
	}

	public static void main(String[] args) {
		FileManager fm = new FileManager();
		Address a = new Address("Mg Mg", "0988775544", "Yangon, Myanmar");
		fm.delete(a);
	}

}
