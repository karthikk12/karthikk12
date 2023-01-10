package com.example.Printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.ReportsDto.MrrSummaryDto;
import com.example.ReportsDto.RiverRunDto;
import com.example.ReportsDto.SummaryHpDto;
import com.example.ReportsDto.VantageDto;

public class DataPrinter {

	public static void printAllDatainExcel(List<SummaryHpDto> reportDtoList, List<VantageDto> vantageDtoList,
			List<MrrSummaryDto> mrrSummaryDtoList, List<RiverRunDto> riverRunDtoList) throws IOException {

		String fileName = "C:\\Users\\FWR7591\\Downloads\\exc\\ReportTest.xlsx";

		FileInputStream input = new FileInputStream(new File(fileName));

		XSSFWorkbook workbook = new XSSFWorkbook(input);

		XSSFSheet sheet = workbook.getSheetAt(0);

		XSSFRow row = sheet.createRow(2);

	}

}
