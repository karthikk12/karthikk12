package com.example.Printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.ReportsProcessor.SummaryHpReport;
import com.example.SegregatorDto.Confluence_SegDto;
import com.example.SegregatorDto.Email_SegDto;
import com.example.SegregatorDto.GlobalRelay_SegDto;
import com.example.SegregatorDto.Mrr_SegDto;
import com.example.SegregatorDto.RingCentral_SegDto;
import com.example.SegregatorDto.RiverRun_SegDto;
import com.example.SegregatorDto.Skype_SegDto;
import com.example.SegregatorDto.SocialMedia_SegDto;
import com.example.SegregatorDto.Zoom_SegDto;

public class DataPrinter {

	static SummaryHpReport summaryHpReport = new SummaryHpReport();

	public void printAllDatainExcel(List<Confluence_SegDto> confluenceList, List<Email_SegDto> emailList,
			List<GlobalRelay_SegDto> globalRelayList, List<Mrr_SegDto> mrrList,
			List<RingCentral_SegDto> ringCentralList, List<RiverRun_SegDto> riverRunList, List<Skype_SegDto> skypeList,
			List<SocialMedia_SegDto> socialMediaList, List<Zoom_SegDto> zoomList, List<String> dateList)
			throws IOException {

		String fileName = "C:\\Users\\FWR7591\\Downloads\\Reconciliaton Report\\Reconciliation_Report_Data.xlsx";

		FileInputStream input = new FileInputStream(new File(fileName));

		XSSFWorkbook workbook = new XSSFWorkbook(input);

		XSSFSheet sheet = workbook.getSheetAt(0);

		XSSFCellStyle style = workbook.createCellStyle();

		style.setBorderBottom(BorderStyle.THIN);

		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderRight(BorderStyle.THIN);

		style.setRightBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderTop(BorderStyle.THIN);

		style.setTopBorderColor(IndexedColors.BLACK.getIndex());

		int counter = 0;

		int masterCount = 0;

// Date Setter
		for (int i = 2; i < 6; i++) {

			try {

				Row row = sheet.createRow(i);

				Cell cell = row.createCell(counter);

				cell.setCellValue(dateList.get(masterCount));

				masterCount++;

			} catch (IndexOutOfBoundsException e) {

				System.out.println("Index Out of bound in Date List of Data Printer :" + e.getMessage());
			}

		}

// Remaining Data Setter
		for (int i = 2; i < 6; i++) {

			counter = 0;

			String dateValue = String.valueOf(sheet.getRow(i).getCell(0));

			// System.out.println(dateValue);

			Row row = sheet.createRow(i);

			while (counter != 28) {

				Cell cell = row.createCell(counter);

				if (counter == 0) {

					cell.setCellValue(dateValue);

					cell.setCellStyle((CellStyle) style);

				}

				if (counter == 1) {

					for (int j = 0; j < emailList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, emailList.get(j).getDate())) {

							cell.setCellValue(emailList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}

				}
				if (counter == 2) {

					for (int j = 0; j < emailList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, emailList.get(j).getDate())) {

							cell.setCellValue(emailList.get(j).getDsFromExchange());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 3) {

					for (int j = 0; j < emailList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, emailList.get(j).getDate())) {

							cell.setCellValue(emailList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 4) {

					for (int j = 0; j < riverRunList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, riverRunList.get(j).getDate())) {

							cell.setCellValue(riverRunList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}

				if (counter == 5) {

					for (int j = 0; j < riverRunList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, riverRunList.get(j).getDate())) {

							cell.setCellValue(riverRunList.get(j).getDsFromExchange());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 6) {

					for (int j = 0; j < riverRunList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, riverRunList.get(j).getDate())) {

							cell.setCellValue(riverRunList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 7) {

					for (int j = 0; j < socialMediaList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, socialMediaList.get(j).getDate())) {

							cell.setCellValue(socialMediaList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 8) {

					for (int j = 0; j < socialMediaList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, socialMediaList.get(j).getDate())) {

							cell.setCellValue(socialMediaList.get(j).getDsFromExchange());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 9) {

					for (int j = 0; j < socialMediaList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, socialMediaList.get(j).getDate())) {

							cell.setCellValue(socialMediaList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}

				if (counter == 10) {

					for (int j = 0; j < globalRelayList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, globalRelayList.get(j).getDate())) {

							cell.setCellValue(globalRelayList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 11) {

					for (int j = 0; j < globalRelayList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, globalRelayList.get(j).getDate())) {

							cell.setCellValue(globalRelayList.get(j).getDsFromExchange());

							break;

						}

					}
				}
				if (counter == 12) {

					for (int j = 0; j < globalRelayList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, globalRelayList.get(j).getDate())) {

							cell.setCellValue(globalRelayList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}

				if (counter == 13) {

					for (int j = 0; j < mrrList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, mrrList.get(j).getDate())) {

							cell.setCellValue(mrrList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 14) {

					for (int j = 0; j < mrrList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, mrrList.get(j).getDate())) {

							cell.setCellValue(mrrList.get(j).getDsFromExchange());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 15) {

					for (int j = 0; j < mrrList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, mrrList.get(j).getDate())) {

							cell.setCellValue(mrrList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}

				if (counter == 16) {

					for (int j = 0; j < skypeList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, skypeList.get(j).getDate())) {

							cell.setCellValue(skypeList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 17) {

					for (int j = 0; j < skypeList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, skypeList.get(j).getDate())) {

							cell.setCellValue(skypeList.get(j).getDsFromExchange());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 18) {

					for (int j = 0; j < skypeList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, skypeList.get(j).getDate())) {

							cell.setCellValue(skypeList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 19) {

					for (int j = 0; j < ringCentralList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, ringCentralList.get(j).getDate())) {

							cell.setCellValue(ringCentralList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 20) {

					for (int j = 0; j < ringCentralList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, ringCentralList.get(j).getDate())) {

							cell.setCellValue(ringCentralList.get(j).getDsFromExchange());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 21) {

					for (int j = 0; j < ringCentralList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, ringCentralList.get(j).getDate())) {

							cell.setCellValue(ringCentralList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 22) {

					for (int j = 0; j < confluenceList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, confluenceList.get(j).getDate())) {

							cell.setCellValue(confluenceList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 23) {

					for (int j = 0; j < confluenceList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, confluenceList.get(j).getDate())) {

							cell.setCellValue(confluenceList.get(j).getDsFromExchange());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 24) {

					for (int j = 0; j < confluenceList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, confluenceList.get(j).getDate())) {

							cell.setCellValue(confluenceList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}
				if (counter == 25) {

					for (int j = 0; j < zoomList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, zoomList.get(j).getDate())) {

							cell.setCellValue(zoomList.get(j).getExchangeToDs());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}

				if (counter == 26) {

					for (int j = 0; j < zoomList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, zoomList.get(j).getDate())) {

							cell.setCellValue(zoomList.get(j).getDsFromExchange());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}

				if (counter == 27) {

					for (int j = 0; j < zoomList.size(); j++) {

						if (StringUtils.equalsIgnoreCase(dateValue, zoomList.get(j).getDate())) {

							cell.setCellValue(zoomList.get(j).getVariance());

							cell.setCellStyle((CellStyle) style);

							break;

						}

					}
				}

				counter++;
			}
			masterCount++;
		}

		workbook.write(new FileOutputStream(fileName));

		workbook.close();

		System.out.println("File Created Completed..");

	}

}
