package com.epam.lozovskyi.shop.Task_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class ChainOfResponsibility {
	public static void main(String[] args) {
		System.out.println("> Read text / Find file (0/1)");
		Scanner input = new Scanner(System.in);
		int act = input.nextInt();

		switch(act) {
			case(0):
				Scanner inputFileName = new Scanner(System.in);
				System.out.println("> Input file's name: ");
				String fileName = inputFileName.nextLine();
				try {
					Read(fileName);
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				inputFileName.close();
				break;
			case(1):
				System.out.println("> Set file's path to search: ");
				Scanner searchDir = new Scanner(System.in);
				String dir = searchDir.nextLine();
				SearchParams(dir);
				break;
		}
	}

	public static void Read(String fileName) throws Exception {
		File file = new File("Folder for txt/" + fileName + ".txt");
		FileReader fr = new FileReader(file); //создаем объект FileReader для объекта File
		BufferedReader reader = new BufferedReader(fr); //создаем BufferedReader с существующего FileReader для построчного считывания
		String line = reader.readLine(); // считаем сначала первую строку
		System.out.println("________________________");
		while (line != null) {
			System.out.println(line);
			line = reader.readLine(); // считываем остальные строки в цикле
		}
		System.out.println("________________________");
		reader.close();
	}

	// Поиск в конкретной директории
	public static void SearchParams(String dir) {
		String name = null;
		String postName = null;

		long minSize = 0, maxSize = 0;

		System.out.println("> Search by file's name? (0/1)");
		Scanner yn1 = new Scanner(System.in);
		int yns1 = yn1.nextInt();
		if(yns1 == 0) {
			System.out.println("> Set file's name(without extension): ");
			Scanner n = new Scanner(System.in);
			name = n.nextLine();

			File[] files = new File(dir).listFiles();
			for (File file : files) {
				if (file.isFile()) {
					String tmp = file.getName().substring(0, file.getName().lastIndexOf('.'));
					if(tmp.equals(name)) System.out.println("> File was found!");
				}
			}
		}

		System.out.println("> Search by file's extension? (0/1)");
		Scanner yn2 = new Scanner(System.in);
		int yns2 = yn2.nextInt();
		if(yns2 == 0) {
			System.out.println("> Set file's extension: ");
			Scanner pn = new Scanner(System.in);
			postName = pn.nextLine();

			File[] files = new File(dir).listFiles();
			for (File file : files) {
				if (file.isFile()) {
					String extension = "";
					int i = file.getName().lastIndexOf('.');
					if (i > 0) extension = file.getName().substring(i+1);
					if(extension.equals(postName)) System.out.println("> File was found!");
				}
			}
		}

		System.out.println("> Search by file's size? (0/1)");
		Scanner yn3 = new Scanner(System.in);
		int yns3 = yn3.nextInt();
		if(yns3 == 0) {
			System.out.println("> Set beginning(byte): ");
			Scanner min = new Scanner(System.in);
			minSize = min.nextInt();

			System.out.println("> Set end(byte): ");
			Scanner max = new Scanner(System.in);
			maxSize = max.nextInt();

			File[] files = new File(dir).listFiles();
			for (File file : files) {
				if (file.isFile())
					if(minSize <= file.length() && maxSize >= file.length()) System.out.println("> File was found!");
			}
		}

		System.out.println("> Search by date? (0/1)");
		Scanner yn4 = new Scanner(System.in);
		int yns4 = yn4.nextInt();
		if(yns4 == 0) {
			BasicFileAttributes attr;
			File[] files = new File(dir).listFiles();
			for (File file : files) {
				if (file.isFile()) {
					Path p = Paths.get(file.getAbsolutePath());
					try {
						attr = Files.readAttributes(p, BasicFileAttributes.class);
						System.out.println(attr.lastModifiedTime());
					} catch(IOException e) {
						e.printStackTrace();
					}

				}
			}
		}

/*
		// Блок поиска только по имени
		if(name != null && postName == null && minSize == 0 && maxSize == 0) {
			File[] files = new File(dir).listFiles();
			for (File file : files) {
			    if (file.isFile()) {
			    	String tmp = file.getName().substring(0, file.getName().lastIndexOf('.'));
			    	if(tmp.equals(name)) System.out.println("> Файл найден!");
			    }
			}
		}

		// Поиск по расширению
		if(name == null && postName != null && minSize == 0 && maxSize == 0) {
			File[] files = new File(dir).listFiles();
			for (File file : files) {
			    if (file.isFile()) {
			    	String extension = "";
			    	int i = file.getName().lastIndexOf('.');
			    	if (i > 0) extension = file.getName().substring(i+1);
			    	if(extension.equals(postName)) System.out.println("> Файл найден!");
			    }
			}
		}

		// Поиск в диапазоне рамеров
		if(name == null && postName == null && minSize >= 0 && maxSize != 0) {
			File[] files = new File(dir).listFiles();
			for (File file : files) {
			    if (file.isFile())
			    	if(minSize <= file.length() && maxSize >= file.length()) System.out.println("> Файл найден!");
			}
		}

		// Поиск по врменному диапазону
		if(name == null && postName == null && minSize == 0 && maxSize == 0) {
			BasicFileAttributes attr;
			File[] files = new File(dir).listFiles();
			for (File file : files) {
			    if (file.isFile()) {
			    	Path p = Paths.get(file.getAbsolutePath());
			    	try {
			    		attr = Files.readAttributes(p, BasicFileAttributes.class);
			    		System.out.println(attr.lastModifiedTime());
			    	} catch(IOException e) {
			    		e.printStackTrace();
			    	}

			    }
			}
		}

		if(name != null && postName != null && minSize != 0 && maxSize != 0) {
			File[] files = new File(dir).listFiles();
			for (File file : files) {
			    if (file.isFile()) {
			    	String tmp = file.getName().substring(0, file.getName().lastIndexOf('.'));
			    	if(tmp.equals(name)) System.out.println("> Файл по имени найден!");

			    	String extension = "";
			    	int i = file.getName().lastIndexOf('.');
			    	if (i > 0) extension = file.getName().substring(i+1);
			    	if(extension.equals(postName)) System.out.println("> Файл по расширению найден!");

			    	if(minSize <= file.length() && maxSize >= file.length()) System.out.println("> Файл по размеру найден найден!");
			    }
			}
		}
*/
		yn1.close();
		yn2.close();
		yn3.close();
		yn4.close();
	}

}

