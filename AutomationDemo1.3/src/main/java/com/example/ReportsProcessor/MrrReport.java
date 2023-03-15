
package com.example.ReportsProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.DataSetter.ReportDataSetter;
import com.example.Enums.ChromeType;
import com.example.ReportsDto.MrrSummaryDto;
import com.example.ReportsDto.SummaryHpDto;
import com.example.ReportsDto.VantageDto;

public class MrrReport {

	static SummaryHpReport summaryHpReport = new SummaryHpReport();

	static ReportDataSetter reportDataSetter = new ReportDataSetter();

	static SummaryHpDto summaryHpDto = new SummaryHpDto();

	static MrrSummaryDto mrrSummaryDto = new MrrSummaryDto();

	static RiverRunReport riverRunReport = new RiverRunReport();

	static List<MrrSummaryDto> mrrSummaryDtoList = new CopyOnWriteArrayList<>();

	static String thirdPage = "http://report.nml.com/sites/cbpbi/Reports/Reconciliation/Reconciliation_Summary_MRR.rdl?Web=1";

	static StringBuilder builder = new StringBuilder();

	public static void loginMrrReportPage(List<SummaryHpDto> reportDtoList, List<VantageDto> vantageDtoList,
			List<String> dateList) throws InterruptedException, IOException, ParseException {

		ChromeOptions options = new ChromeOptions().addArguments(String.valueOf(ChromeType.openType.getAction()));

		WebDriver driver = new ChromeDriver(options);

		String response = summaryHpReport.supplylogin(thirdPage, driver);

		// System.out.println(response);

		executeMrrData(driver, reportDtoList, vantageDtoList, dateList);

	}

	private static void executeMrrData(WebDriver driver, List<SummaryHpDto> reportDtoList,
			List<VantageDto> vantageDtoList, List<String> dateList)
			throws InterruptedException, IOException, ParseException {

		int responseWaiter = 0;

		while (!StringUtils.containsIgnoreCase(driver.getPageSource(), ">Reconciliation Report - MRR")) {

			responseWaiter++;

			System.out.println("Waiting_MRR page Response......" + responseWaiter);

			Thread.sleep(3000);

			if (responseWaiter == Integer.parseInt(ChromeType.waitTime.getAction())) {

				loginMrrReportPage(reportDtoList, vantageDtoList, dateList);

				responseWaiter = 0;

			}

		}

		String tableResponse = driver.getPageSource();

		if (StringUtils.containsIgnoreCase(tableResponse, ">Reconciliation Report - MRR")) {

			List<WebElement> elements = driver.findElements(By.xpath(
					"//div[@id='VisibleReportContentm_sqlRsWebPart_ctl00_ReportViewer_ctl09']//div/table/tbody/tr/*"));

			for (WebElement eachElement : elements) {

				builder.append(eachElement.getText().trim());
			}

			getDataFromMRRPageContect(builder.toString(), driver, reportDtoList, vantageDtoList, dateList);

		}

		else {

			System.out.println("Some error Occurred....");
		}
	}

	private static void getDataFromMRRPageContect(String string, WebDriver driver, List<SummaryHpDto> reportDtoList,
			List<VantageDto> vantageDtoList, List<String> dateList)
			throws InterruptedException, IOException, ParseException {

		if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - MRR")
				&& StringUtils.containsIgnoreCase(builder, "Message Discrepancy in MRR")
				&& StringUtils.containsIgnoreCase(builder, "MRR")
				&& StringUtils.containsIgnoreCase(builder, "Source")) {

			String reportData = StringUtils.substringBetween(builder.toString(), "Difference",
					"Message Discrepancy in MRR");

			dataSetterInMRRDto(reportData.trim(), driver, reportDtoList, vantageDtoList, dateList);

		}

		else if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - MRR")
				&& !StringUtils.containsIgnoreCase(builder, "Message Discrepancy in MRR")
				&& StringUtils.containsIgnoreCase(builder, "MRR")
				&& StringUtils.containsIgnoreCase(builder, "Source")) {

			String reportData = StringUtils.substringAfter(builder.toString(), "Difference");

			dataSetterInMRRDto(reportData.trim(), driver, reportDtoList, vantageDtoList, dateList);

		}

	}

	private static void dataSetterInMRRDto(String report, WebDriver driver, List<SummaryHpDto> reportDtoList,
			List<VantageDto> vantageDtoList, List<String> dateList)
			throws InterruptedException, IOException, ParseException {

		mrrSummaryDtoList = reportDataSetter.setMrrDataInFields(report, mrrSummaryDto, mrrSummaryDtoList);

		System.out.println(SummaryHpReport.lineSkipper + "MRR Report Data Successful (3/4)........");

		prepareFourthReport(reportDtoList, vantageDtoList, mrrSummaryDtoList, dateList);

	}

	private static void prepareFourthReport(List<SummaryHpDto> reportDtoList, List<VantageDto> vantageDtoList,
			List<MrrSummaryDto> mrrSummaryDtoList, List<String> dateList)
			throws InterruptedException, IOException, ParseException {

		riverRunReport.loginRiverRunPage(reportDtoList, vantageDtoList, mrrSummaryDtoList, dateList);

	}
}
