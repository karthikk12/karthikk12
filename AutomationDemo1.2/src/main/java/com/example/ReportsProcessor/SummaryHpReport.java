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

public class SummaryHpReport {

	static StringBuilder builder = new StringBuilder();

	static ReportDataSetter reportDataSetter = new ReportDataSetter();

	static List<SummaryHpDto> reportDtoList = new ArrayList<SummaryHpDto>();

	static SummaryHpDto summaryHpDto = new SummaryHpDto();

	static String mainPage = "http://report.nml.com/sites/cbpbi/Reports/Forms/current.aspx?RootFolder=%2fsites%2fcbpbi%2fReports%2fReconciliation&FolderCTID=0x0120003A31CE0010038F4D85F3E8E2E06252C3";

	static ReconciliationSummaryVantageStarter vantageStarter = new ReconciliationSummaryVantageStarter();

	public static void main(String[] args) throws InterruptedException, IOException {

		System.out.println("Initializing pgm....");

		begin();
	}

	public static void begin() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\FWR7591\\Downloads\\chromedriver_win32-108\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions().addArguments(String.valueOf(ChromeType.openType.getAction()));

		WebDriver driver = new ChromeDriver(options);

		String secondPage = "http://report.nml.com/sites/cbpbi/Reports/Reconciliation/Reconciliation_Summary_HP.rdl?Web=1";

		String response = supplylogin(secondPage, driver);

		int responseWaiter = 0;

		while (!StringUtils.containsIgnoreCase(driver.getPageSource().toString(), ">Reconciliation Report - HP")) {

			responseWaiter++;

			System.out.println("Waiting Summary Hp page Response......" + responseWaiter);

			Thread.sleep(3000);

			if (responseWaiter == Integer.parseInt(ChromeType.waitTime.getAction())) {

				begin();

				responseWaiter = 0;

			}

		}

		String tableResponse = driver.getPageSource();

		// System.out.println(tableResponse);

		if (StringUtils.containsIgnoreCase(tableResponse, ">Reconciliation Report - HP")) {

			System.out.println("yes");

			List<WebElement> elements = driver.findElements(By.xpath(
					"//div[@id='VisibleReportContentm_sqlRsWebPart_ctl00_ReportViewer_ctl09']//div/table/tbody/tr/*"));

			for (WebElement eachElement : elements) {

				// System.out.println(eachElement.getText());

				builder.append(eachElement.getText().trim());
			}

			getDataFromPageContect(builder.toString());

		}

		else {

			System.out.println("Some error Occurred....");
		}

	}

	public static String supplylogin(String secondPage, WebDriver driver) throws InterruptedException {

		driver.manage().window().maximize();

		driver.get(mainPage);

		Thread.sleep(1000);

		driver.get(secondPage);

		Thread.sleep(2000);

		String response = driver.getPageSource();

		if (StringUtils.containsIgnoreCase(response, ">Start date")
				&& StringUtils.containsIgnoreCase(response, ">End date")) {

			driver.findElement(By.name("m_sqlRsWebPart$ctl00$ctl19$ctl06$ctl03$txtValue")).sendKeys("1/5/2023");
			driver.findElement(By.name("m_sqlRsWebPart$ctl00$ctl19$ctl06$ctl05$txtValue")).sendKeys("1/8/2023");

			driver.findElement(By.name("m_sqlRsWebPart$ctl00$ctl19$ApplyParameters")).click();

		}

		return driver.getPageSource();

	}

	public static void getDataFromPageContect(String builder) throws InterruptedException, IOException {

		if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - HP")
				&& StringUtils.containsIgnoreCase(builder, "Message Discrepancy in HP")
				&& StringUtils.containsIgnoreCase(builder, "Date")
				&& StringUtils.containsIgnoreCase(builder, "Source")) {

			String reportData = StringUtils.substringBetween(builder.toString(), "Difference",
					"Message Discrepancy in HP");

			dataSetterInDto(reportData.trim());

			// System.out.println(reportData);

		}

		if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - HP")
				&& !StringUtils.containsIgnoreCase(builder, "Message Discrepancy in HP")
				&& StringUtils.containsIgnoreCase(builder, "Date")
				&& StringUtils.containsIgnoreCase(builder, "Source")) {

			String reportData = StringUtils.substringAfter(builder.toString(), "Difference");

			// System.out.println(reportData);

			dataSetterInDto(reportData.trim());

		}

	}

	public static void dataSetterInDto(String reportData) throws InterruptedException, IOException {

		reportDtoList = reportDataSetter.setDataInRespectiveFields(reportData, summaryHpDto, reportDtoList);

		System.out.println("Summary Hp Page Data Successful........");

		prepareSecondReport(reportDtoList);

	}

	public static void prepareSecondReport(List<SummaryHpDto> reportDtoList) throws InterruptedException, IOException {

		vantageStarter.beginVantageLogin(reportDtoList);

	}

}
