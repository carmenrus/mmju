package com.mmju.java7.coin;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MultiCatchSample {

	public void doSample() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new FileInputStream("file.xml"));
			
			doc.getBaseURI();

		} catch (final ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
