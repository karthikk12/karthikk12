package com.example.Printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataPrinterTest {

	public static void main(String[] args) throws IOException {
		printAllDatainExcel();
	}

	public static void printAllDatainExcel() throws IOException {

		String fileName = "C:\\Users\\FWR7591\\Downloads\\exc\\ReportTest.xlsx";

		FileInputStream input = new FileInputStream(new File(fileName));

		XSSFWorkbook workbook = new XSSFWorkbook(input);

		XSSFSheet sheet = workbook.getSheetAt(1);

		Row row = sheet.createRow(2);

		Cell name = row.createCell(4);

		name.setCellValue("John");

		System.out.println("COmpleted..");

		workbook.write(new FileOutputStream(fileName));

		workbook.close();

	}

}
