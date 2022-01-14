package com.lozovskyi.shop.Task_6;

import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_1.entity.impl.JewelryEarrings;
import com.lozovskyi.shop.Task_1.entity.impl.JewelryRing;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SerializationTest {
	private static final int TIMES = 1000;
	private static final String FILE_NAME = "test.bin";
	private static final String COMPRESSED_FILE_NAME = "testCompressed.bin";

	private Map<Integer, Product> toSave;
	private File file;
	private File compressedFile;

	@Before
	public void initWrite() {
		toSave = new HashMap<>();
		Product productOne = new JewelryRing(1,1500,"Royal-Ring","Gold","Yellow",32,925,"Kingo");
		Product productTwo = new JewelryEarrings(2,1000,"Royal-Earring","platinum","grey",12,960,"Beach");

		toSave.put(productOne.getId(), productOne);
		toSave.put(productTwo.getId(), productTwo);

		file = new File(FILE_NAME);
		compressedFile = new File(COMPRESSED_FILE_NAME);

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
			objectOutputStream.writeObject(toSave);
			objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldWriteObjectAFewTimes() {
		long size = file.length();

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
			for (int i = 0; i < TIMES; i++) {
				objectOutputStream.writeObject(toSave);
			}
			objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(size + "   "+ file.length());
		assertThat(file.length()).isGreaterThan(size);
	}

	@Test
	public void shouldCompressObjectToWrite() {
		long size = file.length();
		try (FileOutputStream fileOutputStream = new FileOutputStream(compressedFile);
			 GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream)) {
			objectOutputStream.writeObject(toSave);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(size + "   "+ compressedFile.length());
		assertThat(compressedFile.length()).isLessThan(size);
	}
}

