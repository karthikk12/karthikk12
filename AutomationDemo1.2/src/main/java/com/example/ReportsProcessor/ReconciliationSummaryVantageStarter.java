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

import com.example.DataSetter.ReportDataSetter;
import com.example.Enums.ChromeType;
import com.example.ReportsDto.SummaryHpDto;
import com.example.ReportsDto.VantageDto;

public class ReconciliationSummaryVantageStarter {

	static SummaryHpReport summaryHpReport = new SummaryHpReport();

	static ReportDataSetter reportDataSetter = new ReportDataSetter();

	static List<VantageDto> vantageDtoList = new ArrayList<>();

	static SummaryHpDto summaryHpDto = new SummaryHpDto();

	static VantageDto vantageDto = new VantageDto();

	static MrrReport mrrReport = new MrrReport();

	static String secondPage = "http://report.nml.com/sites/cbpbi/Reports/Reconciliation/Reconciliation_Summary_Vantage.rdl?Web=1";

	static StringBuilder builder = new StringBuilder();

	public static void beginVantageLogin(List<SummaryHpDto> reportDtoList) throws InterruptedException, IOException {

		ChromeOptions options = new ChromeOptions().addArguments(String.valueOf(ChromeType.openType.getAction()));

		WebDriver driver = new ChromeDriver(options);

		String response = summaryHpReport.supplylogin(secondPage, driver);

		// System.out.println(response);

		executeVantageData(driver, reportDtoList);

	}

	public static void executeVantageData(WebDriver driver, List<SummaryHpDto> reportDtoList)
			throws InterruptedException, IOException {

		int responseWaiter = 0;

		while (!StringUtils.containsIgnoreCase(driver.getPageSource(), ">Reconciliation Report - Vantage")) {

			responseWaiter++;

			System.out.println("Waiting Vantage page Response......" + responseWaiter);

			Thread.sleep(3000);

			if (responseWaiter == Integer.parseInt(ChromeType.waitTime.getAction())) {

				beginVantageLogin(reportDtoList);

				responseWaiter = 0;

			}

		}

		String tableResponse = driver.getPageSource();

		if (StringUtils.containsIgnoreCase(tableResponse, ">Reconciliation Report - Vantage")) {

			List<WebElement> elements = driver.findElements(By.xpath(
					"//div[@id='VisibleReportContentm_sqlRsWebPart_ctl00_ReportViewer_ctl09']//div/table/tbody/tr/*"));

			for (WebElement eachElement : elements) {

				builder.append(eachElement.getText().trim());
			}

			getDataFromVantagePageContect(builder.toString(), driver, reportDtoList);

		}

		else {

			System.out.println("Some error Occurred....");
		}
	}

	private static void getDataFromVantagePageContect(String string, WebDriver driver, List<SummaryHpDto> reportDtoList)
			throws InterruptedException, IOException {

		if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - Vantage")
				&& StringUtils.containsIgnoreCase(builder, "Message Discrepancy in Vantage")
				&& StringUtils.containsIgnoreCase(builder, "Vantage")
				&& StringUtils.containsIgnoreCase(builder, "Hp")) {

			String reportData = StringUtils.substringBetween(builder.toString(), "Difference",
					"Message Discrepancy in Vantage");

			dataSetterInVantageDto(reportData.trim(), driver, reportDtoList);

		}

		else if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - Vantage")
				&& !StringUtils.containsIgnoreCase(builder, "Message Discrepancy in Vantage")
				&& StringUtils.containsIgnoreCase(builder, "Vantage")
				&& StringUtils.containsIgnoreCase(builder, "Hp")) {

			String reportData = StringUtils.substringAfter(builder.toString(), "Difference");

			dataSetterInVantageDto(reportData.trim(), driver, reportDtoList);

		}

	}

	public static void dataSetterInVantageDto(String reportData, WebDriver driver, List<SummaryHpDto> reportDtoList)
			throws InterruptedException, IOException {

		vantageDtoList = reportDataSetter.setVantageInRespectiveFields(reportData, vantageDtoList, vantageDto);

		System.out.println("Summary Vantage Data Successful.....");

		prepareThirdreport(reportDtoList, vantageDtoList);

	}

	public static void prepareThirdreport(List<SummaryHpDto> reportDtoList, List<VantageDto> vantageDtoList)
			throws InterruptedException, IOException {

		mrrReport.loginMrrReportPage(reportDtoList, vantageDtoList);
	}

}
