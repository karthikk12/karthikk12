package com.example.ReportsProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.DataSetter.ReportDataSegregator;
import com.example.DataSetter.ReportDataSetter;
import com.example.Enums.ChromeType;
import com.example.Printer.DataPrinter;
import com.example.ReportsDto.MrrSummaryDto;
import com.example.ReportsDto.RiverRunDto;
import com.example.ReportsDto.SummaryHpDto;
import com.example.ReportsDto.VantageDto;

public class RiverRunReport {

	static ReportDataSetter reportDataSetter = new ReportDataSetter();

	static ReportDataSegregator reportsegerator = new ReportDataSegregator();

	static MrrSummaryDto mrrSummaryDto = new MrrSummaryDto();

	static SummaryHpReport summaryHpReport = new SummaryHpReport();

	static RiverRunDto riverRunDto = new RiverRunDto();

	static List<RiverRunDto> riverRunDtoList = new ArrayList<>();

	static StringBuilder builder = new StringBuilder();

	static String fourthPage = "http://report.nml.com/sites/cbpbi/Reports/Reconciliation/Reconciliation_Summary_RiverRunData.rdl?Web=1";

	public static void loginRiverRunPage(List<SummaryHpDto> reportDtoList, List<VantageDto> vantageDtoList,
			List<MrrSummaryDto> mrrSummaryDtoList) throws InterruptedException, IOException {

		ChromeOptions options = new ChromeOptions().addArguments(String.valueOf(ChromeType.openType.getAction()));

		WebDriver driver = new ChromeDriver(options);

		String response = summaryHpReport.supplylogin(fourthPage, driver);

		// System.out.println(response);

		executeRiverRunData(driver, reportDtoList, vantageDtoList, mrrSummaryDtoList);

	}

	private static void executeRiverRunData(WebDriver driver, List<SummaryHpDto> reportDtoList,
			List<VantageDto> vantageDtoList, List<MrrSummaryDto> mrrSummaryDtoList)
			throws InterruptedException, IOException {

		int responseWaiter = 0;

		while (!StringUtils.containsIgnoreCase(driver.getPageSource(), ">Reconciliation Report - RiverRun")) {

			responseWaiter++;

			System.out.println("Waiting RiverRun, page Response......" + responseWaiter);

			Thread.sleep(3000);

			if (responseWaiter == Integer.parseInt(ChromeType.waitTime.getAction())) {

				loginRiverRunPage(reportDtoList, vantageDtoList, mrrSummaryDtoList);

				responseWaiter = 0;

			}

		}

		String tableResponse = driver.getPageSource();

		if (StringUtils.containsIgnoreCase(tableResponse, ">Reconciliation Report - RiverRun")) {

			List<WebElement> elements = driver.findElements(By.xpath(
					"//div[@id='VisibleReportContentm_sqlRsWebPart_ctl00_ReportViewer_ctl09']//div/table/tbody/tr/*"));

			for (WebElement eachElement : elements) {

				builder.append(eachElement.getText().trim());
			}

			getDataFromRiverRunPageContect(builder.toString(), driver, reportDtoList, vantageDtoList,
					mrrSummaryDtoList);

		}

		else {

			System.out.println("Some error Occurred....");
		}
	}

	private static void getDataFromRiverRunPageContect(String string, WebDriver driver,
			List<SummaryHpDto> reportDtoList, List<VantageDto> vantageDtoList, List<MrrSummaryDto> mrrSummaryDtoList)
			throws IOException {

		if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - RiverRun")
				&& StringUtils.containsIgnoreCase(builder, "Message Discrepancy in RiverRun")
				&& StringUtils.containsIgnoreCase(builder, "RiverRun")
				&& StringUtils.containsIgnoreCase(builder, "Hp")) {

			String reportData = StringUtils.substringBetween(builder.toString(), "Difference",
					"Message Discrepancy in RiverRun");

			dataSetterInRiverRunDto(reportData.trim(), driver, reportDtoList, vantageDtoList, mrrSummaryDtoList);

		}

		else if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - RiverRun")
				&& !StringUtils.containsIgnoreCase(builder, "Message Discrepancy in RiverRun")
				&& StringUtils.containsIgnoreCase(builder, "RiverRun")
				&& StringUtils.containsIgnoreCase(builder, "Hp")) {

			String reportData = StringUtils.substringAfter(builder.toString(), "Difference");

			dataSetterInRiverRunDto(reportData.trim(), driver, reportDtoList, vantageDtoList, mrrSummaryDtoList);

		}

	}

	private static void dataSetterInRiverRunDto(String report, WebDriver driver, List<SummaryHpDto> reportDtoList,
			List<VantageDto> vantageDtoList, List<MrrSummaryDto> mrrSummaryDtoList) throws IOException {

		riverRunDtoList = reportDataSetter.setRiverRunDataInFileds(report, riverRunDtoList, riverRunDto);

		System.out.println("River Run Data Successful.....");

		printAllData(reportDtoList, vantageDtoList, mrrSummaryDtoList);

		driver.quit();

		DataPrinter.printAllDatainExcel(reportDtoList, vantageDtoList, mrrSummaryDtoList, riverRunDtoList);

		System.exit(0);

	}

	private static void printAllData(List<SummaryHpDto> reportDtoList, List<VantageDto> vantageDtoList,
			List<MrrSummaryDto> mrrSummaryDtoList) {

		if (reportDtoList.size() == 0 || vantageDtoList.size() == 0 || mrrSummaryDtoList.size() == 0
				|| riverRunDtoList.size() == 0) {

			System.out.println("Some List is Empty..Please Verify..");

		} else {

			reportDtoList.forEach(n -> System.out.println(n));

			vantageDtoList.forEach(n -> System.out.println(n));

			mrrSummaryDtoList.forEach(n -> System.out.println(n));

			riverRunDtoList.forEach(n -> System.out.println(n));

			reportsegerator.segregatingDataInRespectiveFileds(reportDtoList, vantageDtoList, mrrSummaryDtoList,
					riverRunDtoList);

			System.out.println("Completed....");
		}

	}

}
