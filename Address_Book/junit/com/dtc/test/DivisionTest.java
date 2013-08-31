package com.dtc.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dtc.address_ebook.Division;
import com.dtc.address_ebook.Townships;

public class DivisionTest {

	static EntityManager em;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		em = Persistence.createEntityManagerFactory("Address_Book")
				.createEntityManager();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		em.close();
	}

	@Before
	public void setUp() throws Exception {
		em.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		em.getTransaction().commit();
	}

	@Test
	public void test() throws IOException {
		// read files
		Path data = Paths.get("data");
		File[] files = data.toFile().listFiles();

		for (File f : files) {
			Division d = new Division();
			d.setName(f.getName());
			em.persist(d);

			List<String> lines = Files.readAllLines(f.toPath(),
					Charset.defaultCharset());
			
			for(String l : lines) {
				Townships t = new Townships();
				t.setName(l);
				t.setDivision(d);
				em.persist(t);
			}
		}
	}

}
